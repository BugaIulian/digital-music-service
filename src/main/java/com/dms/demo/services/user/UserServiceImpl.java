package com.dms.demo.services.user;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.RegisterRequestDTO;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserServiceValidations userServiceValidations;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceValidations userServiceValidations) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userServiceValidations = userServiceValidations;
    }

    @Override
    @Transactional
    public RegisterRequestDTO registerUser(RegisterRequestDTO registerRequestDTO) {
        userServiceValidations.validateUserNotAlreadyRegistered(registerRequestDTO);
        User user = objectMapper.convertValue(registerRequestDTO, User.class);
        user.setUserAccountCreationDate(LocalDate.now());
        user.setEmail(registerRequestDTO.getEmail());
        String encryptedPassword = bCryptPasswordEncoder.encode(registerRequestDTO.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return objectMapper.convertValue(user, RegisterRequestDTO.class);
    }

    @Override
    @Transactional
    public UserDTO updateUserProfile(String id, UserDTO userDTO) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        updateUserDetails(updatedUser, userDTO);
        userRepository.save(updatedUser);
        return objectMapper.convertValue(updatedUser, UserDTO.class);
    }

    private void updateUserDetails(User updatedUser, UserDTO userDTO) {
        updatedUser.setUserInterests(userDTO.getUserInterests());
        updatedUser.setUserBirthDate(userDTO.getUserBirthDate());
        updatedUser.setUserFirstName(userDTO.getUserFirstName());
        updatedUser.setUserSecondName(userDTO.getUserSecondName());
        updatedUser.setEmail(userDTO.getEmail());
    }
}