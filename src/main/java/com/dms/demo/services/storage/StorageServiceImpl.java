package com.dms.demo.services.storage;

import com.dms.demo.exceptions.artist.ArtistNotFoundException;
import com.dms.demo.exceptions.song.SongNotFoundException;
import com.dms.demo.models.entities.Artist;
import com.dms.demo.repositories.ArtistRepository;
import com.dms.demo.util.constants.Constants;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService {

    private final ArtistRepository artistRepository;

    @Value("${google.cloud.storage.key.path}")
    private String googleCloudStorageKeyPath;

    public StorageServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Storage getStorage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new ClassPathResource(googleCloudStorageKeyPath).getInputStream()
        );
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    @Override
    public String uploadSongToGoogleCloud(MultipartFile multipartFile, String artistId, String songTitle) throws IOException {
        Storage storage = getStorage();
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException("Artist not found to upload the song."));
        String artistFullName = artist.getFirstName() + " " + artist.getSecondName();
        Blob blob = storage.create(BlobInfo.newBuilder(Constants.GOOGLE_STORAGE_BUCKET, artistFullName + " - " + songTitle).build(), multipartFile.getInputStream());
        return blob.getMediaLink();
    }

    @Override
    public Resource getSongResource(String songId) throws IOException {
        Storage storage = getStorage();
        BlobId blobId = BlobId.of(Constants.GOOGLE_STORAGE_BUCKET, songId);
        Blob blob = storage.get(blobId);
        if (blob != null) {
            byte[] content = blob.getContent();
            return new ByteArrayResource(content);
        } else {
            throw new SongNotFoundException("Song not found!");
        }
    }
}