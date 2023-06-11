package com.dms.demo.services.artist;

import com.dms.demo.exceptions.artist.ArtistAlreadyExistsException;
import com.dms.demo.exceptions.artist.ArtistNotFoundException;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.models.entities.Artist;
import com.dms.demo.repositories.ArtistRepository;
import org.springframework.stereotype.Component;

@Component
public class ArtistServiceValidations {

    private final ArtistRepository artistRepository;

    public ArtistServiceValidations(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void validateArtistNotAlreadyRegistered(ArtistRegisterRequestDTO artist) throws ArtistAlreadyExistsException {
        Artist existingArtist = artistRepository.findByEmail(artist.getEmail());
        if (existingArtist != null) {
            throw new ArtistAlreadyExistsException("Artist with the same email is already registered.");
        }
    }

    public void validateArtistNotFound(Artist artist) throws ArtistNotFoundException {
        if (artist == null) {
            throw new ArtistNotFoundException("Artist not found for login.");
        }
    }
}