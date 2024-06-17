package com.ecommerceboari.authservice.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties("jks")
@Validated
public class JksProperties {

    @NotBlank
    private String keypass;

    @NotBlank
    private String storepass;

    @NotBlank
    private String alias;

    @NotBlank
    private String path;

}
