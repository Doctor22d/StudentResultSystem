package com.example.Auth.Repository;

import com.example.Auth.model.Admin;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminJPARepo extends JpaRepository<Admin,String> {
    Optional<Admin> findByadminEmail(@Email(message = "Invalid Email") String adminEmail);
}
