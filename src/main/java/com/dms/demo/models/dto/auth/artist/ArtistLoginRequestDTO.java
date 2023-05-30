package com.dms.demo.models.dto.auth.artist;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistLoginRequestDTO {

    @NotBlank
    private String email;

    @Email
    private String password;
}