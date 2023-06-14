package com.dms.demo.services.user;

import com.dms.demo.models.dto.ChangePasswordRequestDTO;
import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.user.UserLoginRequestDTO;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;
import com.dms.demo.util.enums.Gender;

import java.util.List;


public interface UserService {

    UserRegisterRequestDTO userRegisterRequest(UserRegisterRequestDTO userRegisterRequestDTO);

    UserLoginRequestDTO userLoginRequest(UserLoginRequestDTO userLoginRequestDTO);

    UserDTO updateUserProfile(String id, UserDTO userDTO);

    void deleteUserById(String id);

    List<UserDTO> getUsers(String firstName, String city, Gender gender);

    void requestPasswordChange(String id);

    void confirmPasswordChange(String userId, ChangePasswordRequestDTO changePasswordRequestDTO);
}