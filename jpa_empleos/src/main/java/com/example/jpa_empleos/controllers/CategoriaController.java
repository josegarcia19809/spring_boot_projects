package com.example.jpa_empleos.controllers;


import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriasRepository categoriasRepo;

    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    // GET /api/categorias
    @GetMapping
    public Iterable<Categoria> listar() {
        return categoriasRepo.findAll();
    }

    // GET /api/categorias/1
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {

        Optional<Categoria> categoria = categoriasRepo.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        }

        return ResponseEntity.notFound().build();
    }

    // POST /api/categorias
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {

        Categoria nuevaCategoria = categoriasRepo.save(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaCategoria);
    }

    // PUT /api/categorias/1
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Integer id,
            @RequestBody Categoria categoria) {

        Optional<Categoria> categoriaExistente =
                categoriasRepo.findById(id);

        if (categoriaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoriaActualizar = categoriaExistente.get();

        categoriaActualizar.setNombre(categoria.getNombre());
        categoriaActualizar.setDescripcion(categoria.getDescripcion());

        Categoria categoriaActualizada =
                categoriasRepo.save(categoriaActualizar);

        return ResponseEntity.ok(categoriaActualizada);
    }

    // DELETE /api/categorias/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (!categoriasRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        categoriasRepo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}