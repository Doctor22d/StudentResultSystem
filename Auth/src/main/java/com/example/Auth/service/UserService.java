package com.example.Auth.service;

import com.example.Auth.DTO.UserRequest;
import com.example.Auth.Repository.UserJPARepo;
import com.example.Auth.Security.JwtUtils;
import com.example.Auth.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJPARepo userJPARepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwt;
    public String loginUser(UserRequest userRequest) {

        User user = userJPARepo.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        return jwt.generateToken(user.getUserid(), String.valueOf(user.getRole()));
    }
}
