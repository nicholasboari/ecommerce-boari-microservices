package org.example.authservice.repository;

import org.example.authservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

    Optional<UserCredential> findByName(String username);
}
