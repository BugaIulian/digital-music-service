package com.dms.demo.services.user;

import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.exceptions.user.UserPasswordException;
import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.user.UserLoginRequestDTO;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;
import com.dms.demo.models.entities.User;
import com.dms.demo.repositories.UserRepository;
import com.dms.demo.services.email.EmailService;
import com.dms.demo.services.utils.StringUtilsService;
import com.dms.demo.util.enums.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserServiceValidations userServiceValidations;
    private final StringUtilsService stringUtilsService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceValidations userServiceValidations, StringUtilsService stringUtilsService, EmailService emailService) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userServiceValidations = userServiceValidations;
        this.stringUtilsService = stringUtilsService;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public UserRegisterRequestDTO registerUserRequest(UserRegisterRequestDTO userRegisterRequestDTO) {
        userServiceValidations.validateUserNotAlreadyRegistered(userRegisterRequestDTO);
        User user = objectMapper.convertValue(userRegisterRequestDTO, User.class);
        setUserDetails(userRegisterRequestDTO, user);
        userRepository.save(user);
        emailService.sendRegistrationEmail(userRegisterRequestDTO.getEmail(), userRegisterRequestDTO.getFirstName());
        return objectMapper.convertValue(user, UserRegisterRequestDTO.class);
    }

    private void setUserDetails(UserRegisterRequestDTO userRegisterRequestDTO, User user) {
        user.setUserAccountCreationDate(LocalDate.now());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setCity(userRegisterRequestDTO.getCity());
        user.setGender(userRegisterRequestDTO.getGender());
        String encryptedPassword = bCryptPasswordEncoder.encode(userRegisterRequestDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setFirstName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(userRegisterRequestDTO.getFirstName()));
    }

    @Override
    public UserLoginRequestDTO userLogin(UserLoginRequestDTO userLoginRequestDTO) {
        User userLogin = userRepository.findByEmail(userLoginRequestDTO.getEmail());
        userServiceValidations.validateUserNotFound(userLogin);
        if (checkUserPassword(userLoginRequestDTO, userLogin) && checkUserEmail(userLoginRequestDTO, userLogin)) {
            return userLoginRequestDTO;
        } else {
            throw new UserPasswordException("Incorrect password, please try again!");
        }
    }

    @Override
    @Transactional
    public UserDTO updateUserProfile(String id, UserDTO userDTO) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        updateUserDetails(updatedUser, userDTO);
        userRepository.save(updatedUser);
        return objectMapper.convertValue(updatedUser, UserDTO.class);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        User userToBeErased = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        userRepository.delete(userToBeErased);
    }

    @Override
    public List<UserDTO> getUsers(String firstName, String city, Gender gender) {

        if (firstName == null && city == null && gender == null) {
            List<User> userEntities = userRepository.findAll();
            List<UserDTO> allUsers = new ArrayList<>();
            userEntities.forEach(userEntity -> allUsers.add(objectMapper.convertValue(userEntity, UserDTO.class)));
            return allUsers;
        }

        if (firstName != null) {
            List<User> sameFirstNameEntities = userRepository.findAllByFirstName(firstName);
            List<UserDTO> allUsersWithTheSameFirstName = new ArrayList<>();
            sameFirstNameEntities.forEach(userEntity -> allUsersWithTheSameFirstName.add(objectMapper.convertValue(userEntity, UserDTO.class)));
            return allUsersWithTheSameFirstName;
        }

        if (city != null) {
            List<User> sameCityEntities = userRepository.findAllByCity(city);
            List<UserDTO> allUsersWithTheSameCity = new ArrayList<>();
            sameCityEntities.forEach(userEntity -> allUsersWithTheSameCity.add(objectMapper.convertValue(userEntity, UserDTO.class)));
            return allUsersWithTheSameCity;
        }

        if (gender != null) {
            List<User> sameGenderEntities = userRepository.findAllByGender(gender);
            List<UserDTO> allUserWithTheSameGender = new ArrayList<>();
            sameGenderEntities.forEach(userEntity -> allUserWithTheSameGender.add(objectMapper.convertValue(userEntity, UserDTO.class)));
            return allUserWithTheSameGender;
        }

        return Collections.emptyList();
    }

    private void updateUserDetails(User updatedUser, UserDTO userDTO) {
        updatedUser.setInterests(userDTO.getInterests());
        updatedUser.setDob(userDTO.getDob());
        updatedUser.setAge(LocalDate.now().getYear() - userDTO.getDob().getYear());
        updatedUser.setFirstName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(userDTO.getFirstName()));
        updatedUser.setSecondName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(userDTO.getSecondName()));
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setCity(stringUtilsService.capitalizeAndRemoveWhiteSpaces(userDTO.getCity()));
    }

    private boolean checkUserPassword(UserLoginRequestDTO userLoginRequestDTO, User userLogin) {
        return new BCryptPasswordEncoder().matches(userLoginRequestDTO.getPassword(), userLogin.getPassword());
    }

    private boolean checkUserEmail(UserLoginRequestDTO userLoginRequestDTO, User userLogin) {
        return userLoginRequestDTO.getEmail().equals(userLogin.getEmail());
    }
}
