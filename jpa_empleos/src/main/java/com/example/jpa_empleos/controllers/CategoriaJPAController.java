package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa-categorias")
public class CategoriaJPAController {

    @Autowired
    private CategoriasJPARepository categoriasJPARepo;

    // 1. Obtener todas las categorías
    @GetMapping
    public List<Categoria> buscarTodas() {
        return categoriasJPARepo.findAll();
    }

    // 2. Obtener categorías ordenadas por nombre
    @GetMapping("/ordenadas")
    public List<Categoria> buscarTodasOrdenadas() {
        return categoriasJPARepo.findAll(
                Sort.by("nombre").descending()
        );
    }

    // 3. Obtener categorías con paginación
    @GetMapping("/paginadas")
    public Page<Categoria> buscarPaginadas(
            @RequestParam int pagina,
            @RequestParam int cantidad) {

        return categoriasJPARepo.findAll(
                PageRequest.of(pagina, cantidad)
        );
    }

    // 4. Obtener categorías con paginación y orden
    @GetMapping("/paginadas/ordenadas")
    public Page<Categoria> buscarPaginadasOrdenadas(
            @RequestParam int pagina,
            @RequestParam int cantidad) {

        return categoriasJPARepo.findAll(
                PageRequest.of(
                        pagina,
                        cantidad,
                        Sort.by("nombre").descending()
                )
        );
    }

    // 5. Eliminar todas las categorías
    @DeleteMapping("/todas")
    public void borrarTodas() {
        categoriasJPARepo.deleteAllInBatch();
    }
}
