package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.epam.spm.rest_api_test.repository.CertificateRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceBean implements CertificateService {


    private final CertificateRepository certificateRepository;

    public CertificateServiceBean(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Gift_certificate create(Gift_certificate certificate) {
        return certificateRepository.save(certificate);
    }
}
