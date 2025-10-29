package com.example.music_api.controllers;

import com.example.music_api.models.Album;
import com.example.music_api.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/albums")
    public List<Album> buscarTodos() {
        return albumService.buscarTodos();
    }
}
