package esm.repository;



import esm.model.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<GiftCertificate, Integer> {
    List<GiftCertificate> findByName(String name);
    List<GiftCertificate> findByTagsName(String name);
    List<GiftCertificate> findByDescriptionLike(String description);
    List<GiftCertificate> findByNameLike(String name);


}
