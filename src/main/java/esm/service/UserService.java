package esm.service;



import esm.dto.request.BuyCertificatesDto;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDto create(UserRequestDto user);

    Page<UserResponseDto> getAll(Pageable pageable);

    UserResponseDto addCertificate(BuyCertificatesDto name, int id);

    UserResponseDto getUserById(int id);

    UserResponseDto getUserWithMostExpensiveOrder();
}
