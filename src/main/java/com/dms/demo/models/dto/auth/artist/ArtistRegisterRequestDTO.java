package com.dms.demo.models.dto.auth.artist;

import com.dms.demo.util.enums.MusicGenre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistRegisterRequestDTO {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String stageName;

    @NotBlank
    private MusicGenre musicGenre;
}