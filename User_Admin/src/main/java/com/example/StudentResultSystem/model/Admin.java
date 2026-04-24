package com.example.StudentResultSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "admin", uniqueConstraints = {
        @UniqueConstraint(name = "uk_admin_AdminEmail", columnNames = "AdminEmail")
}
)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String AdminID;
    private String AdminName;
    @Column(unique = true, nullable = false)
    private String adminEmail;
    @Column(nullable = false)
    private String password;
    @Transient
    private String confirmPassword;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @NotNull(message = "Contact Number is required")
    @Size(min = 10, message = "contact number is invalid")
    private String contactNumber;
}
