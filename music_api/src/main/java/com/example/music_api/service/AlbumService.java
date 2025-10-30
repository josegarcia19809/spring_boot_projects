package com.example.music_api.service;

import com.example.music_api.models.Album;
import com.example.music_api.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;

    @Override
    public List<Album> buscarTodos() {
        return albumRepository.findAll();
    }

    @Override
    public void guardar(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void eliminar(int idAlbum) {
        albumRepository.deleteById(idAlbum);
    }
}
