package esm.controller;

import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.response.ResponseCertificateDTO;
import esm.model.User;
import esm.service.UserService;
import esm.service.impl.CertificateServiceImpl;
import esm.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }



    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllCertificate() {
        return userService.getAll();
    }


}
