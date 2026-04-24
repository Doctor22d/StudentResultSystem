package com.example.Auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Builder
@Table(
        name = "user_Data",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_user_rollno", columnNames = "rollNo")
        }
)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userid;
    @Column(unique = true,nullable = false)
    private String rollNo;
    private String name;
    @Column(nullable = false,unique = true)
    @Email(message = "invalid Email")
    private String email;
    @Size(min=8)
    private String password;
    @Transient
    private String confirmPassword;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Enumerated(EnumType.STRING)
    private schoolName schoolname;
    private String courseName;
    private String batch;
}
