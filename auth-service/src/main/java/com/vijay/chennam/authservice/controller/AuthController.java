package com.vijay.chennam.authservice.controller;

import com.vijay.chennam.authservice.dto.AuthResponse;
import com.vijay.chennam.authservice.service.AuthService;
import com.vijay.chennam.authservice.entity.UserCredential;
import com.vijay.chennam.authservice.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public AuthResponse addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public AuthResponse validateToken(@RequestParam("token") String token) {

        service.validateToken(token);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus("SUCCESS");
        authResponse.setMessage("User successfully logged in");
        return authResponse;
    }
}
