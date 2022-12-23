package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.springframework.stereotype.Service;


public interface CertificateService {
    Gift_certificate create(Gift_certificate certificate);
}
