package com.dms.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePasswordRequestDTO implements Serializable {

    @NotBlank
    private String newPassword;

    @Pattern(regexp = "^[1-9]+$", message = "Invalid input. Only digits from 1 to 9 are allowed.")
    private Integer smsToken;
}