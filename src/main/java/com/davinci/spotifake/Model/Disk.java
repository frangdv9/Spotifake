package com.davinci.spotifake.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "disk")
public class Disk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date releaseDate;

    @ManyToOne()
    @JsonIgnore
    private Artist artist;
    @OneToMany(mappedBy = "disk", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Genre genre;



    public Disk(){
        
    }

    public Disk(Long id, String name, Date releaseDate, List<Song> songs, Genre genre) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.songs = songs;
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
