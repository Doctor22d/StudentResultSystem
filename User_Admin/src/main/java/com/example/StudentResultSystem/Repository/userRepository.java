package com.example.StudentResultSystem.Repository;

import com.example.StudentResultSystem.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User,String> {
    Optional<User> findByRollNo(String rollNo);

    Optional<User> findByEmail(String email);
}
