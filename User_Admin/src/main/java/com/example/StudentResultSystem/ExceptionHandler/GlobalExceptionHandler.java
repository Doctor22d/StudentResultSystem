package com.example.StudentResultSystem.ExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex){
        Map<String,String> map=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->
                map.put(error.getField(),error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                map.put(error.getObjectName(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        String message = "Data integrity violation";
        String errorCode = "DATA_INTEGRITY_ERROR";

        Throwable root = ex.getRootCause();
        String dbMessage = root != null ? root.getMessage() : "";

        if (dbMessage != null) {

            if (dbMessage.contains("uk_user_email") || dbMessage.contains("uk_admin_AdminEmail")) {
                message = "Email already exists";
                errorCode = "EMAIL_ALREADY_EXISTS";
            }

            else if (dbMessage.contains("uk_user_rollno")) {
                message = "Roll number already exists";
                errorCode = "ROLLNO_ALREADY_EXISTS";
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("errorCode", errorCode);
        response.put("message", message);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
