package esm.urlCreator;


import esm.controller.CertificateController;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.request.FindByTagRequestDto;
import esm.dto.request.RequestPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CertificateUrlCreator {
    public Link getCertificateById(int id) {

        return linkTo(methodOn(CertificateController.class)
                .getCertificateById(id)).withRel("allCertificates");
    }

    public Link addCertificate(CertificateRequestDTO requestDTO) {
        return linkTo(methodOn(CertificateController.class).addCertificate(requestDTO)).withRel("addCertificate");
    }

    public Link editById(CertificateRequestDTO certificateRequestDTO, int id) {
        return linkTo(methodOn(CertificateController.class).
                editById(certificateRequestDTO, id)).withRel("addCertificate");
    }

    public Link findByTag(String tagName, RequestPage requestPage) {

        return linkTo(methodOn(CertificateController.class).findByTag(tagName, requestPage)).withRel("findByTag");
    }

    public Link findPopularTag() {
        return linkTo(methodOn(CertificateController.class).popularTag()).withRel("findPopularTag");
    }

}
