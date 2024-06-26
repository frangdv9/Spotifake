package com.davinci.spotifake.Repository;

import com.davinci.spotifake.Model.Artist;

import com.davinci.spotifake.Model.Genre;
import com.davinci.spotifake.Model.Instrument;
import com.davinci.spotifake.Model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    // - save()  findById() findAll() estos estan en jpa

    List<Artist> findByGenre(Genre genre);
    List<Artist> findByNationality(Nationality nationality);
    List<Artist> findByInstrument(Instrument instrument);

    @Query("SELECT DISTINCT a FROM Artist a WHERE a.instrument = :instrumentName")
    List<Artist> findByInstrumentName(@Param("instrumentName") String instrumentName);

    @Query("SELECT a FROM Artist a WHERE size(a.disks) = :numSongs")
    List<Artist> findByNumSongs(@Param("numSongs") int numSongs);

    @Query("SELECT a FROM Artist a WHERE a.deathDate IS NULL")
    List<Artist> findAliveArtists();

    @Query("SELECT a FROM Artist a WHERE a.deathDate IS NOT NULL")
    List<Artist> findDeceasedArtists();

    @Query(value = "SELECT * FROM artist WHERE YEAR(CURRENT_DATE()) - YEAR(birth_date) = :age OR (death_date IS NOT NULL AND YEAR(death_date) - YEAR(birth_date) = :age)", nativeQuery = true)
    List<Artist> findByAgeIncludingDeceased(@Param("age") int age);






}
