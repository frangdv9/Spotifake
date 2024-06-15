package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.ArtistDisk;
import com.davinci.spotifake.Repository.ArtistDiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistDiskService {
    private final ArtistDiskRepository artistDiskRepository;

    @Autowired
    public ArtistDiskService(ArtistDiskRepository artistDiskRepository) {
        this.artistDiskRepository = artistDiskRepository;
    }
    public List<ArtistDisk> getAllArtistDisks() {
        return artistDiskRepository.findAll();
    }
    public ArtistDisk saveArtistDisk(ArtistDisk artistDisk) {
        return artistDiskRepository.save(artistDisk);
    }
}
