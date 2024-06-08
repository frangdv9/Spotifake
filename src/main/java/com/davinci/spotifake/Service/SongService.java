package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.Genre;
import org.springframework.stereotype.Service;
import com.davinci.spotifake.Model.Song;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Song createSong(int id, String name, Genre genre, String lyrics) {
        Song song = new Song(id, name, genre, lyrics);
        return repository.save(song);
    }

    public void updateSong(Song song) {
        repository.update(song);
    }

    public Song findSongById(int id) {
        return repository.findById(id);
    }

    public List<Song> findSongsByName(String name) {
        return repository.findByName(name);
    }

    public List<Song> findSongsByLyrics(String lyrics) {
        return repository.findByLyrics(lyrics);
    }
}