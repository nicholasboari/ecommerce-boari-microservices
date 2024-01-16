package com.ecommerceboari.userservice.service;

import com.ecommerceboari.userservice.dto.KeycloakUser;
import com.ecommerceboari.userservice.dto.LoginRequestDTO;
import com.ecommerceboari.userservice.dto.SignUpRequestDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {
    AccessTokenResponse loginWithKeycloak(LoginRequestDTO loginRequestDTO);
    int createUserWithKeycloak(KeycloakUser keycloakUser);
}
