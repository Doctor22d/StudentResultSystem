package com.example.uploadResult.controller;

import com.example.uploadResult.model.StudentResult;
import com.example.uploadResult.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/result")
@AllArgsConstructor
public class ResultController {  // ✅ Capitalized

    private final ResultService resultService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
        // ✅ Validate file is not empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }

        try {
            String uploadFile = resultService.uploadFile(file);
            return ResponseEntity.ok(uploadFile);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/get-result/{rollNo}")
    public ResponseEntity<?> getResult(@PathVariable String rollNo) {
        // ✅ Validate rollNo is not blank
        if (rollNo == null || rollNo.isBlank()) {
            return ResponseEntity.badRequest().body("Roll number cannot be empty.");
        }

        try {
            List<StudentResult> result = resultService.getResult(rollNo); // ✅ Fixed method name
            if (result == null || result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No result found for roll number: " + rollNo);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching result: " + e.getMessage());
        }
    }
}