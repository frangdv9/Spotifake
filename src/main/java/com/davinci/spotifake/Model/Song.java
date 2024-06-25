package com.davinci.spotifake.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "song_seq_gen", sequenceName = "SONGS_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    private String lyrics;

    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "disk_id")  // Name of the foreign key column in song table
    private Disk disk;
    
    public Song(){}

    public Song(String name, String lyrics, Genre genre) {
        this.name = name;
        this.lyrics = lyrics;
        this.genre = genre;
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

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setGender(Genre genre) {
        this.genre = genre;
    }
}
