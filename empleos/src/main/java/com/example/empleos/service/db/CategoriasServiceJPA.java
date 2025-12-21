package com.example.empleos.service.db;

import com.example.empleos.models.Categoria;
import com.example.empleos.repository.CategoriasJPARepository;
import com.example.empleos.repository.VacantesRepository;
import com.example.empleos.service.ICategoriasService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriasServiceJPA implements ICategoriasService {

    private final VacantesRepository vacantesRepository;
    private CategoriasJPARepository categoriasRepository;

    public CategoriasServiceJPA(CategoriasJPARepository categoriasRepository, VacantesRepository vacantesRepository) {
        this.categoriasRepository = categoriasRepository;
        this.vacantesRepository = vacantesRepository;
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optionalCategoria = categoriasRepository.findById(idCategoria);
        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        categoriasRepository.deleteById(idCategoria);
    }

    @Override
    public Page<Categoria> buscarTodas(Pageable pageable) {
        return categoriasRepository.findAll(pageable);
    }
}
