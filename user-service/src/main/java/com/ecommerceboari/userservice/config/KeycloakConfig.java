package com.ecommerceboari.userservice.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    public final static String serverUrl = "http://localhost:9090";
    public final static String realm = "ecommerce-boari-realm";
    public final static String clientId = "auth-service";
    public final static String clientSecret = "7noxL5c48wsXmeCxaqG3ZD2mseSuAhrK";
    final static String userName = "boari";
    final static String password = "boari";

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public static Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .username(userName)
                .password(password)
                .build();
    }
}
