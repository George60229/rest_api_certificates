package esm.dto.request;

import java.util.List;

public class BuyCertificatesDto {
    List<String> giftCertificates;

    public List<String> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<String> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }
}
