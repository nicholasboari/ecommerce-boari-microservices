package com.ecommerceboari.authservice.service;

import com.ecommerceboari.authservice.dto.request.AuthRequestDTO;
import com.ecommerceboari.authservice.dto.response.AuthResponseDTO;
import com.ecommerceboari.authservice.entity.User;
import com.ecommerceboari.authservice.entity.enums.Role;
import com.ecommerceboari.authservice.jwt.JwtService;
import com.ecommerceboari.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByName(authRequestDTO.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);
        if (passwordEncoder.matches(CharBuffer.wrap(authRequestDTO.getPassword()), user.getPassword())) {
            AuthResponseDTO authResponseDTO = modelMapper.map(user, AuthResponseDTO.class);
            authResponseDTO.setToken(token);
            return authResponseDTO;
        }
        throw new RuntimeException("Invalid password");
    }

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        Optional<User> optionalUser = userRepository.findByName(authRequestDTO.getName());
        if (optionalUser.isPresent()) throw new RuntimeException("Login already exists");
        User user = modelMapper.map(authRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(authRequestDTO.getPassword())));
        user.setCreatedAt(Instant.now());
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);
        AuthResponseDTO authResponseDTO = modelMapper.map(savedUser, AuthResponseDTO.class);
        authResponseDTO.setToken(token);
        return authResponseDTO;
    }
}