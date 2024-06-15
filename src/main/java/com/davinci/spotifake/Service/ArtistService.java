package com.davinci.spotifake.Service;
import com.davinci.spotifake.Model.Artist;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Instrument;
import com.davinci.spotifake.Model.Nationality;
import com.davinci.spotifake.Repository.ArtistRepository;
import com.davinci.spotifake.Service.ErrorHandler.ExceptionSpotifake;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public Artist createArtist(Artist artist) throws Exception {
        validateArtist(artist);
        return repository.save(artist);
    }

    public void updateArtist(Artist artist) throws Exception {
        if (artist == null) {
            throw new BadRequestException("El artista proporcionado no puede ser nulo.");
        }
        if (!repository.existsById(artist.getId())) {
            throw ExceptionSpotifake.throwBadRequestException("El artista a actualizar no existe en la base de datos.");
        }
        repository.save(artist);
    }

    public Optional<Artist> findArtistById(long id) throws Exception {
        if (id <= 0) {
            throw new BadRequestException("El ID del artista debe ser mayor que cero.");
        }
        return repository.findById(id);
    }

    public List<Artist> findAllArtists() {
        return repository.findAll();
    }

    public List<Artist> findArtistsByGenre(Genre genre) {
        return repository.findByGenre(genre);
    }

    public List<Artist> findArtistsByNationality(Nationality nationality) {
        return repository.findByNationality(nationality);
    }

    public List<Artist> findArtistsByInstrument(Instrument instrument) {
        return repository.findByInstrument(instrument);
    }

    public List<Artist> findArtistsByNumSongs(int numSongs) {
        return repository.findByNumSongs(numSongs);
    }

    public List<Artist> findAliveArtists() {
        return repository.findAliveArtists();
    }

    public List<Artist> findDeceasedArtists() {
        return repository.findDeceasedArtists();
    }

    private void validateArtist(Artist artist) throws Exception {
        if (artist == null || artist.getBiography() == null || artist.getGenre() == null) {
            throw new BadRequestException("Los campos biography y genre son requeridos.");
        }
    }




}
