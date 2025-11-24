package com.example.cafe_api_rest_db.controllers;

import java.net.URI;
import java.util.List;

import com.example.cafe_api_rest_db.dto.ApiResponse;
import com.example.cafe_api_rest_db.models.Cafe;
import com.example.cafe_api_rest_db.services.CafeService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/cafes")
@CrossOrigin(origins = "*")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    // Obtener todos
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cafe>>> getAll() {
        List<Cafe> cafes = cafeService.findAll();
        ApiResponse<List<Cafe>> resp = new ApiResponse<>(
                200,
                "Lista de cafés obtenida correctamente",
                cafes,
                null
        );
        return ResponseEntity.ok(resp);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cafe>> getById(@PathVariable Long id) {
        // lanza ResourceNotFoundException si no existe
        Cafe cafe = cafeService.findById(id);
        ApiResponse<Cafe> resp = new ApiResponse<>(
                200,
                "Café encontrado",
                cafe,
                null
        );
        return ResponseEntity.ok(resp);
    }

    // Crear
    @PostMapping
    public ResponseEntity<ApiResponse<Cafe>> create(@Valid @RequestBody Cafe cafe) {
        Cafe creado = cafeService.save(cafe);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        ApiResponse<Cafe> resp = new ApiResponse<>(
                201,
                "Café creado correctamente",
                creado,
                null
        );

        return ResponseEntity.created(location).body(resp);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cafe>> update(@PathVariable Long id,
                                                    @Valid @RequestBody Cafe cafe) {
        // lanza ResourceNotFoundException si no existe
        Cafe actualizado = cafeService.update(id, cafe);
        ApiResponse<Cafe> resp = new ApiResponse<>(
                200,
                "Café actualizado correctamente",
                actualizado,
                null
        );
        return ResponseEntity.ok(resp);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        cafeService.delete(id); // lanza ResourceNotFoundException si no existe
        ApiResponse<Void> resp = new ApiResponse<>(
                204,
                "Café eliminado correctamente",
                null,
                null
        );
        // NoContent con cuerpo está permitido, pero algunos clientes
        // esperan cuerpo vacío. Ajusta si lo prefieres.
        return ResponseEntity.noContent().build();
        // Si prefieres devolver el ApiResponse con 200:
        // return ResponseEntity.ok(new ApiResponse<>(200,
        // "Café eliminado correctamente", null, null));
    }
}
