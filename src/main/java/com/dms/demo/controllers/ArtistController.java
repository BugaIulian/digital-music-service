package com.dms.demo.controllers;

import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.services.artist.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/register")
    public ResponseEntity<ArtistRegisterRequestDTO> registerNewArtist(@RequestBody @Valid ArtistRegisterRequestDTO artistRegisterRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.artistRegisterRequest(artistRegisterRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ArtistLoginRequestDTO> artistLogin(@RequestBody @Valid ArtistLoginRequestDTO artistLoginRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(artistService.artistLoginRequest(artistLoginRequestDTO));
    }
}