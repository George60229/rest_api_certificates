package esm.service;


import esm.converter.CertificateConverter;
import esm.converter.TagConverter;
import esm.dto.request.CertificateEditRequestDto;
import esm.dto.request.CertificateFindByRequestDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import esm.dto.response.TagResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateService {

    public void setConverter(CertificateConverter converter, TagConverter tagConverter);


    /**
     * get all entity
     *
     * @return list of certificates.
     */


    Page<ResponseCertificateDTO> listCertificates(CertificateFindByRequestDTO certificateFindByRequestDTO, Pageable pageable);

    /**
     * delete entity by id
     *
     * @param id just id
     */

    void deleteCertificateById(Integer id);


    ResponseCertificateDTO getCertificateById(@RequestParam int id);


    /**
     * get all entity by description
     *
     * @return list of certificates.
     */


    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */


    /**
     * get all entity ordered by date desc
     *
     * @return list of certificates.
     */


    ResponseCertificateDTO createCertificate(@RequestBody CertificateRequestDTO certificateDTO);


    ResponseCertificateDTO editCertificate(CertificateRequestDTO certificateEditDto, int id);

    Page<ResponseCertificateDTO> findByTagName(String tagName, Pageable pageable);

    ResponseCertificateDTO editOneField(CertificateEditRequestDto certificateEditRequestDto);

    Page<ResponseCertificateDTO> findByTagsNameList(List<String> tagNames,Pageable pageable);

    TagResponseDTO popularTag();
}


