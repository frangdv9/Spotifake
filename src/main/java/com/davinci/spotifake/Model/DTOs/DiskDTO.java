package com.davinci.spotifake.Model.DTOs;

import java.util.Date;
import java.util.List;

public class DiskDTO {

    private String name;

    private Date releaseDate;

    private String genre;

    private List<SongDTO> songs;

    private String artistId;

    public DiskDTO() {
    }

    public DiskDTO(String name, Date releaseDate, String genre, List<SongDTO> songs, String artistId) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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