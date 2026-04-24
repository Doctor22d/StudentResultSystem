package com.example.StudentResultSystem.dto;

import com.example.StudentResultSystem.model.schoolName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private String userId;
    private String rollNo;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String token;
    private String courseName;
    private schoolName schoolName;
    private String batch;
}
