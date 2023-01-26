package esm.service;



import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto user);

    Page<UserResponseDto> getAll(Pageable pageable);

    UserResponseDto addCertificate(String name,int id);

    UserResponseDto getUserById(int id);
}
