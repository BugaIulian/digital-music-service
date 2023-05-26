package com.dms.demo.models.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequestDTO implements Serializable {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}