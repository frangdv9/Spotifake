package com.davinci.spotifake.Model.DTOs;

import com.davinci.spotifake.Model.Disk;
import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Instrument;
import com.davinci.spotifake.Model.Nationality;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class ArtistDTO {

    private Genre genre;
    private Nationality nationality;
    private Date birthDate;
    private Date deathDate;
    private Instrument instrument;
    private String biography;
    private List<Disk> disks;

    ArtistDTO(){

    }

    public ArtistDTO(Genre genre, Nationality nationality, Date birthDate, Date deathDate, Instrument instrument, String biography, List<Disk> disks) {
        this.genre = genre;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.instrument = instrument;
        this.biography = biography;
        this.disks = disks;
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

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
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
}
