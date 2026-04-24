package com.example.uploadResult.repository;

import com.example.uploadResult.model.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaRepo extends JpaRepository<StudentResult,String> {
    Optional<List<StudentResult>> findByRollNo(String rollNo);
}
