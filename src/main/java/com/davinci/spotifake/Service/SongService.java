package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.DTOs.SongDTO;
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

    public Song createSong(SongDTO newSong) {
        if (newSong == null) {
            throw new IllegalArgumentException("La canción proporcionada no puede ser nula.");
        }
        if (newSong.getLyrics() == null || newSong.getName() == null || newSong.getGenre() == null) {
            throw new IllegalArgumentException("Los campos lyrics, name y genre son requeridos.");
        }
        try {
            Genre.valueOf(newSong.getGenre().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El género proporcionado no es válido.");
        }
        List<Song> existingSongs = repository.findByName(newSong.getName());
        if (!existingSongs.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una canción con el mismo nombre.");
        }
        Song song = new Song();
        song.setLyrics(newSong.getLyrics());
        song.setName(newSong.getName());
        song.setGenre(Genre.valueOf(newSong.getGenre().toUpperCase()));

        return repository.save(song);
    }

    public void updateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("La canción proporcionada no puede ser nula.");
        }
        if (!repository.existsById(song.getId())) {
            throw new IllegalArgumentException("La canción a actualizar no existe en la base de datos.");
        }

        repository.save(song);
    }

    public Optional<Song> findSongById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la canción debe ser mayor que cero.");
        }

        return repository.findById(id);
    }

    public List<Song> findSongsByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la canción no puede ser nulo o vacío.");
        }

        return repository.findByName(name);
    }

    public List<Song> findSongsByLyrics(String lyrics) {
        if (lyrics == null || lyrics.isEmpty()) {
            throw new IllegalArgumentException("Las letras de la canción no pueden ser nulas o vacías.");
        }

        return repository.findByLyrics(lyrics);
    }
}