package esm.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer userId;
    String username;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_certificates",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")})
    private List<GiftCertificate> giftCertificates;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GiftCertificate> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificate> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }
    public void addGiftCertificate(List<GiftCertificate> giftCertificate){
        this.giftCertificates.addAll(giftCertificate);
    }
}



