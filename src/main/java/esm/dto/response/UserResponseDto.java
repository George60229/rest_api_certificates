package esm.dto.response;

import esm.model.GiftCertificate;

import java.util.List;

public class UserResponseDto {
    private int userId;
    private String name;

    private List<GiftCertificate> certificates;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<GiftCertificate> certificates) {
        this.certificates = certificates;
    }

    public void addCertificate(GiftCertificate giftCertificate) {
        certificates.add(giftCertificate);
    }
}
