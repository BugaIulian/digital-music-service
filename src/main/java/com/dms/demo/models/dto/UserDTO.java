package com.dms.demo.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDTO implements Serializable {

    @NotBlank
    @Size(min = 2, max = 40, message = "First name must contain between 4 and 40 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40, message = "Second name must contain between 4 and 40 characters")
    private String secondName;

    @NotBlank
    @Email
    private String email;

    @Past(message = "DOB should be in the past")
    private LocalDate dob;

    @NotBlank
    private String interests;

    @NotBlank
    private String city;

}