package com.example.globaljava.repositories;

import com.example.globaljava.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsername(String username);
}