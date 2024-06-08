package com.davinci.spotifake.Model;

public class Song {

    private Long id;

    private String name;

    private String lyrics;

    private Genre genre;

    public Song(){

    }

    public Song(Long id, String name, String lyrics, Genre genre) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGender(Genre genre) {
        this.genre = genre;
    }
}
