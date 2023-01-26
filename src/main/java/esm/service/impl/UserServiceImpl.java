package esm.service.impl;


import esm.converter.CertificateConverter;
import esm.converter.UserConverter;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserResponseDto;
import esm.exception.AppNotFoundException;
import esm.exception.ErrorCode;
import esm.model.GiftCertificate;
import esm.model.Tag;
import esm.model.User;
import esm.repository.CertificateRepository;
import esm.repository.UserRepository;
import esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final CertificateRepository certificateRepository;

    @Autowired
    private UserConverter converter;


    public UserServiceImpl(UserRepository userRepository, CertificateRepository certificateRepository) {
        this.userRepository = userRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {

        List<GiftCertificate> result = new ArrayList<>();

        for (int i = 0; i < user.getCertificates().size(); i++) {
            List<GiftCertificate> giftCertificates = certificateRepository.findByName(user.getCertificates().get(i).getName());
            if (!certificateRepository.findByName(user.getCertificates().get(i).getName()).isEmpty()) {
                result.addAll(giftCertificates);
            } else {
                GiftCertificate giftCertificate = user.getCertificates().get(i);
                giftCertificate.setCreateDate(LocalDateTime.now());
                result.add(giftCertificate);
            }

        }
        user.setCertificates(result);
        return converter.convertOneToDTO(userRepository.save(converter.convertDTOtoModel(user)));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return converter.convert(userRepository.findAll());
    }

    @Override
    public UserResponseDto addCertificate(String name, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new AppNotFoundException("User is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        User myUser = user.get();
        if (certificateRepository.findByName(name).isEmpty()) {
            throw new AppNotFoundException("Certificate is not found with this name " + name, ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        myUser.addGiftCertificate(certificateRepository.findByName(name));
        userRepository.save(myUser);


        Optional<User> responseDto = userRepository.findById(id);
        if (responseDto.isEmpty()) {
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        return converter.convertOneToDTO(responseDto.get());
    }

    @Override
    public UserResponseDto getUserById(int id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        return converter.convertOneToDTO(userOptional.get());
    }
}
