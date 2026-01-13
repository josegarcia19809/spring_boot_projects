package com.example.api_rest_crud.validation;

import com.example.api_rest_crud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation
        implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Evita el NullPointerException si falla la inyección
        if (userService == null) return true;
        return !userService.existsByUsername(username);
    }
}

