package com.davinci.spotifake.Repository;

import com.davinci.spotifake.Model.ArtistDisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtistDiskRepository extends JpaRepository<ArtistDisk, Long> {

    List<ArtistDisk> findByArtistId(Long artistId);

    List<ArtistDisk> findByDiskId(Long diskId);
}
