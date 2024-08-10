package com.vijay.chennam.authservice.repository;

import com.vijay.chennam.authservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

    Optional<UserCredential> findByName(String username);
}
