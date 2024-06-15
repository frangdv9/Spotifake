package com.davinci.spotifake.Controller;

import com.davinci.spotifake.Model.Disk;
import com.davinci.spotifake.Model.DTOs.DiskDTO;
import com.davinci.spotifake.Service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/discos")
public class DiskController {

    private final DiskService diskService;

    @Autowired
    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearDisco(@RequestBody DiskDTO newDisk) {
        try {
            Disk createdDisk = diskService.createDisk(newDisk);
            return ResponseEntity.ok(createdDisk);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDiscoPorId(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(diskService.findDiskById(id)
                    .orElseThrow(() -> new Exception("Disco no encontrado con ID: " + id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> buscarDiscosPorNombre(@PathVariable("name") String name) {
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

    @GetMapping("/genero/{genre}")
    public ResponseEntity<?> buscarDiscosPorGenero(@PathVariable("genre") String genre) {
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

    @GetMapping("/fecha/{releaseDate}")
    public ResponseEntity<?> buscarDiscosPorFechaLanzamiento(@PathVariable("releaseDate") String releaseDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = dateFormat.parse(releaseDateStr);

            List<Disk> discs = diskService.findDisksByReleaseDate(releaseDate);
            if (discs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(discs);
        } catch (ParseException pe) {
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-MM-dd.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodosLosDiscos() {
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
