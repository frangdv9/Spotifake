package com.davinci.spotifake.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "artist_disk")
public class ArtistDisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_artist", referencedColumnName = "id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "id_disk", referencedColumnName = "id")
    private Disk disk;

    public ArtistDisk() {
    }

    public ArtistDisk(Long id, Artist artist, Disk disk) {
        this.id = id;
        this.artist = artist;
        this.disk = disk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}

