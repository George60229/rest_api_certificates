package org.epam.spm.rest_api_test.repository;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Gift_certificate, Integer> {

}
