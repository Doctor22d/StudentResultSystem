package com.example.Auth.controller;

import com.example.Auth.DTO.AdminRequest;
import com.example.Auth.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody AdminRequest adminRequest){
        try {
            String res=adminService.loginAdmin(adminRequest);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
