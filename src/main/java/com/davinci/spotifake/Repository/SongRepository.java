package com.davinci.spotifake.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByName(String name);
    List<Song> findByLyricsContaining(String lyrics);
}