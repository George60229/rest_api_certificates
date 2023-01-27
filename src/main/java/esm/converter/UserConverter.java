package esm.converter;

import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.model.GiftCertificate;
import esm.model.Order;
import esm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        userResponseDTO.setOrders(user.getOrders());
        return userResponseDTO;
    }

    public User convertDTOtoModel(UserRequestDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getName());
        Order order = getOrder(userDTO.getCertificates());
        user.addOrder(order);
        return user;
    }

    public Page<UserResponseDto> listToPage(List<UserResponseDto> userResponseDto) {

        return new PageImpl<>(userResponseDto);
    }

    public Order getOrder(List<GiftCertificate> giftCertificates) {
        Order order = new Order();
        StringBuilder stringBuilder = new StringBuilder();
        BigDecimal price = new BigDecimal(0);
        for (GiftCertificate giftCertificate : giftCertificates) {
            if (giftCertificate.getPrice() != null) {
                price = price.add(giftCertificate.getPrice());
            }
            stringBuilder.append("||").append(giftCertificate.getName());
        }
        order.setPrice(price);
        order.setOrderDate(LocalDateTime.now());
        order.setName(stringBuilder.toString());
        return order;
    }
}

