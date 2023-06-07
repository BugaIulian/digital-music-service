package com.dms.demo.services.artist;

import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.models.entities.Artist;
import com.dms.demo.repositories.ArtistRepository;
import com.dms.demo.services.utils.StringUtilsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StringUtilsService stringUtilsService;

    public ArtistServiceImpl(ArtistRepository artistRepository, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder, StringUtilsService stringUtilsService) {
        this.artistRepository = artistRepository;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.stringUtilsService = stringUtilsService;
    }

    @Override
    public ArtistRegisterRequestDTO artistRegisterRequest(ArtistRegisterRequestDTO artistRegisterRequestDTO) {
        Artist artistToBeRegistered = objectMapper.convertValue(artistRegisterRequestDTO, Artist.class);
        artistToBeRegistered.setCity(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getCity()));
        artistToBeRegistered.setEmail(artistRegisterRequestDTO.getEmail());
        artistToBeRegistered.setPassword(bCryptPasswordEncoder.encode(artistRegisterRequestDTO.getPassword()));
        artistToBeRegistered.setFirstName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getFirstName()));
        artistToBeRegistered.setStageName(artistRegisterRequestDTO.getStageName());
        artistRepository.save(artistToBeRegistered);
        return objectMapper.convertValue(artistToBeRegistered, ArtistRegisterRequestDTO.class);
    }

    @Override
    public ArtistLoginRequestDTO artistLogin(ArtistLoginRequestDTO artistLoginRequestDTO) {
        return null;
    }
}