package org.epam.spm.rest_api_test.repository;

import org.epam.spm.rest_api_test.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
