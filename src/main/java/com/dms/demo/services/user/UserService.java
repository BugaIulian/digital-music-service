package com.dms.demo.services.user;

import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.RegisterRequestDTO;

public interface UserService {

    RegisterRequestDTO registerUser(RegisterRequestDTO registerRequestDTO);

    UserDTO updateUserProfile(String id, UserDTO userDTO);
}