package com.dms.demo.services.user;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.dto.auth.RegisterRequestDTO;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidations {
    private final UserRepository userRepository;

    public UserServiceValidations(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void validateUserNotAlreadyRegistered(RegisterRequestDTO user) throws ValidationException {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new ValidationException("User with the same email is already registered.");
        }
    }
}