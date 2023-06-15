package com.dms.demo.services.storage;

import com.dms.demo.util.enums.MusicGenre;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String uploadSongToGoogleCloud(MultipartFile multipartFile, String artistId, String songTitle, MusicGenre musicGenre) throws IOException;

    Resource getSongResource(String songId) throws IOException;
}