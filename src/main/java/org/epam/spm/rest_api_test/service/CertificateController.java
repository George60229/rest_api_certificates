package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {

    private final CertificateServiceBean certificateServiceBean;

    public CertificateController(CertificateServiceBean certificateServiceBean) {
        this.certificateServiceBean = certificateServiceBean;
    }

    @PostMapping("/certificate")
    @ResponseStatus(HttpStatus.CREATED)
    public Gift_certificate createCertificate(@RequestBody Gift_certificate certificate) {
        return certificateServiceBean.create(certificate);
    }

    @GetMapping("/certificate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Collection<Gift_certificate> getAllCertificates() {
        return certificateServiceBean.getAll();
    }

    @DeleteMapping("/certificate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@PathVariable Integer id) {
        certificateServiceBean.removeByID(id);
    }





}
