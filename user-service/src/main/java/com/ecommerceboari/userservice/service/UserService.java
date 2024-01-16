package com.ecommerceboari.userservice.service;

import com.ecommerceboari.userservice.dto.SignUpRequestDTO;

public interface UserService {
    String signUpUser(SignUpRequestDTO signUpRequestDTO);
}
