package com.dms.demo.controllers;

import com.dms.demo.services.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final StorageService storageService;

    public SongController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload/{artistId}")
    public String uploadSong(@PathVariable String artistId, @RequestParam MultipartFile file, @RequestParam String songName) throws IOException {
        return storageService.uploadSongToGoogleCloud(file, artistId, songName);
    }

    @GetMapping("/play/{songId}")
    public ResponseEntity<Resource> playSong(@PathVariable String songId) throws IOException {
        Resource songResource = storageService.getSongResource(songId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename(songId + ".mp3")
                .build());
        return new ResponseEntity<>(songResource, headers, HttpStatus.OK);
    }
}