package com.ecommerceboari.authservice.repository;

import com.ecommerceboari.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(String name);
}
