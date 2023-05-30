package com.dms.demo.models.dto.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequestDTO implements Serializable {

    @Email
    private String email;

    @NotBlank
    private String password;
}