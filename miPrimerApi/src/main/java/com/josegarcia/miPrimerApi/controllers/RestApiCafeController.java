package com.josegarcia.miPrimerApi.controllers;

import com.josegarcia.miPrimerApi.models.Cafe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                new Cafe("Café Cereza", 23.00),
                new Cafe("Café Ganador", 25.00),
                new Cafe("Café Lareño", 18.00),
                new Cafe("Café Três Pontas", 31.00)
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
        coffees.add(nuevoCafe);
        return nuevoCafe;
    }

    @PutMapping("/{id}")
    ResponseEntity<Cafe> actualizarCafe(@PathVariable String id, @RequestBody Cafe coffee) {
        int coffeeIndex = -1;

        for (Cafe c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
                break;
            }
        }

        return (coffeeIndex == -1)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) // No se encontró el café
                : new ResponseEntity<>(coffee, HttpStatus.OK); // Café actualizado con éxito
    }

    @DeleteMapping("/{id}")
    void borrarCafe(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}
