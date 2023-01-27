package esm.controller;

import esm.dto.request.BuyCertificatesDto;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.service.UserService;
import esm.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllUsers/{page}")
    public Page<UserResponseDto> getAllUsers(@PathVariable(value = "page") int number) {

        Pageable pageable = PageRequest.of(number-1, 5);
        return userService.getAll(pageable);
    }

    @PostMapping("/buyCertificate/{id}")
    public UserResponseDto buyCertificate(@RequestBody BuyCertificatesDto name, @PathVariable(value = "id") int id) {
        return userService.addCertificate(name, id);

    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable(value = "id") int id) {
        return userService.getUserById(id);
    }


}
