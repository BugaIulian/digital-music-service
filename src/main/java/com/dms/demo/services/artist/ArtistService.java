package com.dms.demo.services.artist;

import com.dms.demo.models.dto.ArtistDTO;
import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.util.enums.Gender;

import java.util.List;

public interface ArtistService {

    ArtistRegisterRequestDTO artistRegisterRequest(ArtistRegisterRequestDTO artistRegisterRequestDTO);

    ArtistLoginRequestDTO artistLoginRequest(ArtistLoginRequestDTO artistLoginRequestDTO);

    ArtistDTO updateArtistProfile(String id, ArtistDTO artistDTO);

    void deleteArtistById(String id);

    List<ArtistDTO> getArtists(String firstName, String city, Gender gender);
}