package com.dms.demo.services.artist;

import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;

public interface ArtistService {

    ArtistRegisterRequestDTO artistRegister(ArtistRegisterRequestDTO artistRegisterRequestDTO);

    ArtistLoginRequestDTO artistLogin(ArtistLoginRequestDTO artistLoginRequestDTO);
}