package com.ecommerceboari.authservice.config;

import com.ecommerceboari.authservice.entity.Type;
import com.ecommerceboari.authservice.entity.UserEntity;
import com.ecommerceboari.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FirstUserConfig implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(FirstUserConfig.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() != 0) {
            return;
        }

        logger.info("No existing users, inserting a new user.");

        userRepository.save(
                new UserEntity(
                        UUID.randomUUID().toString(),
                        "Nicholas Boari",
                        "nicholas@email.com",
                        passwordEncoder.encode("123456"),
                        Type.ADMIN,
                        Instant.now()
                )
        );
    }
}