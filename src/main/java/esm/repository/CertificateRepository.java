package esm.repository;



import esm.model.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<GiftCertificate, Integer> {


}
