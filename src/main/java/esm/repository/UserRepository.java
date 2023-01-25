package esm.repository;


import esm.model.GiftCertificate;
import esm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("update User u set u.giftCertificates = ?1 where u.userId = ?2")
    int updateGiftCertificatesByUserId(GiftCertificate giftCertificates, Integer userId);



}
