package com.josegarcia.miPrimerApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cafe {
    private String id;
    private String nombre;
    private double precio;

    // Constructor que recibe id, nombre y precio
    public Cafe(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
}
