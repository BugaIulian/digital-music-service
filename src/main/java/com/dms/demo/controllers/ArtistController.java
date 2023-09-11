package com.dms.demo.controllers;

import com.dms.demo.models.dto.ArtistDTO;
import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.services.artist.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated

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

    @PostMapping("/album-cover/{prompt}")
    public ResponseEntity<String> createAlbumCover(@PathVariable String prompt) {
        return ResponseEntity.ok(artistService.createAlbumCover(prompt));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ArtistDTO> updateArtistProfile(@PathVariable String id, @RequestBody @Valid
    ArtistDTO artistDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.updateArtistProfile(id, artistDTO));
    }
}