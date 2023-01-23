package org.epam.spm.rest_api_test.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tages")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tag_id;

    private String name;


    public Integer getId() {
        return tag_id;
    }

    public void setId(Integer id) {
        this.tag_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
