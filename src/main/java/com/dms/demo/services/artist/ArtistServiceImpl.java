package com.dms.demo.services.artist;

import com.dms.demo.exceptions.artist.ArtistNotFoundException;
import com.dms.demo.models.dto.ArtistDTO;
import com.dms.demo.models.dto.auth.artist.ArtistLoginRequestDTO;
import com.dms.demo.models.dto.auth.artist.ArtistRegisterRequestDTO;
import com.dms.demo.models.entities.Artist;
import com.dms.demo.models.entities.Role;
import com.dms.demo.repositories.ArtistRepository;
import com.dms.demo.services.dalle.DalleService;
import com.dms.demo.services.role.RoleService;
import com.dms.demo.services.utils.StringUtilsService;
import com.dms.demo.util.enums.Gender;
import com.dms.demo.util.enums.RoleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StringUtilsService stringUtilsService;
    private final RoleService roleService;
    private final ArtistServiceValidations artistServiceValidations;
    private final DalleService dalleService;

    public ArtistServiceImpl(ArtistRepository artistRepository, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder, StringUtilsService stringUtilsService, RoleService roleService, ArtistServiceValidations artistServiceValidations, DalleService dalleService) {
        this.artistRepository = artistRepository;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.stringUtilsService = stringUtilsService;
        this.roleService = roleService;
        this.artistServiceValidations = artistServiceValidations;
        this.dalleService = dalleService;
    }

    @Override
    @Transactional
    public ArtistRegisterRequestDTO artistRegisterRequest(ArtistRegisterRequestDTO artistRegisterRequestDTO) {
        artistServiceValidations.validateArtistNotAlreadyRegistered(artistRegisterRequestDTO);
        Artist artistToBeRegistered = objectMapper.convertValue(artistRegisterRequestDTO, Artist.class);
        setArtistDetails(artistRegisterRequestDTO, artistToBeRegistered);
        artistRepository.save(artistToBeRegistered);
        return objectMapper.convertValue(artistToBeRegistered, ArtistRegisterRequestDTO.class);
    }


    @Override
    public ArtistLoginRequestDTO artistLoginRequest(ArtistLoginRequestDTO artistLoginRequestDTO) {
        Artist artistLogin = artistRepository.findByEmail(artistLoginRequestDTO.getEmail());
        artistServiceValidations.validateArtistNotFound(artistLogin);
        if (checkArtistPassword(artistLoginRequestDTO, artistLogin) && checkArtistEmail(artistLoginRequestDTO, artistLogin)) {
            return artistLoginRequestDTO;
        } else {
            throw new ArtistNotFoundException("Incorrect password, please try again!");
        }
    }

    @Override
    @Transactional
    public ArtistDTO updateArtistProfile(String id, ArtistDTO artistDTO) {
        Artist artistToBeUpdated = artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException("Artist to be updated could not be found."));
        updateArtistDetails(artistDTO, artistToBeUpdated);
        artistRepository.save(artistToBeUpdated);
        return objectMapper.convertValue(artistToBeUpdated, ArtistDTO.class);
    }


    @Override
    @Transactional
    public void deleteArtistById(String id) {

    }

    @Override
    public List<ArtistDTO> getArtists(String firstName, String city, Gender gender) {
        return null;
    }

    @Override
    public String createAlbumCover(String prompt) {
        return dalleService.createAlbumCover(prompt);
    }

    private boolean checkArtistEmail(ArtistLoginRequestDTO artistLoginRequestDTO, Artist artistLogin) {
        return artistLoginRequestDTO.getEmail().equals(artistLogin.getEmail());
    }

    private boolean checkArtistPassword(ArtistLoginRequestDTO artistLoginRequestDTO, Artist artistLogin) {
        return new BCryptPasswordEncoder().matches(artistLoginRequestDTO.getPassword(), artistLogin.getPassword());
    }

    private void updateArtistDetails(ArtistDTO artistDTO, Artist artistToBeUpdated) {
        artistToBeUpdated.setFirstName(artistDTO.getFirstName());
        artistToBeUpdated.setSecondName(artistDTO.getSecondName());
        artistToBeUpdated.setEmail(artistDTO.getEmail());
        artistToBeUpdated.setCity(artistDTO.getCity());
        artistToBeUpdated.setMusicGenre(artistDTO.getMusicGenre());
        artistToBeUpdated.setStageName(artistDTO.getStageName());
        artistToBeUpdated.setDob(artistDTO.getDob());
    }

    private void setArtistDetails(ArtistRegisterRequestDTO artistRegisterRequestDTO, Artist artistToBeRegistered) {
        artistToBeRegistered.setCity(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getCity()));
        artistToBeRegistered.setEmail(artistRegisterRequestDTO.getEmail());
        artistToBeRegistered.setPassword(bCryptPasswordEncoder.encode(artistRegisterRequestDTO.getPassword()));
        artistToBeRegistered.setFirstName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getFirstName()));
        artistToBeRegistered.setSecondName(stringUtilsService.capitalizeAndRemoveWhiteSpaces(artistRegisterRequestDTO.getSecondName()));
        artistToBeRegistered.setStageName(artistRegisterRequestDTO.getStageName());
        Role role = roleService.createRole(RoleType.ROLE_FREE_ARTIST);
        artistToBeRegistered.getArtistRoles().add(role);
        artistToBeRegistered.setAccountCreationDate(LocalDate.now());
    }
}