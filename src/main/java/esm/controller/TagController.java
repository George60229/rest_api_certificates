package esm.controller;


import esm.dto.request.TagRequestDTO;
import esm.dto.response.TagResponseDTO;

import esm.service.TagService;
import esm.service.impl.TagServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagServiceImpl;

    public TagController(TagServiceImpl tagServiceBean) {
        this.tagServiceImpl = tagServiceBean;
    }

    @GetMapping("/getTagById{id}")
    public TagResponseDTO getTagByID(@PathVariable(value = "id") int id) {
        return tagServiceImpl.getById(id);
    }


    @PostMapping("/addTag")
    public TagResponseDTO addTag(@RequestBody TagRequestDTO tag) {
        return tagServiceImpl.createTag(tag);
    }

    @GetMapping("/getAllTags/{page}")
    public Page<TagResponseDTO> getAllTags(@PathVariable(value = "page") int number) {
        Pageable pageable = PageRequest.of(number - 1, 10);
        return tagServiceImpl.getAllTags(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTagById(@PathVariable(value = "id") int id) {
        tagServiceImpl.deleteById(id);
    }


}
