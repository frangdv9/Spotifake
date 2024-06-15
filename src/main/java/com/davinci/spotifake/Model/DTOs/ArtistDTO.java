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

    private String name;
    private String genre;
    private String nationality;
    private String birthDate;
    private String deathDate;
    private String instrument;
    private String biography;
    private List<DiskDTO> disks;

    ArtistDTO(){

    }

    public ArtistDTO(String name, String genre, String nationality, String birthDate, String deathDate, String instrument, String biography) {
        this.name = name;
        this.genre = genre;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.instrument = instrument;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<DiskDTO> getDisks() {
        return disks;
    }

    public void setDisks(List<DiskDTO> disks) {
        this.disks = disks;
    }
}
