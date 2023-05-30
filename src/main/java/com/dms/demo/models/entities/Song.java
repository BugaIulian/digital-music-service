package com.dms.demo.models.entities;

import com.dms.demo.util.enums.MusicGenre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "song_title")
    private String songTitle;

    @Column(name = "song_genre")
    private MusicGenre musicGenre;

    @Column
    private int duration;

    @ManyToMany(mappedBy = "songs")
    private List<PlayList> playlists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}