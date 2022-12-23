package org.epam.spm.rest_api_test.service;


import org.epam.spm.rest_api_test.domain.Gift_certificate;
import org.epam.spm.rest_api_test.domain.Tag;

import java.util.Collection;

public interface TagService {
    Tag create(Tag tag);
    Collection<Tag> getAll();

    void removeByID(Integer id);
}
