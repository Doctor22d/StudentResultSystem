package com.example.StudentResultSystem.Validations;

import com.example.StudentResultSystem.dto.RequestAdmin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidatorAdmin implements ConstraintValidator<PasswordMatchAdmin,RequestAdmin> {

    @Override
    public boolean isValid(RequestAdmin requestAdmin, ConstraintValidatorContext constraintValidatorContext) {
        if (requestAdmin == null) {
            return true;
        }

        String password = requestAdmin.getPassword();
        String confirmPassword = requestAdmin.getConfirmPassword();

        // let @NotNull handle empty cases
        if (password == null || confirmPassword == null) {
            return true;
        }

        return password.equals(confirmPassword);
    }
}
