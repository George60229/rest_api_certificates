package esm.controller;

import esm.dto.request.BuyCertificatesRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.service.UserService;
import esm.service.impl.UserServiceImpl;
import esm.urlCreator.UserUrlCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {


    private final UserService userService;

    @Autowired
    UserUrlCreator userUrlCreator;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/addUser")
    public CollectionModel<UserResponseDto> addUser(@RequestBody UserRequestDto user) {
        List<UserResponseDto> list = new ArrayList<>();
        UserResponseDto userResponseDto = userService.create(user);
        list.add(userResponseDto);

        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getAllUsers());
        links.add(userUrlCreator.getRichUser());
        links.add(userUrlCreator.getUserById(userResponseDto.getUserId()));

        return CollectionModel.of(list, links);

    }

    @GetMapping("/getAllUsers")
    public CollectionModel<UserResponseDto> getAllUsers(@PageableDefault Pageable pageable) {
        Page<UserResponseDto> list = userService.getAll(pageable);

        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getRichUser());


        return CollectionModel.of(list, links);
    }

    @GetMapping("/getAllUsers/{page}")
    public CollectionModel<UserResponseDto> getAllUsersWithPage(@PathVariable(value = "page") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<UserResponseDto> list = userService.getAll(pageable);
        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getRichUser());


        return CollectionModel.of(list, links);
    }


    @PostMapping("/buyCertificate/{id}")
    public CollectionModel<UserResponseDto> buyCertificate(@RequestBody BuyCertificatesRequestDTO name, @PathVariable(value = "id") int id) {
        List<UserResponseDto> list = new ArrayList<>();

        list.add(userService.addCertificate(name, id));

        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getAllUsers());
        links.add(userUrlCreator.getRichUser());
        links.add(userUrlCreator.addOrder(id, name));
        links.add(userUrlCreator.getUserById(id));
        return CollectionModel.of(list, links);


    }

    @GetMapping("/{id}")
    public CollectionModel<UserResponseDto> getById(@PathVariable(value = "id") int id) {
        List<UserResponseDto> list = new ArrayList<>();

        list.add(userService.getUserById(id));

        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getAllUsers());
        links.add(userUrlCreator.getRichUser());


        return CollectionModel.of(list, links);

    }


    @GetMapping("/richUser")
    public CollectionModel<UserResponseDto> getRichUser() {


        List<UserResponseDto> list = new ArrayList<>();

        list.add(userService.getUserWithMostExpensiveOrder());

        List<Link> links = new ArrayList<>();
        links.add(userUrlCreator.getAllUsers());
        links.add(userUrlCreator.getRichUser());
        return CollectionModel.of(list, links);

    }


}
