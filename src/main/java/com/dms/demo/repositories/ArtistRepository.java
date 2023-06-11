package com.dms.demo.repositories;

import com.dms.demo.models.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {

    Artist findByEmail(String email);
}