package com.example.Auth.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminRequest {
    @Email(message = "Invalid Email")
    private String adminEmail;
    @Size(min = 8)
    private String password;
}
