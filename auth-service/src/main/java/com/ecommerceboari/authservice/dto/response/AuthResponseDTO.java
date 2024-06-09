package com.ecommerceboari.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String id;
    private String name;
    private String email;
    private String token;
    private Instant createdAt;
}
