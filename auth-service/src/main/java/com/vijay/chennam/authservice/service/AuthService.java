package com.vijay.chennam.authservice.service;

import com.vijay.chennam.authservice.dto.AuthResponse;
import com.vijay.chennam.authservice.entity.UserCredential;
import com.vijay.chennam.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AuthResponse saveUser(UserCredential credential) {

        AuthResponse authResponse = new AuthResponse();
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);

        authResponse.setStatus("SUCCESS");
        authResponse.setMessage("User added successfully");
        return authResponse;

    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
