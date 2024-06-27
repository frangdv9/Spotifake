package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.DTOs.SongDTO;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Song;
import com.davinci.spotifake.Repository.DiskRepository;
import com.davinci.spotifake.Repository.SongRepository;
import com.davinci.spotifake.Service.ErrorHandler.ExceptionSpotifake;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository repository;

    private DiskRepository diskRepository;

    @Autowired
    public SongService(SongRepository repository, DiskRepository diskRepository) {

        this.repository = repository;
        this.diskRepository = diskRepository;
    }

    public SongService() {
        this.repository = null;
    }

    public Song createSong(SongDTO newSong) throws Exception {
        if (newSong == null || newSong.getLyrics() == null || newSong.getName() == null || newSong.getGenre() == null) {
            throw new BadRequestException("Los campos lyrics, name y genre son requeridos.");
        }
        try {
            Genre.valueOf(newSong.getGenre().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El género proporcionado no es válido.");
        }
        try {
            Long.parseLong(newSong.getDiskId());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El id del disco proporcionado no es válido.");
        }
        List<Song> existingSongs = repository.findByNameIgnoreCase(newSong.getName());
        if (!existingSongs.isEmpty()) {
            throw new BadRequestException("Ya existe una canción con el mismo nombre.");
        }
        Song song = new Song();
        song.setLyrics(newSong.getLyrics());
        song.setName(newSong.getName());
        song.setGenre(Genre.valueOf(newSong.getGenre().toUpperCase()));
        song.setDisk(diskRepository.getById(Long.parseLong(newSong.getDiskId())));
        return repository.save(song);
    }

    public void updateSong(Song song) throws Exception{
        if (song == null) {
            throw new BadRequestException("La canción proporcionada no puede ser nula.");
        }
        if (!repository.existsById(song.getId())) {
            throw ExceptionSpotifake.throwBadRequestException("La canción a actualizar no existe en la base de datos.");
        }

        repository.save(song);
    }

    public Optional<Song> findSongById(long id) throws Exception {
        if (id <= 0) {
            throw new BadRequestException("El ID de la canción debe ser mayor que cero.");
        }

        return repository.findById(id);
    }

    public List<Song> findSongsByName(String name) throws Exception {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("El nombre de la canción no puede ser nulo o vacío.");
        }

        return repository.findByNameIgnoreCase(name);
    }

    public List<Song> findSongsByLyrics(String lyrics) throws Exception{
        if (lyrics == null || lyrics.isEmpty()) {
            throw new BadRequestException("Las letras de la canción no pueden ser nulas o vacías.");
        }

        return repository.findByLyricsContainingIgnoreCase(lyrics);
    }
    public List<Song> findAllSongs() {
        return repository.findAll();
    }
}