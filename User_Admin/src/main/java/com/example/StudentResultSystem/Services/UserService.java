package com.example.StudentResultSystem.Services;

import com.example.StudentResultSystem.Repository.userRepository;
import com.example.StudentResultSystem.dto.RequestUser;
import com.example.StudentResultSystem.dto.RequestUserUpdates;
import com.example.StudentResultSystem.dto.ResponseUser;
import com.example.StudentResultSystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseUser createUser(RequestUser request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .rollNo(request.getRollNo())
                .password(passwordEncoder.encode(request.getPassword()))
                .courseName(request.getCourseName())
                .batch(request.getBatch())
                .schoolname(request.getSchoolname())
                .build();

        return mapToResponse(userRepository.save(user));
    }

    public ResponseUser getUser(String rollNo) {
        return userRepository.findByRollNo(rollNo)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public ResponseUser updateUser(RequestUserUpdates request) {

        User user = userRepository.findByRollNo(request.getRollNo())
                .orElse(null);

        if (user == null) return null;

        if (request.getName() != null) user.setName(request.getName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getCourseName() != null) user.setCourseName(request.getCourseName());
        if (request.getBatch() != null) user.setBatch(request.getBatch());
        if (request.getSchoolname() != null) user.setSchoolname(request.getSchoolname());

        return mapToResponse(userRepository.save(user));
    }

    public boolean deleteUser(String rollNo) {
        return userRepository.findByRollNo(rollNo)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }

    private ResponseUser mapToResponse(User user) {
        ResponseUser res = new ResponseUser();
        res.setUserId(user.getUserid());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRollNo(user.getRollNo());
        res.setBatch(user.getBatch());
        res.setCourseName(user.getCourseName());
        res.setSchoolName(user.getSchoolname());
        res.setCreatedDate(user.getCreatedAt());
        res.setUpdatedDate(user.getUpdateAt());
        return res;
    }
}