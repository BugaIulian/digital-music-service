package com.dms.demo.models.dto.auth.artist;

import com.dms.demo.util.enums.MusicGenre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ArtistRegisterRequestDTO implements Serializable {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String stageName;

    @NotNull
    private MusicGenre musicGenre;

    @NotBlank
    private String city;
}