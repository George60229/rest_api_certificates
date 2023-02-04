package esm.service;



import esm.converter.UserConverter;
import esm.dto.request.BuyCertificatesRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void setConverter(UserConverter converter);
    UserResponseDto create(UserRequestDto user);

    Page<UserResponseDto> getAll(Pageable pageable);

    UserResponseDto addCertificate(BuyCertificatesRequestDTO name, int id);

    UserResponseDto getUserById(int id);

    UserResponseDto getUserWithMostExpensiveOrder();
}
