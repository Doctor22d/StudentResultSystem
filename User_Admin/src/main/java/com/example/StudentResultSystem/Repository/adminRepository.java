package com.example.StudentResultSystem.Repository;

import com.example.StudentResultSystem.model.Admin;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface adminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByAdminEmail(String adminEmail);
}
