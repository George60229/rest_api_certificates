package esm.service.impl;


import esm.converter.TagConverter;
import esm.dto.request.TagRequestDTO;
import esm.dto.response.TagResponseDTO;
import esm.exception.AppNotFoundException;
import esm.exception.ErrorCode;
import esm.model.Tag;
import esm.repository.TagRepository;
import esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TagServiceImpl implements TagService {


    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Autowired
    TagConverter converter;


    @Override
    public void setConverter(TagConverter tagConverter) {
        converter=tagConverter;
    }

    @Override
    public TagResponseDTO createTag(TagRequestDTO tagDTO) {
        return converter.convertOneToDTO(tagRepository.save(converter.convertDTOtoModel(tagDTO)));
    }

    @Override
    public Page<TagResponseDTO> getAllTags(Pageable pageable) {
        return converter.convert(tagRepository.findAll(pageable));
    }

    @Override
    public void deleteById(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public TagResponseDTO getById(int id) {
        Optional<Tag> myTag = tagRepository.findById(id);
        if (myTag.isEmpty()) {
            throw new AppNotFoundException("Tag with this id " + id + " is not found", ErrorCode.TAG_NOT_FOUND);
        }
        return converter.convertOneToDTO(myTag.get());
    }

}
