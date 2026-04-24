package com.example.StudentResultSystem.dto;

import com.example.StudentResultSystem.Validations.PasswordMatchAdmin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@PasswordMatchAdmin
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAdmin {

    @NotNull(message = "Email is Required")
    @Email(message = "Email format is wrong")
    private String adminEmail;

    private String adminName;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 20, message = "password must be 8 and 20 character")
    private String password;

    @NotNull(message = "Confirm Password is required")
    @Size(min = 8, max = 20, message = "password must be 8 and 20 character")
    private String confirmPassword;

    @NotNull(message = "Contact Number is required")
    @Size(min = 10, message = "contact number is invalid")
    private String contactNumber;
}