package com.dms.demo.models.dto.auth.artist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class ArtistLoginRequestDTO implements Serializable {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}