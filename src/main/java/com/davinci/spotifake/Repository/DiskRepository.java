package com.davinci.spotifake.Repository;

import com.davinci.spotifake.Model.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface DiskRepository extends JpaRepository<Disk, Long> {

    Disk findById(long id);
    List<Disk> findByName(String name);
    List<Disk> findByGenre(String genre);
    List<Disk> findByReleaseDate(Date releaseDate);
    List<Disk> findAll();
}