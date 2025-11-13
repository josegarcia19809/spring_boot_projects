package com.josegarcia.miPrimerApi.controllers;

import com.josegarcia.miPrimerApi.models.Cafe;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/response-entity-cafes")
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

    @GetMapping
    public ResponseEntity<Iterable<Cafe>> getCoffees() {
        Iterable<Cafe> coffeeList = cafes;
        return ResponseEntity.ok(coffeeList);
    }

    @PostMapping
    public ResponseEntity<?> postCoffee(@RequestBody Cafe nuevoCafe) {
        if (nuevoCafe == null || nuevoCafe.getNombre() == null ||
                nuevoCafe.getNombre().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("⚠️ Error: el nombre del café no puede estar vacío.");
        }

        cafes.add(nuevoCafe);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCafe);
    }

    @GetMapping("/nombres")
    public ResponseEntity<String> obtenerDatos() {
        String nombresCafes = cafes.stream()
                .map(Cafe::getNombre)
                .collect(Collectors.joining(", "));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ValorPersonalizado");
        return new ResponseEntity<>(nombresCafes, headers, HttpStatus.OK);
    }

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

    @PutMapping("/{id}")
    ResponseEntity<Cafe> actualizarCafe(@PathVariable String id, @RequestBody Cafe coffee) {
        int indiceCafeBuscado = -1;

        for (Cafe c : cafes) {
            if (c.getId().equals(id)) {
                indiceCafeBuscado = cafes.indexOf(c);
                coffee.setId(id);
                cafes.set(indiceCafeBuscado, coffee);
                break;
            }
        }

        return (indiceCafeBuscado == -1)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) // No se encontró el café
                : new ResponseEntity<>(coffee, HttpStatus.OK); // Café actualizado con éxito
    }

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

