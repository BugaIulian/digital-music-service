package com.dms.demo.controllers;

import com.dms.demo.services.artist.ArtistService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
}
