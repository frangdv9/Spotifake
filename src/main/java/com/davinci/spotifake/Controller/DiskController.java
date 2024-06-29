package com.davinci.spotifake.Controller;

import com.davinci.spotifake.Model.Disk;
import com.davinci.spotifake.Model.DTOs.DiskDTO;
import com.davinci.spotifake.Service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/disks")
public class DiskController {

    private final DiskService diskService;

    @Autowired
    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDisk(@RequestBody DiskDTO newDisk) {
        try {
            Disk createdDisk = diskService.createDisk(newDisk);
            return ResponseEntity.ok(createdDisk);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getDiskById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(diskService.findDiskById(id)
                    .orElseThrow(() -> new Exception("Disk not found with ID: " + id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchDisksByName(@PathVariable("name") String name) {
        try {
            List<Disk> discs = diskService.findDisksByName(name);
            if (discs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(discs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> searchDisksByGenre(@PathVariable("genre") String genre) {
        try {
            List<Disk> discs = diskService.findDisksByGenre(genre);
            if (discs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(discs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/release-date/{releaseDate}")
    public ResponseEntity<?> searchDisksByReleaseDate(@PathVariable("releaseDate") String releaseDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = dateFormat.parse(releaseDateStr);

            List<Disk> disks = diskService.findDisksByReleaseDate(releaseDate);
            if (disks.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron discos para la fecha especificada: " + releaseDateStr);
            }
            return ResponseEntity.ok(disks);
        } catch (ParseException pe) {
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/findAll")
    public ResponseEntity<?> getAllDisks() {
        try {
            List<Disk> discs = diskService.getAllDisks();
            if (discs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(discs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
