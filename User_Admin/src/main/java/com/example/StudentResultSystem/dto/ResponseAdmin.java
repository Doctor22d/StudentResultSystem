package com.example.StudentResultSystem.dto;

import com.example.StudentResultSystem.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAdmin {
    private String AdminID;
    private String AdminEmail;
    private String AdminName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime CreateDate;
    private LocalDateTime UpdateDate;
    private String token;
    private String contactNumber;

}
