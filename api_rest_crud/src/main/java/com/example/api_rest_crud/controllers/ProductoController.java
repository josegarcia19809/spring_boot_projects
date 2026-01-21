package com.example.api_rest_crud.controllers;

import com.example.api_rest_crud.entities.Producto;
import com.example.api_rest_crud.services.ProductoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServiceI service;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Producto> list() {
        return service.buscarTodos();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Producto> productOptional = service.buscarPorId(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Producto product,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody Producto product,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Producto> productOptional = service.actualizar(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Producto> productOptional = service.borrar(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), "El campo " + fieldError.getField() +
                    " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
