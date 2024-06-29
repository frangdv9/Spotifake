package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.ArtistDisk;
import com.davinci.spotifake.Model.Disk;
import com.davinci.spotifake.Model.DTOs.DiskDTO;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Repository.ArtistRepository;
import com.davinci.spotifake.Repository.DiskRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiskService {

    private DiskRepository repository;
    private ArtistRepository artistRepository;


    @Autowired
    public DiskService(DiskRepository repository , ArtistRepository artistRepository) {
        this.repository = repository;
        this.artistRepository = artistRepository;
    }

    public Disk createDisk(DiskDTO newDisk) throws Exception {
        if (newDisk == null || newDisk.getName() == null || newDisk.getReleaseDate() == null || newDisk.getGenre() == null) {
            throw new BadRequestException("Los campos nombre, fecha de lanzamiento y géneros son requeridos.");
        }
        Genre genre;
        try {
            genre = Genre.valueOf(newDisk.getGenre().toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Uno o más géneros proporcionados no son válidos.");
        }
        try{
            Long.parseLong(newDisk.getArtistId());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El id Artista proporcionado no es válido.");
        }
        List<Disk> existingDisks = repository.findByName(newDisk.getName());
        if (!existingDisks.isEmpty()) {
            throw new BadRequestException("Ya existe un disco con el mismo nombre.");
        }
        if (!artistRepository.existsById(Long.parseLong(newDisk.getArtistId()))) {
            throw new BadRequestException("No existe un artista con ese id");
        }
        Disk disk = new Disk();
        disk.setName(newDisk.getName());
        disk.setReleaseDate(newDisk.getReleaseDate());
        disk.setGenre(genre);
        disk.setArtist(artistRepository.getById(Long.parseLong(newDisk.getArtistId())));

        return repository.save(disk);
    }

    public void updateDisk(Disk disk) throws Exception {
        if (disk == null) {
            throw new BadRequestException("El disco proporcionado no puede ser nulo.");
        }
        if (!repository.existsById(disk.getId())) {
            throw new BadRequestException("El disco a actualizar no existe en la base de datos.");
        }

        repository.save(disk);
    }

    public Optional<Disk> findDiskById(long id) throws Exception {
        if (id <= 0) {
            throw new BadRequestException("El ID del disco debe ser mayor que cero.");
        }

        return Optional.ofNullable(repository.findById(id));
    }

    public List<Disk> findDisksByName(String name) throws Exception {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("El nombre del disco no puede ser nulo o vacío.");
        }

        return repository.findByNameContainingIgnoreCase(name);
    }
    public List<Disk> findDisksByGenre(String genre) throws Exception {
        if (genre == null || genre.isEmpty()) {
            throw new BadRequestException("El género del disco no puede ser nulo o vacío.");
        }
        try {
            Genre genreEnum = Genre.valueOf(genre.toUpperCase());
            return repository.findByGenre(genreEnum);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El género del disco no es válido");
        }
    }

    public List<Disk> findDisksByReleaseDate(Date releaseDate) throws Exception {
        if (releaseDate == null) {
            throw new BadRequestException("La fecha de lanzamiento del disco no puede ser nula.");
        }
        return repository.findByReleaseDate(releaseDate);
    }


    public List<Disk> getAllDisks() {
        return repository.findAll();
    }
}
