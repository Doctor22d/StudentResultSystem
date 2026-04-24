package com.example.Auth.service;

import com.example.Auth.DTO.AdminRequest;
import com.example.Auth.Repository.AdminJPARepo;
import com.example.Auth.Security.JwtUtils;
import com.example.Auth.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminJPARepo adminJPARepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwt;
    public String loginAdmin(AdminRequest adminRequest) {

        Admin admin = adminJPARepo.findByadminEmail(adminRequest.getAdminEmail())
                .orElseThrow(() -> new RuntimeException("Email is incorrect or not found"));

        if (!passwordEncoder.matches(adminRequest.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Wrong Password");
        }

        return jwt.generateToken(admin.getAdminID(), String.valueOf(admin.getRole()));
    }
}
