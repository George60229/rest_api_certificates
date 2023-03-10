package esm.controller;


import esm.dto.request.*;
import esm.dto.response.ResponseCertificateDTO;
import esm.dto.response.TagResponseDTO;
import esm.exception.BadRequestException;
import esm.exception.ErrorCode;
import esm.service.CertificateService;
import esm.service.impl.CertificateServiceImpl;
import esm.urlCreator.CertificateUrlCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

    private final CertificateService certificateServiceBean;

    @Autowired
    CertificateUrlCreator certificateUrlCreator;

    public CertificateController(CertificateServiceImpl certificateServiceBean) {
        this.certificateServiceBean = certificateServiceBean;
    }

    @RolesAllowed({"USER","ADMIN"})
    @GetMapping("/{id}")
    public CollectionModel<ResponseCertificateDTO> getCertificateById(@PathVariable(value = "id") int id) {
        List<ResponseCertificateDTO> list = new ArrayList<>();
        list.add(certificateServiceBean.getCertificateById(id));

        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());


        return CollectionModel.of(list, links);

    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/addCertificate")
    public CollectionModel<ResponseCertificateDTO> addCertificate(@RequestBody CertificateRequestDTO giftCertificate) {
        List<ResponseCertificateDTO> list = new ArrayList<>();
        list.add(certificateServiceBean.createCertificate(giftCertificate));

        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());

        return CollectionModel.of(list, links);

    }

    @RolesAllowed({"USER","ADMIN"})
    @GetMapping("/getAllCertificates")
    public CollectionModel<ResponseCertificateDTO> getAllCertificatesWithPage(@RequestBody CertificateFindByRequestDTO
                                                                                      certificateFindByRequestDTO) {
        Pageable pageable = PageRequest.of(certificateFindByRequestDTO.getPage(), 10);
        Page<ResponseCertificateDTO> list = certificateServiceBean.listCertificates(certificateFindByRequestDTO,
                pageable);


        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());
        return CollectionModel.of(list, links);
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@PathVariable(value = "id") Integer id) {
        certificateServiceBean.deleteCertificateById(id);
    }


    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<ResponseCertificateDTO> editById(@RequestBody CertificateRequestDTO certificateRequestDTO, @PathVariable(value = "id") int id) {
        List<ResponseCertificateDTO> list = new ArrayList<>();
        list.add(certificateServiceBean.editCertificate(certificateRequestDTO, id));

        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());

        return CollectionModel.of(list, links);
    }

    @RolesAllowed({"USER","ADMIN"})
    @GetMapping("/findByTag/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<ResponseCertificateDTO> findByTag(@PathVariable(value = "name") String name,
                                                             @RequestBody RequestPage requestPage) {

        Pageable pageable = PageRequest.of(defaultPagination(requestPage) - 1, 10);
        Page<ResponseCertificateDTO> list = (certificateServiceBean.findByTagName(
                name, pageable));


        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());

        return CollectionModel.of(list, links);
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("/editOneField")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<ResponseCertificateDTO> editCertificateByParameter(@RequestBody CertificateEditRequestDto certificateEditRequestDto) {
        List<ResponseCertificateDTO> list = new ArrayList<>();
        list.add(certificateServiceBean.editOneField(certificateEditRequestDto));
        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());
        return CollectionModel.of(list, links);

    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/findByTags")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<ResponseCertificateDTO> findByTagList(@RequestBody FindByTagRequestDto tagNames) {
        Pageable pageable = PageRequest.of(tagNames.getPage(), 10);
        Page<ResponseCertificateDTO> list = (certificateServiceBean.findByTagsNameList(
                tagNames.getTagNames(), pageable));

        List<Link> links = new ArrayList<>();
        links.add(certificateUrlCreator.findPopularTag());

        return CollectionModel.of(list, links);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/findPopularTag")
    public CollectionModel<TagResponseDTO> popularTag() {
        List<TagResponseDTO> list = new ArrayList<>();
        list.add(certificateServiceBean.popularTag());

        List<Link> links = new ArrayList<>();


        return CollectionModel.of(list, links);
    }

    private int defaultPagination(RequestPage requestPage) {
        int number = requestPage.getPage();
        if (requestPage.getPage() < 0) {
            throw new BadRequestException("Page must be positive", ErrorCode.BAD_REQUEST_ERROR);
        }
        if (number == 0) {
            number = 1;
        }
        return number;
    }
}
