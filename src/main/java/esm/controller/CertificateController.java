package esm.controller;


import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import esm.service.impl.CertificateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

    private final CertificateServiceImpl certificateServiceBean;

    public CertificateController(CertificateServiceImpl certificateServiceBean) {
        this.certificateServiceBean = certificateServiceBean;
    }

    @GetMapping("/{id}")
    public ResponseCertificateDTO getCertificateById(@PathVariable(value = "id") int id) {
        return certificateServiceBean.getCertificateById(id);
    }

    @PostMapping("/addCertificate")
    public ResponseCertificateDTO addCertificate(@RequestBody CertificateRequestDTO giftCertificate) {
        return certificateServiceBean.createCertificate(giftCertificate);
    }

    @GetMapping("/getAllCertificates")
    public List<ResponseCertificateDTO> getAllCertificate(@RequestBody CertificateFindByDTO certificateFindByDTO) {
        return certificateServiceBean.listCertificates(certificateFindByDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@PathVariable(value = "id") Integer id) {
        certificateServiceBean.deleteCertificateById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCertificateDTO editById(@RequestBody CertificateRequestDTO certificateRequestDTO, @PathVariable(value = "id") int id) {
        return certificateServiceBean.editCertificate(certificateRequestDTO, id);
    }

    @GetMapping("/findByTag/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCertificateDTO> findByTag(@PathVariable String name) {
        return certificateServiceBean.findByTagName(name);
    }


}
