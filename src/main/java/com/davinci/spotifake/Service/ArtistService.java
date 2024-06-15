package com.davinci.spotifake.Service;

import com.davinci.spotifake.Model.Artist;
import com.davinci.spotifake.Model.DTOs.ArtistDTO;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Instrument;
import com.davinci.spotifake.Model.Nationality;
import com.davinci.spotifake.Repository.ArtistRepository;
import com.davinci.spotifake.Service.ErrorHandler.ExceptionSpotifake;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    @Autowired
    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public Artist createArtist(ArtistDTO artistDTO) throws Exception {
        validateArtistDTO(artistDTO);
        Artist artist = convertToArtist(artistDTO);
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

    public List<Artist> findArtistsByGenre(String genreStr) throws BadRequestException {
        Genre genre = parseGenre(genreStr);
        return repository.findByGenre(genre);
    }

    public List<Artist> findArtistsByNationality(String nationalityStr) throws BadRequestException {
        Nationality nationality = parseNationality(nationalityStr);
        return repository.findByNationality(nationality);
    }

    public List<Artist> findArtistsByInstrument(List<String> instrumentStrs) throws BadRequestException {
        List<Instrument> instruments = instrumentStrs.stream()
                .map(this::parseInstrument)
                .collect(Collectors.toList());
        return repository.findByInstrumentIn(instruments);
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

    private Genre parseGenre(String genreStr) throws BadRequestException {
        try {
            return Genre.valueOf(genreStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El género proporcionado no es válido.");
        }
    }

    private Nationality parseNationality(String nationalityStr) throws BadRequestException {
        try {
            return Nationality.valueOf(nationalityStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("La nacionalidad proporcionada no es válida.");
        }
    }

    private Instrument parseInstrument(String instrumentStr) throws BadRequestException {
        try {
            return Instrument.valueOf(instrumentStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("El instrumento proporcionado no es válido.");
        }
    }

    private void validateArtistDTO(ArtistDTO artistDTO) throws Exception {
        if (artistDTO == null || artistDTO.getBiography() == null || artistDTO.getGenre() == null) {
            throw new BadRequestException("Los campos biography y genre son requeridos.");
        }
        parseDate(artistDTO.getBirthDate(), "yyyy-MM-dd", "La fecha de nacimiento proporcionada no es válida.");
        if (artistDTO.getDeathDate() != null) {
            parseDate(artistDTO.getDeathDate(), "yyyy-MM-dd", "La fecha de fallecimiento proporcionada no es válida.");
        }
    }

    private void parseDate(String dateStr, String format, String errorMessage) throws BadRequestException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new BadRequestException(errorMessage);
        }
    }

    private Artist convertToArtist(ArtistDTO artistDTO) throws BadRequestException {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artist.setBiography(artistDTO.getBiography());
        artist.setGenre(parseGenre(artistDTO.getGenre()));
        artist.setNationality(parseNationality(artistDTO.getNationality()));
        artist.setInstrument(parseInstrument(artistDTO.getInstrument()));
        artist.setBirthDate(parseDate(artistDTO.getBirthDate(), "yyyy-MM-dd"));
        if (artistDTO.getDeathDate() != null) {
            artist.setDeathDate(parseDate(artistDTO.getDeathDate(), "yyyy-MM-dd"));
        }
        return artist;
    }

    private Date parseDate(String dateStr, String format) throws BadRequestException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new BadRequestException("La fecha proporcionada no es válida.");
        }
    }
}