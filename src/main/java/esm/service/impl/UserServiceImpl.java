package esm.service.impl;


import esm.converter.CertificateConverter;
import esm.converter.UserConverter;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.model.GiftCertificate;
import esm.model.Tag;
import esm.model.User;
import esm.repository.CertificateRepository;
import esm.repository.UserRepository;
import esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final CertificateRepository certificateRepository;

    @Autowired
    private UserConverter converter;


    public UserServiceImpl(UserRepository userRepository,CertificateRepository certificateRepository) {
        this.userRepository = userRepository;
        this.certificateRepository=certificateRepository;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        return converter.convertOneToDTO(userRepository.save(converter.convertDTOtoModel(user)));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return converter.convert(userRepository.findAll());
    }

    @Override
    public UserResponseDto addCertificate(String name, int id) {
        return null;
    }
}
