package org.epam.spm.rest_api_test.service;

import org.epam.spm.rest_api_test.domain.Tag;
import org.epam.spm.rest_api_test.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TagServiceBean implements TagService {
    private final TagRepository tagRepository;

    public TagServiceBean(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Collection<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public void removeByID(Integer id) {

    }


}
