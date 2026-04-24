package com.example.Auth.Repository;

import com.example.Auth.model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJPARepo extends JpaRepository<User,String> {


    Optional<User> findByEmail(@Email(message = "invalid Email") String email);
}
