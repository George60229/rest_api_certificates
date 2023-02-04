package esm.dto.request;

import java.util.List;

public class BuyCertificatesRequestDTO {
    List<String> certificates;

    public List<String> getGiftCertificates() {
        return certificates;
    }

    public void setGiftCertificates(List<String> giftCertificates) {
        this.certificates = giftCertificates;
    }
}
