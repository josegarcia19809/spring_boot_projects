package com.josegarcia.miPrimerApi.controllers;

import com.josegarcia.miPrimerApi.models.Cafe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
