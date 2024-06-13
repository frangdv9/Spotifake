package com.davinci.spotifake.Controller;

import com.davinci.spotifake.Model.DTOs.SongDTO;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Song;

import com.davinci.spotifake.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Map<String, Object> requestBody) {
        String name = requestBody.get("name").toString();
        String lyrics = requestBody.get("lyrics").toString();
        String genre = requestBody.get("genre").toString().toUpperCase();

        try {
            Song createdSong = songService.createSong(new SongDTO(name,lyrics,genre));
            return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Song> searchSongById(@PathVariable int id) {
        Optional<Song> foundSong = songService.findSongById(id);
        return foundSong.isPresent() ? new ResponseEntity<>(foundSong.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/songName/{name}")
    public ResponseEntity<List<Song>> searchSongByName(@PathVariable String name) {
        List<Song> foundSongs = songService.findSongsByName(name);

        return !foundSongs.isEmpty() ? new ResponseEntity<>(foundSongs, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/lyrics")
    public ResponseEntity<List<Song>> findSongsByLyrics(@RequestParam("lyrics") String lyrics) {
        List<Song> foundSongs = songService.findSongsByLyrics(lyrics);

        return !foundSongs.isEmpty() ? new ResponseEntity<>(foundSongs, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
