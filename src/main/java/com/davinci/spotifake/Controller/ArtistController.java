package com.davinci.spotifake.Controller;

import com.davinci.spotifake.Model.Artist;
import com.davinci.spotifake.Model.DTOs.ArtistDTO;
import com.davinci.spotifake.Model.DTOs.SongDTO;
import com.davinci.spotifake.Model.Song;
import com.davinci.spotifake.Service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @PostMapping("/create")
    public ResponseEntity<Artist> createArtist(@RequestBody Map<String, Object> requestBody) {
        try {
            String name = requestBody.get("name").toString();
            String genre = requestBody.get("genre").toString();
            String nationality = requestBody.get("nationality").toString();
            String birthDate = requestBody.get("birthDate").toString();
            String deathDate = requestBody.get("deathDate").toString();
            String instrument = requestBody.get("instrument").toString();
            String biography = requestBody.get("biography").toString();

            ArtistDTO artistDTO = new ArtistDTO(name, genre, nationality, birthDate, deathDate, instrument, biography);

            Artist createdArtist = artistService.createArtist(artistDTO);

            return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Artist> searchArtistById(@PathVariable int id) throws Exception {
        Optional<Artist> artistFound = artistService.findArtistById(id);
        return artistFound.isPresent() ? new ResponseEntity<>(artistFound.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Artist>> searchArtistByGenre(@PathVariable String genre) throws Exception {
        List<Artist> foundArtists = artistService.findArtistsByGenre(genre);
        return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByNationality/{nationality}")
    public ResponseEntity<List<Artist>> searchArtistByNationality(@PathVariable String nationality) throws Exception {
        List<Artist> foundArtists = artistService.findArtistsByNationality(nationality);
        return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByInstruments")
    public ResponseEntity<List<Artist>> searchArtistsByInstruments(@RequestParam List<String> instruments) {
        try {
            List<Artist> foundArtists = artistService.findArtistsByInstrument(instruments);
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByAge/{age}")
    public ResponseEntity<List<Artist>> searchArtistsByAge(@PathVariable int age) {
        try {
            List<Artist> foundArtists = artistService.findArtistsByAge(age);
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByNumberOfSongs/{numberOfSongs}")
    public ResponseEntity<List<Artist>> searchArtistsByNumberOfSongs(@PathVariable int numberOfSongs) {
        try {
            List<Artist> foundArtists = artistService.findArtistsByNumSongs(numberOfSongs);
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findLivingArtists")
    public ResponseEntity<List<Artist>> findLivingArtists() {
        try {
            List<Artist> foundArtists = artistService.findAliveArtists();
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findDeceasedArtists")
    public ResponseEntity<List<Artist>> findDeceasedArtists() {
        try {
            List<Artist> foundArtists = artistService.findDeceasedArtists();
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Artist>> findAllArtists() {
        try {
            List<Artist> foundArtists = artistService.findAllArtists();
            return !foundArtists.isEmpty() ? new ResponseEntity<>(foundArtists, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
