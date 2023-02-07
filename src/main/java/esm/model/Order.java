package esm.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    LocalDateTime orderDate;
    BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
    List<GiftCertificate> certificateList = new ArrayList<>();

    public List<GiftCertificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<GiftCertificate> certificateList) {
        this.certificateList = certificateList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
