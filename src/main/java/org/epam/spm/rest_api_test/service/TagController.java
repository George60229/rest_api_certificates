package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.epam.spm.rest_api_test.domain.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagServiceBean tagServiceBean;

    public TagController(TagServiceBean tagServiceBean) {
        this.tagServiceBean = tagServiceBean;
    }

    @PostMapping("/tages")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createCertificate(@RequestBody Tag tag){

        return tagServiceBean.create(tag);
    }
}
