package com.josegarcia.miPrimerApi.controllers;

import com.josegarcia.miPrimerApi.models.Cafe;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/response-entity-cafes")
@Validated
public class ResponseEntityCoffee {
    private List<Cafe> cafes = new ArrayList<>();

    public ResponseEntityCoffee() {
        cafes.addAll(List.of(
                new Cafe("85", "Café Cereza", 23.00),
                new Cafe("95", "Café Ganador", 25.00),
                new Cafe("15", "Café Lareño", 18.00),
                new Cafe("25", "Café Nescafe", 31.00)
        ));
    }

    @Operation(summary = "Obtener todos los cafés", description = "Devuelve una lista con todos los cafés")
    @ApiResponse(responseCode = "200", description = "Lista devuelta exitosamente")

    @GetMapping
    public ResponseEntity<Iterable<Cafe>> getCoffees() {
        Iterable<Cafe> coffeeList = cafes;
        return ResponseEntity.ok(coffeeList);
    }

    @Operation(summary = "Agregar un café", description = "Agrega un café")
    @ApiResponse(responseCode = "CREATED", description = "Café creado exitosamente")
    @PostMapping
    public ResponseEntity<?> postCoffee(@Valid @RequestBody Cafe nuevoCafe, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            List<Map<String, Object>> errores = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> {
                        Map<String, Object> err = new HashMap<>();
                        err.put("campo", error.getField());
                        err.put("mensaje", error.getDefaultMessage());
                        err.put("valorRechazado", error.getRejectedValue());
                        return err;
                    })
                    .toList();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errores);
        }

        cafes.add(nuevoCafe);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCafe);
    }

    @Operation(summary = "Mostrar nombres de cafés", description = "Muestra solamente nombres de cafés")
    @ApiResponse(responseCode = "200", description = "Lista de nombres devuelta exitosamente")
    @GetMapping("/nombres")
    public ResponseEntity<String> obtenerDatos() {
        String nombresCafes = cafes.stream()
                .map(Cafe::getNombre)
                .collect(Collectors.joining(", "));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ValorPersonalizado");
        return new ResponseEntity<>(nombresCafes, headers, HttpStatus.OK);
    }

    @Operation(summary = "Buscar café por ID", description = "Muestra solamente el café según el ID mandado")
    @ApiResponse(responseCode = "200", description = "El café se devolvió exitosamente")
    @ApiResponse(responseCode = "Not Found", description = "No se encontró el café")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCoffeeById(@PathVariable String id) {
        for (Cafe c : cafes) {
            if (c.getId().equals(id)) {
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("⚠️ No se encontró ningún café con id: " + id);
    }

    @Operation(summary = "Actualizar café por ID", description = "Actualiza el café según el ID mandado")
    @ApiResponse(responseCode = "200", description = "El café se actualizó exitosamente")
    @ApiResponse(responseCode = "Not Found", description = "El café no se encontró.")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCafe(
            @PathVariable String id,
            @Valid @RequestBody Cafe coffee,
            BindingResult bindingResult) {

        // 1) Si hay errores de validación → devolver mensajes
        if (bindingResult.hasErrors()) {
            List<String> errores = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errores);
        }

        // 2) Buscar café por ID
        int indiceCafeBuscado = -1;

        for (Cafe c : cafes) {
            if (c.getId().equals(id)) {
                indiceCafeBuscado = cafes.indexOf(c);
                coffee.setId(id);              // Mantener ID original
                cafes.set(indiceCafeBuscado, coffee);
                break;
            }
        }

        // 3) Respuesta final
        return (indiceCafeBuscado == -1)
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Café no encontrado")
                : ResponseEntity.ok(coffee);
    }


    @Operation(summary = "Eliminar café por ID", description = "Elimina el café según el ID mandado")
    @ApiResponse(responseCode = "Not content", description = "El café se eliminó exitosamente. No devuelve contenido")
    @ApiResponse(responseCode = "Not Found", description = "El café no se encontró.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarCafe(@PathVariable String id) {
        boolean eliminado = cafes.removeIf(c -> c.getId().equals(id));

        if (eliminado) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // Eliminado correctamente
                    .body("☕ Café eliminado con éxito.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // No encontrado
                    .body("⚠️ No se encontró ningún café con id: " + id);
        }
    }
}

