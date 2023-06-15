package com.dms.demo.services.song;

import com.dms.demo.models.entities.Artist;
import com.dms.demo.models.entities.Song;
import com.dms.demo.util.enums.MusicGenre;

public interface SongService {

    Song createSong(String title, MusicGenre musicGenre, Artist artist);
}