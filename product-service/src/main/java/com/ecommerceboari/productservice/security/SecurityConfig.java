package com.ecommerceboari.productservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()).disable())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("client_admin")

                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/brands/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/brands/**").hasRole("client_admin")

                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasRole("client_admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasRole("client_admin")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)
                        ));
        return http.build();
    }
}