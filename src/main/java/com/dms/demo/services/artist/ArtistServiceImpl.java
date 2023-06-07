package com.dms.demo.services.artist;

import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.models.entities.Artist;
import com.dms.demo.models.entities.Role;
import com.dms.demo.repositories.ArtistRepository;
import com.dms.demo.services.role.RoleService;
import com.dms.demo.services.utils.StringUtilsService;
import com.dms.demo.util.enums.UserRoles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StringUtilsService stringUtilsService;
    private final RoleService roleService;

    public ArtistServiceImpl(ArtistRepository artistRepository, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder, StringUtilsService stringUtilsService, RoleService roleService) {
        this.artistRepository = artistRepository;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.stringUtilsService = stringUtilsService;
        this.roleService = roleService;
    }

    @Override
    public ArtistRegisterRequestDTO artistRegisterRequest(ArtistRegisterRequestDTO artistRegisterRequestDTO) {
        Artist artistToBeRegistered = objectMapper.convertValue(artistRegisterRequestDTO, Artist.class);
        setArtistDetails(artistRegisterRequestDTO, artistToBeRegistered);
        artistRepository.save(artistToBeRegistered);
        return objectMapper.convertValue(artistToBeRegistered, ArtistRegisterRequestDTO.class);
    }

    private void setArtistDetails(ArtistRegisterRequestDTO artistRegisterRequestDTO, Artist artistToBeRegistered) {
        artistToBeRegistered.setCity(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getCity()));
        artistToBeRegistered.setEmail(artistRegisterRequestDTO.getEmail());
        artistToBeRegistered.setPassword(bCryptPasswordEncoder.encode(artistRegisterRequestDTO.getPassword()));
        artistToBeRegistered.setFirstName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getFirstName()));
        artistToBeRegistered.setStageName(artistRegisterRequestDTO.getStageName());
        Role role = roleService.createRole(UserRoles.ROLE_FREE_ARTIST);
        artistToBeRegistered.getArtistRoles().add(role);
        artistToBeRegistered.setAccountCreationDate(LocalDate.now());
    }

    @Override
    public ArtistLoginRequestDTO artistLogin(ArtistLoginRequestDTO artistLoginRequestDTO) {
        return null;
    }
}