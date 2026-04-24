package com.example.Auth.controller;

import com.example.Auth.DTO.UserRequest;
import com.example.Auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@Valid @RequestBody UserRequest userRequest){
        try{
            String res=userService.loginUser(userRequest);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}