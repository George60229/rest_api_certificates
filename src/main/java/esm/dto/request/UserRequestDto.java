package esm.dto.request;

import esm.model.GiftCertificate;

import java.util.ArrayList;
import java.util.List;

public class UserRequestDto {
    String name;

    List<GiftCertificate> certificates=new ArrayList<>();

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
}
