package com.josegarcia.miPrimerApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.josegarcia.miPrimerApi.models.Cafe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestApiCafeController {
    private List<Cafe> coffees = new ArrayList<>();

    public RestApiCafeController() {
        coffees.addAll(List.of(
                new Cafe("85", "Café Cereza", 23.00),
                new Cafe("95", "Café Ganador", 25.00),
                new Cafe("15", "Café Lareño", 18.00),
                new Cafe("25", "Café Três Pontas", 31.00)
        ));
    }

    @GetMapping
    Iterable<Cafe> mostrarCafes() {
        return coffees;
    }

    @GetMapping("/{id}")
    Optional<Cafe> obtenerCafePorId(@PathVariable String id) {
        for (Cafe c : coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Cafe agregarCafe(@RequestBody Cafe nuevoCafe) {
        // Generar siempre un id único, sin importar si el cliente manda uno
        coffees.add(nuevoCafe);
        return nuevoCafe;
    }

    @PutMapping("/{id}")
    ResponseEntity<Cafe> actualizarCafe(@PathVariable String id, @RequestBody Cafe coffee) {
        int indiceCafeBuscado = -1;

        for (Cafe c : coffees) {
            if (c.getId().equals(id)) {
                indiceCafeBuscado = coffees.indexOf(c);
                coffee.setId(id);
                coffees.set(indiceCafeBuscado, coffee);
                break;
            }
        }

        return (indiceCafeBuscado == -1)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) // No se encontró el café
                : new ResponseEntity<>(coffee, HttpStatus.OK); // Café actualizado con éxito
    }

    @DeleteMapping("/{id}")
    void borrarCafe(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}