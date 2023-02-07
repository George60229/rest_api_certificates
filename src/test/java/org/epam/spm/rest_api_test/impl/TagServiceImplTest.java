package org.epam.spm.rest_api_test.impl;


import esm.converter.TagConverter;
import esm.dto.request.TagRequestDTO;
import esm.dto.response.TagResponseDTO;
import esm.exception.AppNotFoundException;
import esm.exception.BadRequestException;
import esm.model.Tag;
import esm.repository.TagRepository;
import esm.service.TagService;
import esm.service.impl.TagServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {


    @Test
    public void findByTagName() {
        List<Tag> tags = new ArrayList<>();
        List<TagRequestDTO> expectedTags = new ArrayList<>();

        TagRequestDTO requestDTO = new TagRequestDTO();
        expectedTags.add(requestDTO);
        requestDTO.setName("Hello");

        Tag tag = new Tag();
        tag.setName("Hello");

        Tag resTag = new Tag();
        resTag.setId(5);
        resTag.setName("Hello");
        tags.add(resTag);


        Pageable pageable = PageRequest.of(0, 5);
        TagRepository mock = Mockito.mock(TagRepository.class);

        when(mock.findAll(pageable)).thenReturn(new PageImpl<>(tags));
        when(mock.findById(5)).thenReturn(Optional.of(resTag));
        when(mock.findById(100)).thenReturn(Optional.empty());





        TagService tagService = new TagServiceImpl(mock);
        tagService.setConverter(new TagConverter());

        Assertions.assertEquals(tagService.getById(5).getName(), tag.getName());
        Assertions.assertEquals(expectedTags.size(), tagService.getAllTags(pageable).getTotalElements());
        Assertions.assertEquals(tags.get(0).getName(), tagService.getById(5).getName());


        Assertions.assertThrows(AppNotFoundException.class, () -> {
            tagService.getById(100);
        });


    }


}
