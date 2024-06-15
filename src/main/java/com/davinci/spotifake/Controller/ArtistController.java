package com.davinci.spotifake.Controller;

import com.davinci.spotifake.Model.Artist;
import com.davinci.spotifake.Model.DTOs.ArtistDTO;
import com.davinci.spotifake.Model.DTOs.SongDTO;
import com.davinci.spotifake.Model.Song;
import com.davinci.spotifake.Service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}
