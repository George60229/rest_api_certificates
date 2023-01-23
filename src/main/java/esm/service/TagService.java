package esm.service;


import esm.converter.TagConverter;
import esm.dto.request.TagRequestDTO;
import esm.dto.response.TagResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TagService {


    TagResponseDTO createTag(@RequestBody TagRequestDTO tagDTO);

    List<TagResponseDTO> getAllTags();

    void deleteById(int id);

    TagResponseDTO getById(int id);




}
