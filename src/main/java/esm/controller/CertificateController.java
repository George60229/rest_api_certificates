package esm.controller;


import esm.dto.request.CertificateEditDto;
import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.request.FindByTagDto;
import esm.dto.response.ResponseCertificateDTO;
import esm.dto.response.TagResponseDTO;
import esm.service.CertificateService;
import esm.service.impl.CertificateServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

    private final CertificateService certificateServiceBean;

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

    @GetMapping("/getAllCertificates/{page}")
    public Page<ResponseCertificateDTO> getAllCertificate(@PathVariable(value = "page") int number,@RequestBody CertificateFindByDTO certificateFindByDTO) {
        Pageable pageable = PageRequest.of(number - 1, 5);
        return certificateServiceBean.listCertificates(certificateFindByDTO,pageable);
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

    @GetMapping("/findByTag/{name}/{page}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseCertificateDTO> findByTag(@PathVariable(value = "name") String name ,@PathVariable(value = "page") int number) {
        Pageable pageable = PageRequest.of(number - 1, 5);
        return certificateServiceBean.findByTagName(name,pageable);
    }

    @PutMapping("/editOneField")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCertificateDTO editCertificateByParameter(@RequestBody CertificateEditDto certificateEditDto) {
        return certificateServiceBean.editOneField(certificateEditDto);

    }

    @GetMapping("/findByTags/{page}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseCertificateDTO> findByTagList(@RequestBody FindByTagDto tagNames,@PathVariable(value = "page") int number) {
        Pageable pageable = PageRequest.of(number - 1, 5);
        return certificateServiceBean.findByTagsNameList(tagNames.getTagNames(),pageable);
    }

    @GetMapping("/findPopularTag")
    public Page<TagResponseDTO> popularTag(){
        return certificateServiceBean.popularTag();
    }


}
