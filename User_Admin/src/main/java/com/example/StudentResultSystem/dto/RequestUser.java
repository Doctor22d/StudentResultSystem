package com.example.StudentResultSystem.dto;

import com.example.StudentResultSystem.Validations.PasswordMatch;
import com.example.StudentResultSystem.model.schoolName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@PasswordMatch(message = "password do not match")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    @NotBlank(message = "Roll Number is required")
    private String rollNo;
    private String name;
    @Valid
    @NotNull(message = "Email is required")
    @Email(message = "not valid format")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8,max = 20,message = "password must be 8 and 20 characters")
    private String password;
    @NotBlank
    @Size(min = 8,max = 20,message = "password must be 8 and 20 characters")
    private String confirmPassword;
    private String batch;
    @Enumerated(EnumType.STRING)
    private schoolName schoolname;
    private String courseName;

}
