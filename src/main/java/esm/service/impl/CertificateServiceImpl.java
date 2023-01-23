package esm.service.impl;


import esm.converter.CertificateConverter;
import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import esm.exception.AppNotFoundException;

import esm.exception.ErrorCode;
import esm.model.GiftCertificate;
import esm.model.Tag;
import esm.repository.CertificateRepository;
import esm.repository.TagRepository;
import esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository, TagRepository tagRepository) {
        this.certificateRepository = certificateRepository;

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
    public ResponseCertificateDTO createCertificate(CertificateRequestDTO certificateDTO) {
        return converter.convertToDTO(certificateRepository.save(converter.convertDTOtoModel(certificateDTO)));
    }


    @Override
    public List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO) {
        if (certificateFindByDTO == null) {
            certificateFindByDTO = new CertificateFindByDTO();
        }
        return converter.convertListToDTO(certificateRepository.findAll());
    }

    @Override
    public ResponseCertificateDTO editCertificate(CertificateRequestDTO certificateEditDto, int id) {
        Optional<GiftCertificate> giftCertificate = certificateRepository.findById(id);

        if (giftCertificate.isEmpty()) {
            throw new AppNotFoundException("Certificate with this id is not exist"+id,ErrorCode.TAG_NOT_FOUND);
        }
       GiftCertificate certificate= giftCertificate.get();

        return converter.convertToDTO(certificateRepository.save(converter.updateByRequest(certificate, certificateEditDto)));
    }


}
