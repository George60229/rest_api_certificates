package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {

    private final CertificateServiceBean certificateServiceBean;

    public CertificateController(CertificateServiceBean certificateServiceBean) {
        this.certificateServiceBean = certificateServiceBean;
    }

    @PostMapping("/certificate")
    @ResponseStatus(HttpStatus.CREATED)
    public Gift_certificate createCertificate(@RequestBody Gift_certificate certificate){

        return certificateServiceBean.create(certificate);
    }
}
