package com.davinci.spotifake.Repository;

import com.davinci.spotifake.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByName(String name);
    List<Song> findByLyrics(String lyrics);

    void update(Song song);
}
