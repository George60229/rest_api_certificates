package org.epam.spm.rest_api_test.impl;

import esm.converter.UserConverter;
import esm.dto.request.BuyCertificatesRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.exception.AppNotFoundException;
import esm.model.GiftCertificate;
import esm.model.User;
import esm.repository.CertificateRepository;
import esm.repository.UserRepository;
import esm.service.UserService;
import esm.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Test
    public void first() {

        User user = new User();
        user.setUsername("George");
        user.setUserId(5);

        User userWithNoId = new User();
        userWithNoId.setUsername("George");



        Pageable pageable = PageRequest.of(0, 5);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        UserRepository mock = Mockito.mock(UserRepository.class);
        when(mock.findById(5)).thenReturn(Optional.of(user));
        when(mock.findById(100)).thenReturn(Optional.empty());
        when(mock.findAll(pageable)).thenReturn(new PageImpl<>(userList));
        when(mock.findExpensiveOrder()).thenReturn(userList);

       // when(mock.save(userWithNoId)).thenReturn(userWithNoId);


        CertificateRepository mockCertificate = Mockito.mock(CertificateRepository.class);
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("Test");
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        giftCertificates.add(giftCertificate);
        when(mockCertificate.findByName("Test")).thenReturn(giftCertificates);

        UserService userService = new UserServiceImpl(mock, mockCertificate);
        userService.setConverter(new UserConverter());
        BuyCertificatesRequestDTO buyCertificatesRequestDTO = new BuyCertificatesRequestDTO();



        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("George");




        Assertions.assertEquals(userService.getAll(pageable).getTotalElements(), userList.size());
        Assertions.assertEquals(userService.getUserWithMostExpensiveOrder().getName(), userList.get(0).getUsername());
       // Assertions.assertEquals(userService.create(userRequestDto).getName(), user.getUsername());
        Assertions.assertThrows(AppNotFoundException.class, () -> {
            userService.getUserById(100);
        });
        Assertions.assertEquals(userService.addCertificate(buyCertificatesRequestDTO, 5).getName(), "George");


    }
}
