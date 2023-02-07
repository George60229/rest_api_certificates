package esm.dto.request;
import java.util.List;

public class BuyCertificatesRequestDTO {

    String[] certificates;

    public String[] getCertificates() {
        return certificates;
    }

    public void setCertificates(String[] certificates) {
        this.certificates = certificates;
    }
}
