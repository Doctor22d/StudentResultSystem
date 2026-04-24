package com.example.StudentResultSystem.Validations;

import com.example.StudentResultSystem.Validations.PasswordMatch;
import com.example.StudentResultSystem.dto.RequestUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidtor implements ConstraintValidator<PasswordMatch, RequestUser> {

    @Override
    public boolean isValid(RequestUser value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();

        // let @NotBlank / @NotNull handle empty cases
        if (password == null || confirmPassword == null) {
            return true;
        }

        return password.equals(confirmPassword);
    }
}
