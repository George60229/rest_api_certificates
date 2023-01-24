package esm.service;


import esm.dto.request.CertificateEditDto;
import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateService {




    /**
     * get all entity
     *
     * @return list of certificates.
     */


    List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO);

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

    List<ResponseCertificateDTO>findByTagName(String tagName);
}


