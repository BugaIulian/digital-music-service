package com.dms.demo.services.spotify;

import com.dms.demo.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistTablePopulator {

    @Autowired
    private ArtistRepository artistRepository;
}