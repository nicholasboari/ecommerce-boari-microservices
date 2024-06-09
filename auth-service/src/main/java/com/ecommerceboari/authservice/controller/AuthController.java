package com.ecommerceboari.authservice.controller;

import com.ecommerceboari.authservice.dto.request.AuthRequestDTO;
import com.ecommerceboari.authservice.dto.response.AuthResponseDTO;
import com.ecommerceboari.authservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        LOGGER.info("Received request to login a user");
        AuthResponseDTO user = userService.login(authRequestDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        LOGGER.info("Received request to register a user");
        AuthResponseDTO authResponseDTO = userService.register(authRequestDTO);
        return ResponseEntity.created(URI.create("/users/" + authResponseDTO.getId())).body(authResponseDTO);
    }

    @GetMapping("/test")
    public String teste(){
        return "certo";
    }
}
