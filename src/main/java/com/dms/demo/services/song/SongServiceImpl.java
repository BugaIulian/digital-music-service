package com.dms.demo.services.song;

import com.dms.demo.models.entities.Artist;
import com.dms.demo.models.entities.Song;
import com.dms.demo.repositories.SongRepository;
import com.dms.demo.util.enums.MusicGenre;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song createSong(String title, MusicGenre musicGenre, Artist artist) {
        Song song = new Song();
        song.setSongTitle(title);
        song.setArtist(artist);
        song.setMusicGenre(musicGenre);
        return songRepository.save(song);
    }
}