package com.example.StudentResultSystem.controller;


import com.example.StudentResultSystem.Services.AdminServices;
import com.example.StudentResultSystem.dto.RequestAdmin;
import com.example.StudentResultSystem.dto.ResponseAdmin;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServices adminService;

    @PostMapping("/AdminRegistration")
    public ResponseEntity<String> adminRegistration(
            @Valid @RequestBody RequestAdmin requestAdmin) {

        List<ResponseAdmin> admins = adminService.createAdmin(requestAdmin);

        if (admins.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no admins registered");
        }

        return ResponseEntity.ok("ADMIN REGISTERED SUCCESSFULLY");
    }

}
