package esm.service.impl;


import esm.converter.UserConverter;
import esm.dto.request.BuyCertificatesRequestDTO;
import esm.dto.request.UserRequestDto;
import esm.dto.response.UserInfoResponseDto;
import esm.dto.response.UserResponseDto;
import esm.exception.AppNotFoundException;
import esm.exception.BadRequestException;
import esm.exception.ErrorCode;
import esm.model.GiftCertificate;
import esm.model.User;
import esm.repository.CertificateRepository;
import esm.repository.RoleRepository;
import esm.repository.UserRepository;
import esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final CertificateRepository certificateRepository;


    @Autowired
    private UserConverter converter;
    @Autowired
    private RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, CertificateRepository certificateRepository) {
        this.userRepository = userRepository;
        this.certificateRepository = certificateRepository;
        this.roleRepository=roleRepository;

    }

    @Override
    public UserInfoResponseDto confirmUser(int id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new AppNotFoundException("User in cant be confirmed", ErrorCode.USER_NOT_FOUND);
        }
        User myUser = user.get();
        myUser.setRoles(Collections.singletonList(roleRepository.findByName("USER")));
        return converter.convertOneToInfoDTO(userRepository.save(myUser));
    }

    @Override
    public UserResponseDto getUserOrders(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        return converter.convertOneToDTO(userOptional.get());
    }

    @Override
    public void setConverter(UserConverter converter) {
        this.converter = converter;
    }

    @Override
    public UserInfoResponseDto create(UserRequestDto user) {

        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new BadRequestException("Login is used", ErrorCode.BAD_REQUEST_ERROR);

        }

        if (!user.getCertificates().isEmpty()) {
            List<GiftCertificate> result = new ArrayList<>();
            for (int i = 0; i < user.getCertificates().size(); i++) {
                List<GiftCertificate> giftCertificates = certificateRepository.findByName(user.getCertificates().get(i).getName());
                if (!certificateRepository.findByName(user.getCertificates().get(i).getName()).isEmpty()) {
                    result.addAll(giftCertificates);
                }
            }
            user.setCertificates(result);
        }


        return converter.convertOneToInfoDTO((userRepository.save(converter.convertDTOtoModel(user))));
    }

    @Override
    public Page<UserResponseDto> getAll(Pageable pageable) {
        return converter.convert(userRepository.findAll(pageable));
    }

    @Override
    public UserResponseDto addCertificate(BuyCertificatesRequestDTO name, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new AppNotFoundException("User is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        User myUser = user.get();
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        String[] certificates = name.getCertificates();

        for (String certificate : certificates) {
            if (certificateRepository.findByName(certificate).isEmpty()) {
                throw new AppNotFoundException("Certificate is not found with this name " + certificate, ErrorCode.CERTIFICATE_NOT_FOUND);
            } else {
                giftCertificates.addAll(certificateRepository.findByName(certificate));
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
    public UserInfoResponseDto getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new AppNotFoundException("User with this id is not found " + id, ErrorCode.USER_NOT_FOUND);
        }
        return converter.convertOneToInfoDTO(userOptional.get());
    }

    @Override
    public UserResponseDto getUserWithMostExpensiveOrder() {
        List<User> result = userRepository.findExpensiveOrder();
        result.removeAll(Collections.singletonList(null));
        List<User> res = result.stream().sorted(Comparator.comparingInt(User::getAllPrice).reversed()).toList();
        return converter.convertOneToDTO(res.get(0));

    }

    @Override
    public Page<UserInfoResponseDto> getUserInfo(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return converter.convertToInfo(users);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new AppNotFoundException("Username is not found", ErrorCode.USER_NOT_FOUND);
        }

        return user;
    }
}
