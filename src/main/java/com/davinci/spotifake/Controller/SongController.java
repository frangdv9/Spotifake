package com.davinci.spotifake.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongController {

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Map<String, Object> requestBody) {
        String name;
        String lyrics;
        String genre;
        try {
            name = requestBody.get("name").toString();
            lyrics = requestBody.get("lyrics").toString();
            genre = requestBody.get("genre").toString();
        }catch (Exception e){
            return new ResponseEntity<>(new Song(), HttpStatus.BAD_REQUEST);
        }
        Song createdSong = songService.createSong(name, lyrics,genre);
        if (createdSong!=null) {
            return new ResponseEntity<>(createdSong, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Song(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Song> searchSongById(@PathVariable String id){
        Song foundSong = songService.findSongById(id);
        if (foundSong!=null) {
            return new ResponseEntity<>(foundSong, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Song(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/songName/{name}")
    public ResponseEntity<Song> searchSongByName(@PathVariable String id){
        Song foundSong = songService.findSongByName(id);
        if (foundSong!=null) {
            return new ResponseEntity<>(foundSong, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Song(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/find/lyrics")
    public ResponseEntity<Song> createSong(@RequestBody Map<String, Object> requestBody) {
        String lyrics;
        try {
            lyrics = requestBody.get("lyrics").toString();
        }catch (Exception e){
            return new ResponseEntity<>(new Song(), HttpStatus.BAD_REQUEST);
        }
        Song foundSong = songService.findSongByLyrics(lyrics);
        if (foundSong!=null) {
            return new ResponseEntity<>(foundSong, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Song(), HttpStatus.BAD_REQUEST);
        }
    }

}
