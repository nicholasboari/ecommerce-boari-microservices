package com.ecommerceboari.userservice.service.impl;

import com.ecommerceboari.userservice.convertor.UserMapper;
import com.ecommerceboari.userservice.dto.KeycloakUser;
import com.ecommerceboari.userservice.dto.SignUpRequestDTO;
import com.ecommerceboari.userservice.entity.User;
import com.ecommerceboari.userservice.repository.UserRepository;
import com.ecommerceboari.userservice.service.KeycloakService;
import com.ecommerceboari.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    @Override
    public String signUpUser(SignUpRequestDTO signUpRequest) {

        KeycloakUser keycloakUser = new KeycloakUser();
        keycloakUser.setFirstName(signUpRequest.getName());
        keycloakUser.setLastName(signUpRequest.getSurname());
        keycloakUser.setEmail(signUpRequest.getEmail());
        keycloakUser.setPassword(signUpRequest.getPassword());
        keycloakUser.setRole("client_user");
        keycloakUser.setUsername(signUpRequest.getUsername());

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        if (status == 201) {
            User signUpUser = UserMapper.signUpRequestToUser(signUpRequest);
            signUpUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(signUpUser);
            return "Sign Up completed";
        }
        return "Not Register";
    }
}