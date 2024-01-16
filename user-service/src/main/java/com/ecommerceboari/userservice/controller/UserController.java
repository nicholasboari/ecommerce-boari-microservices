package com.ecommerceboari.userservice.controller;

import com.ecommerceboari.userservice.dto.LoginRequestDTO;
import com.ecommerceboari.userservice.dto.SignUpRequestDTO;
import com.ecommerceboari.userservice.service.KeycloakService;
import com.ecommerceboari.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final KeycloakService keycloakService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(userService.signUpUser(signUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequestDTO request){
        AccessTokenResponse accessTokenResponse = keycloakService.loginWithKeycloak(request);
        return ResponseEntity.ok(accessTokenResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<String> infoUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        AccessToken.Access access = accessToken.getRealmAccess();
        Set<String> roles = access.getRoles();

        String role = roles.stream()
                .filter(s -> s.equals("ROLE_USER") || s.equals("ROLE_ADMIN"))
                .findAny()
                .orElse("noElement");

        return ResponseEntity.ok(role);
    }

    @GetMapping("/test")
    public String test(){
        return "sim";
    }
}
