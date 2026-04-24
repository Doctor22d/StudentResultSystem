package com.example.uploadResult.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String resultId;
    private String rollNo;
    private String subject;
    private String cgpa;
    private String grade;
}
