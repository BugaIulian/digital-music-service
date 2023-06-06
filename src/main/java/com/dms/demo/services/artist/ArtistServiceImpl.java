package com.dms.demo.services.artist;

import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Override
    public ArtistRegisterRequestDTO artistRegister(ArtistRegisterRequestDTO artistRegisterRequestDTO) {
        return null;
    }

    @Override
    public ArtistLoginRequestDTO artistLogin(ArtistLoginRequestDTO artistLoginRequestDTO) {
        return null;
    }
}