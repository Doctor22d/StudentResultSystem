package com.example.StudentResultSystem.controller;

import com.example.StudentResultSystem.Services.UserService;
import com.example.StudentResultSystem.dto.RequestUser;
import com.example.StudentResultSystem.dto.RequestUserUpdates;
import com.example.StudentResultSystem.dto.ResponseUser;
import com.example.StudentResultSystem.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser requestUser) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(requestUser));
    }

    @GetMapping("/showUser")
    public ResponseEntity<?> getUser(@RequestHeader("ROLLNO") String rollNo) {
        ResponseUser user = userService.getUser(rollNo);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<?> updateUser(@RequestBody RequestUserUpdates request) {
        ResponseUser user = userService.updateUser(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/removeUser/{rollNo}")
    public ResponseEntity<?> deleteUser(@PathVariable String rollNo){
        boolean deleted = userService.deleteUser(rollNo);

        if (!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        return ResponseEntity.ok("User Deleted Successfully");
    }
}