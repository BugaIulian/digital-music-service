package com.dms.demo.models.dto.auth.user;

import com.dms.demo.util.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequestDTO implements Serializable {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String city;

    @NotNull
    private Gender gender;
}