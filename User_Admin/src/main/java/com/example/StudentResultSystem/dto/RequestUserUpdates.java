package com.example.StudentResultSystem.dto;

import com.example.StudentResultSystem.model.schoolName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUserUpdates {

    @Size(min = 3, message = "Roll number must be at least 3 characters")
    private String rollNo;

    private String name;

    @Email(message = "Invalid email format")
    private String email;
    private String courseName;

    private String batch;

    private schoolName schoolname;
}