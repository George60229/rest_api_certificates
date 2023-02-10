package esm.dto.request;

import esm.model.GiftCertificate;
import esm.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRequestDto {
    String name;

    String surname;

    String password;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    String login;

    List<GiftCertificate> certificates = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<GiftCertificate> certificates) {
        this.certificates = certificates;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
