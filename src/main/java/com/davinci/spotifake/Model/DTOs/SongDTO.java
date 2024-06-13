package com.davinci.spotifake.Model.DTOs;

import com.davinci.spotifake.Model.Genre;

public class SongDTO {
    private String name;

    private String lyrics;

    private String genre;

    public SongDTO(){

    }



    public SongDTO( String name, String lyrics, String genre) {

        this.name = name;
        this.lyrics = lyrics;
        this.genre = genre;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    }




