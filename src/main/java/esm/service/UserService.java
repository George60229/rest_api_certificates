package esm.service;


import esm.converter.UserConverter;
import esm.dto.request.BuyCertificatesRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserInfoResponseDto;
import esm.dto.response.UserResponseDto;
import esm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends  UserDetailsService {




    UserResponseDto getUserOrders(int id);

    void setConverter(UserConverter converter);

    UserInfoResponseDto create(UserRequestDto user);

    Page<UserResponseDto> getAll(Pageable pageable);

    UserResponseDto addCertificate(BuyCertificatesRequestDTO name, int id);

    UserInfoResponseDto getUserById(int id);

    UserResponseDto getUserWithMostExpensiveOrder();

    Page<UserInfoResponseDto> getUserInfo(Pageable pageable);
}
