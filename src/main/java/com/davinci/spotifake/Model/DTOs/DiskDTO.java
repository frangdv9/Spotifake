package com.davinci.spotifake.Model.DTOs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiskDTO {

    private String name;

    private String releaseDate;

    private String genre;

    private List<SongDTO> songs;

    private String artistId;

    public DiskDTO() {
    }

    public DiskDTO(String name, String releaseDate, String genre, List<SongDTO> songs, String artistId) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
        this.artistId = artistId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public Date getFormattedReleaseDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(this.releaseDate);
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }
}