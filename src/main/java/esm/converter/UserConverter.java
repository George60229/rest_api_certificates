package esm.converter;

import esm.dto.request.TagRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.TagResponseDTO;
import esm.dto.response.UserResponseDto;
import esm.model.Tag;
import esm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.geo.GeoPage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public Page<UserResponseDto> convert(Page<User> users) {
        return listToPage(users.stream()
                .map(this::convertOneToDTO)
                .collect(Collectors.toList()));

    }

    public UserResponseDto convertOneToDTO(User user) {
        UserResponseDto userResponseDTO = new UserResponseDto();
        userResponseDTO.setUserId(user.getUserId());
        userResponseDTO.setName(user.getUsername());
        userResponseDTO.setCertificates(user.getGiftCertificates());
        return userResponseDTO;
    }

    public User convertDTOtoModel(UserRequestDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getName());
        user.setGiftCertificates(userDTO.getCertificates());
        return user;
    }

    public Page<UserResponseDto> listToPage(List<UserResponseDto> userResponseDto) {

        return new PageImpl<>(userResponseDto);
    }
}

