package com.dms.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ArtistDTO implements Serializable {

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @Past
    private LocalDate dob;

    @NotBlank
    private String homeTown;

    @NotBlank
    private long followers;
}