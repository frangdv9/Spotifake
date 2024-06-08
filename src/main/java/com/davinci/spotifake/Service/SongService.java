package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Song;
import com.davinci.spotifake.Repository.SongRepository;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private final SongRepository repository;


    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public SongService() {
        this.repository = null;
    }

    public Song createSong(String name, Genre genre, String lyrics) {
        Song song = new Song(name, lyrics, genre);
        assert repository != null;
        return repository.save(song);
    }

    public void updateSong(Song song) {
        repository.save(song);
    }

    public Optional<Song> findSongById(long id) {
        assert repository != null;
        return repository.findById(id);
    }

    public List<Song> findSongsByName(String name) {
        return repository.findByName(name);
    }

    public List<Song> findSongsByLyrics(String lyrics) {
        return repository.findByLyrics(lyrics);
    }
}