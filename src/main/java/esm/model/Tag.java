package esm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tages")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tag_id;
    private String name;

    public int getId() {
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
