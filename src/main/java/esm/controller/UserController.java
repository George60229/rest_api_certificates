package esm.controller;

import esm.dto.request.CertificateFindByDTO;
import esm.dto.request.CertificateRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.ResponseCertificateDTO;
import esm.dto.response.UserResponseDto;
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
    public UserResponseDto addUser(@RequestBody UserRequestDto user) {
        return userService.create(user);
    }

    @GetMapping("/getAllUsers")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/buyCertificate/{id}")
    public UserResponseDto buyCertificate(String name,@PathVariable(value = "id")int id){
        return userService.addCertificate(name,id);

    }


}
