package com.example.empleos.service.db;

import com.example.empleos.models.Categoria;
import com.example.empleos.repository.CategoriasRepository;
import com.example.empleos.service.ICategoriasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasServiceJPA implements ICategoriasService {

    private CategoriasRepository categoriasRepository;

    public CategoriasServiceJPA(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        //return ((List<Categoria>) categoriasRepository).findAll();
        return List.of();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optionalCategoria = categoriasRepository.findById(idCategoria);
        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }
        return null;
    }
}
