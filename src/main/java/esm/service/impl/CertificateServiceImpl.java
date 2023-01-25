package esm.service.impl;


import esm.converter.CertificateConverter;
import esm.dto.request.CertificateEditDto;
import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import esm.exception.AppNotFoundException;

import esm.exception.BadRequestException;
import esm.exception.ErrorCode;
import esm.model.GiftCertificate;
import esm.model.Tag;
import esm.repository.CertificateRepository;
import esm.repository.TagRepository;
import esm.service.CertificateService;
import esm.utils.FindParameter;
import esm.utils.SortParameter;
import esm.utils.SortWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    private final TagRepository tagRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository, TagRepository tagRepository) {
        this.certificateRepository = certificateRepository;

        this.tagRepository = tagRepository;
    }

    @Autowired
    private CertificateConverter converter;


    @Override
    public void deleteCertificateById(Integer id) {
        certificateRepository.deleteById(id);
    }


    @Override
    public ResponseCertificateDTO getCertificateById(int id) {

        if (certificateRepository.findById(id).isEmpty()) {
            throw new AppNotFoundException("Certificate with this id " + id + " is not found",
                    ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        GiftCertificate giftCertificate = certificateRepository.findById(id).get();

        return converter.convertToDTO(giftCertificate);
    }


    @Override
    public ResponseCertificateDTO createCertificate(CertificateRequestDTO certificateDto) {

        List<Tag> result = new ArrayList<>();

        for (int i = 0; i < certificateDto.getTags().size(); i++) {
            List<Tag> tags = tagRepository.findByName(certificateDto.getTags().get(i).getName());
            if (!tagRepository.findByName(certificateDto.getTags().get(i).getName()).isEmpty()) {
                result.addAll(tags);
            } else {
                Tag tag = new Tag();
                tag.setName(certificateDto.getTags().get(i).getName());
                result.add(tag);
            }

        }
        certificateDto.setTags(result);
        return converter.convertToDTO(certificateRepository.save(converter.convertDTOtoModel(certificateDto)));
    }


    @Override
    public List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO) {
        if (certificateFindByDTO == null) {
            certificateFindByDTO = new CertificateFindByDTO();
        }
        if (!certificateFindByDTO.getFindParameter().equals(FindParameter.DEFAULT) && certificateFindByDTO.getValue().equals("")) {
            throw new BadRequestException("Value must have FindParameter", ErrorCode.BAD_REQUEST_ERROR);
        }
        List<GiftCertificate> res;

        if (certificateFindByDTO.getFindParameter().name().equals("DESCRIPTION")) {
            res = certificateRepository.findByDescriptionLike(certificateFindByDTO.getValue());
        } else if (certificateFindByDTO.getFindParameter().name().equals("NAME")) {
            res = certificateRepository.findByNameLike(certificateFindByDTO.getValue());
        } else {
            res = certificateRepository.findAll();
        }

        if (certificateFindByDTO.getSortParameter().equals(SortParameter.DATE)) {
            if (certificateFindByDTO.getSortWay().equals(SortWay.DESC)) {
                res = res.stream().sorted(Comparator.comparing(GiftCertificate::getCreateDate).reversed()).collect(Collectors.toList());
            } else
                res = res.stream().sorted(Comparator.comparing(GiftCertificate::getCreateDate)).collect(Collectors.toList());
        }
        if (certificateFindByDTO.getSortParameter().equals(SortParameter.NAME)) {
            if (certificateFindByDTO.getSortWay().equals(SortWay.DESC)) {
                res = res.stream().sorted(Comparator.comparing(GiftCertificate::getName).reversed()).collect(Collectors.toList());
            } else
                res = res.stream().sorted(Comparator.comparing(GiftCertificate::getName)).collect(Collectors.toList());
        }


        return converter.convertListToDTO(res);
    }

    @Override
    public ResponseCertificateDTO editCertificate(CertificateRequestDTO certificateEditDto, int id) {
        Optional<GiftCertificate> giftCertificate = certificateRepository.findById(id);

        if (giftCertificate.isEmpty()) {
            throw new AppNotFoundException("Certificate with this id is not exist" + id, ErrorCode.TAG_NOT_FOUND);
        }

        GiftCertificate certificate = giftCertificate.get();
        List<Tag> result = new ArrayList<>();

        for (int i = 0; i < certificateEditDto.getTags().size(); i++) {
            List<Tag> tags = tagRepository.findByName(certificateEditDto.getTags().get(i).getName());
            if (!tagRepository.findByName(certificateEditDto.getTags().get(i).getName()).isEmpty()) {
                result.addAll(tags);
            } else {
                Tag tag = new Tag();
                tag.setName(certificateEditDto.getTags().get(i).getName());
                result.add(tag);
            }

        }
        certificateEditDto.setTags(result);
        return converter.convertToDTO(certificateRepository.save(converter.updateByRequest(certificate, certificateEditDto)));
    }

    @Override
    public List<ResponseCertificateDTO> findByTagName(String tagName) {
        return converter.convertListToDTO(certificateRepository.findByTagsName(tagName));
    }

    @Override
    public ResponseCertificateDTO editOneField(CertificateEditDto certificateEditDto) {

        if (certificateEditDto.getParameter() == null || certificateEditDto.getValue() == null
                || certificateEditDto.getId() == 0) {
            throw new BadRequestException("Value, id and parameter can't be null", ErrorCode.BAD_REQUEST_ERROR);
        }
        int id = certificateEditDto.getId();

        Optional<GiftCertificate> certificate = certificateRepository.findById(id);
        if (certificate.isEmpty()) {
            throw new AppNotFoundException("Certificate with this id is not found: " + id,
                    ErrorCode.CERTIFICATE_NOT_FOUND);
        }

        GiftCertificate giftCertificate = certificate.get();
        return converter.convertToDTO(certificateRepository.save(converter.updateByField(certificateEditDto, giftCertificate)));
    }


}
