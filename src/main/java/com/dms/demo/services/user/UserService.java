package com.dms.demo.services.user;

import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.user.UserLoginRequestDTO;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;

import java.util.List;

public interface UserService {

    UserRegisterRequestDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO);

    UserLoginRequestDTO userLogin(UserLoginRequestDTO userLoginRequestDTO);

    UserDTO updateUserProfile(String id, UserDTO userDTO);

    void deleteUserById(String id);

    List<UserDTO> getUsers();

}