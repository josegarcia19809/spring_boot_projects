package com.josegarcia.miPrimerApi.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class Cafe {
    @NotBlank(message = "Debes agregar un id")
    private String id;

    @NotBlank(message = "Debes agregar un nombre")
    private String nombre;

    // Usar Double (wrapper) para que pueda ser null si no viene en el JSON
    @NotNull(message = "Debes agregar un precio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    // Constructor que recibe id, nombre y precio
    public Cafe(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
}
