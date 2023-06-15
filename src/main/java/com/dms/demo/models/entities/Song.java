package com.dms.demo.models.entities;

import com.dms.demo.models.datamapping.genre.GenreConverter;
import com.dms.demo.util.enums.MusicGenre;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
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
    @Convert(converter = GenreConverter.class)
    private MusicGenre musicGenre;

    @Column
    private Duration duration;

    @ManyToMany(mappedBy = "songs")
    private List<PlayList> playlists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Song() {
        this.id = new ULID().nextULID();
    }
}