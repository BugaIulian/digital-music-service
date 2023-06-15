package com.dms.demo.models.dto;

import com.dms.demo.util.enums.MusicGenre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String city;

    @Email
    private String email;

    @NotNull
    private MusicGenre musicGenre;

    @NotBlank
    private String stageName;
}