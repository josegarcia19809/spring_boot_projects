package com.example.music_api.controllers;

import com.example.music_api.models.Album;
import com.example.music_api.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/albums")
    public List<Album> buscarTodos() {
        return albumService.buscarTodos();
    }

    @PostMapping("/albums")
    public Album guardar(@RequestBody Album album) {
        albumService.guardar(album);
        return album;
    }

    @PutMapping("/albums")
    public Album editar(@RequestBody Album album) {
        albumService.guardar(album);
        return album;
    }

    @DeleteMapping("/albums/{id}")
    public String eliminar(@PathVariable int id) {
        albumService.eliminar(id);
        return "Album eliminado exitosamente";
    }
}
