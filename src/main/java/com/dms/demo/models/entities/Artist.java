package com.dms.demo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @Column(name = "artist_id", length = 26)
    private String id;

}