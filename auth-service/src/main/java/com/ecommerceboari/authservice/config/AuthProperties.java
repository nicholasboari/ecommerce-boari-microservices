package com.ecommerceboari.authservice.config;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("auth")
@Data
public class AuthProperties {

    @NotBlank
    private String providedUri;
}
