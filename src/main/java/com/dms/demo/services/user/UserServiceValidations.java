package com.dms.demo.services.user;

import com.dms.demo.exceptions.user.UserAlreadyExistsException;
import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidations {
    private final UserRepository userRepository;

    public UserServiceValidations(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void validateUserNotAlreadyRegistered(UserRegisterRequestDTO user) throws UserAlreadyExistsException {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with the same email is already registered.");
        }
    }

    public void validateUserNotFound(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException("User not found for login.");
        }
    }
}