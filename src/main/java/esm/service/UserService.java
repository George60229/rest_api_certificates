package esm.service;



import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto user);

    List<UserResponseDto> getAll();

    UserResponseDto addCertificate(String name,int id);

    UserResponseDto getUserById(int id);
}
