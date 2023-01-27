package esm.service.impl;


import esm.converter.CertificateConverter;
import esm.converter.UserConverter;
import esm.dto.request.BuyCertificatesDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            }
        }
        if (result.isEmpty()) {
            throw new AppNotFoundException("Certificates is not found", ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        user.setCertificates(result);
        return converter.convertOneToDTO(userRepository.save(converter.convertDTOtoModel(user)));
    }

    @Override
    public Page<UserResponseDto> getAll(Pageable pageable) {
        return converter.convert(userRepository.findAll(pageable));
    }

    @Override
    public UserResponseDto addCertificate(BuyCertificatesDto name, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new AppNotFoundException("User is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        User myUser = user.get();
        List<GiftCertificate> giftCertificates = new ArrayList<>();

        for (int i = 0; i < name.getGiftCertificates().size(); i++) {
            if (certificateRepository.findByName(name.getGiftCertificates().get(i)).isEmpty()) {
                throw new AppNotFoundException("Certificate is not found with this name " + name.getGiftCertificates().get(i), ErrorCode.CERTIFICATE_NOT_FOUND);
            } else {
                giftCertificates.addAll(certificateRepository.findByName(name.getGiftCertificates().get(i)));
            }

        }


        myUser.addOrder(converter.getOrder(giftCertificates));

        userRepository.save(myUser);


        Optional<User> responseDto = userRepository.findById(id);

        if (responseDto.isEmpty()) {
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }

        return converter.convertOneToDTO(responseDto.get());
    }

    @Override
    public UserResponseDto getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        return converter.convertOneToDTO(userOptional.get());
    }
}
