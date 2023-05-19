package com.dms.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ArtistDTO implements Serializable {

    @NotBlank
    private String artistFirstName;

    @NotBlank
    private String artistSecondName;

    @Past
    private LocalDate artistBirthDate;

    @NotBlank
    private String artistHomeTown;

    @NotBlank
    private long artistFollowers;
}