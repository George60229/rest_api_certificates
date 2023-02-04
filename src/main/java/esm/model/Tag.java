package esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tages")
public class Tag extends RepresentationModel<Tag> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tag_id;


    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "certificates_tags",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")})
    private List<GiftCertificate> giftCertificates = new ArrayList<>();


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


    public int getGiftCertificatesSize() {
        return giftCertificates.size();
    }


}
