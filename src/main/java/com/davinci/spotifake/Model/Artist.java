package com.davinci.spotifake.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Genre genre;

    private Nationality nationality;

    private Date birthDate;

    private Date deathDate;

    private Instrument instrument;

    private String biography;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Disk> disks;

    public Artist() {

    }

    public Artist(Long id,String name, Genre genre, Nationality nationality, Date birthDate, Date deathDate, Instrument instrument, String biography, List<Disk> disks) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.instrument = instrument;
        this.biography = biography;
        this.disks = disks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void setDisks(List<Disk> disks) {
        this.disks = disks;
    }

    public Date getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }
}
