package com.josegarcia.miPrimerApi.controllers;

import com.josegarcia.miPrimerApi.models.Cafe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping("/coffees")
    Iterable<Cafe> mostrarCafes() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Cafe> obtenerCafePorId(@PathVariable String id) {
        for (Cafe c : coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Cafe AgregarCafe(@RequestBody Cafe nuevoCafe) {
        coffees.add(nuevoCafe);
        return nuevoCafe;
    }

    @PutMapping("/coffees/{id}")
    Cafe ActualizarCafe(@PathVariable String id, @RequestBody Cafe coffee) {
        int coffeeIndex = -1;

        for (Cafe c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
                break;
            }
        }

        if (coffeeIndex == -1) {
            return null;
        }

        return coffee;
    }
}
