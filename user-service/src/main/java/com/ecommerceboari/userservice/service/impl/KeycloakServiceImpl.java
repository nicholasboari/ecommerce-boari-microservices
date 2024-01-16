package com.ecommerceboari.userservice.service.impl;

import com.ecommerceboari.userservice.config.KeycloakConfig;
import com.ecommerceboari.userservice.dto.KeycloakUser;
import com.ecommerceboari.userservice.dto.LoginRequestDTO;
import com.ecommerceboari.userservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;

    @Override
    public AccessTokenResponse loginWithKeycloak(LoginRequestDTO loginRequestDTO) {
        Keycloak loginKeycloak = buildKeycloak(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        return loginKeycloak.tokenManager().getAccessToken();
    }

    private Keycloak buildKeycloak(String username, String password) {
        return KeycloakBuilder.builder()
                .realm(KeycloakConfig.realm)
                .serverUrl(KeycloakConfig.serverUrl)
                .clientId(KeycloakConfig.clientId)
                .clientSecret(KeycloakConfig.clientSecret)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public int createUserWithKeycloak(KeycloakUser keycloakUser) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());  // Defina o nome de usuário

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(KeycloakConfig.serverUrl)
                .realm(KeycloakConfig.realm)
                .username("boari")
                .password("boari")
                .clientId("auth-service")
                .clientSecret(KeycloakConfig.clientSecret)
                .build();

        UsersResource usersResource = keycloak.realm(KeycloakConfig.realm).users();

        keycloak.tokenManager().getAccessToken();

        Response response = usersResource.create(userRepresentation);
        System.out.println(response.getStatus());
        return response.getStatus();
    }
}
