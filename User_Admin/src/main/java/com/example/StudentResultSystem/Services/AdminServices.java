package com.example.StudentResultSystem.Services;


import com.example.StudentResultSystem.Repository.adminRepository;
import com.example.StudentResultSystem.dto.RequestAdmin;
import com.example.StudentResultSystem.dto.ResponseAdmin;
import com.example.StudentResultSystem.model.Admin;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServices {
    private final adminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    public List<ResponseAdmin> createAdmin(RequestAdmin requestAdmin) {

        Admin admin = Admin.builder()
                .adminEmail(requestAdmin.getAdminEmail())
                .AdminName(requestAdmin.getAdminName())
                .confirmPassword(requestAdmin.getConfirmPassword())
                .password(passwordEncoder.encode(requestAdmin.getPassword()))
                .contactNumber(requestAdmin.getContactNumber())
                .build();
        Admin savedAdmin = adminRepository.save(admin);

        return mapToResponse(savedAdmin);
    }

    private List<ResponseAdmin> mapToResponse(Admin admin) {

        List<ResponseAdmin> admins = new ArrayList<>();

        ResponseAdmin responseAdmin = new ResponseAdmin();
        responseAdmin.setContactNumber(admin.getContactNumber());
        responseAdmin.setAdminID(admin.getAdminID());
        responseAdmin.setAdminEmail(admin.getAdminEmail());
        responseAdmin.setAdminName(admin.getAdminName());
        responseAdmin.setCreateDate(admin.getCreatedDate());
        responseAdmin.setUpdateDate(admin.getUpdateDate());
        responseAdmin.setRole(admin.getRole());

        admins.add(responseAdmin);

        return admins;
    }
}
