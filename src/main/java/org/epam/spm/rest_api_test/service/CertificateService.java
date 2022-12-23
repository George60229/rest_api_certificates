package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;

import java.util.Collection;
import java.util.List;


public interface CertificateService {
    Gift_certificate create(Gift_certificate certificate);

    Collection<Gift_certificate> getAll();
    void removeByID(Integer id);


}
