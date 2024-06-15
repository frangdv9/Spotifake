package com.davinci.spotifake.Repository;

import com.davinci.spotifake.Model.Artist;
import com.davinci.spotifake.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {




}
