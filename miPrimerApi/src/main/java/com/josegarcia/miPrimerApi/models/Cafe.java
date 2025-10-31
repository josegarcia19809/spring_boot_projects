package com.josegarcia.miPrimerApi.models;

import lombok.Data;
import java.util.UUID;

@Data
public class Cafe {
    private final String id;
    private String nombre;
    private double precio;

    // Constructor que recibe id, nombre y precio
    public Cafe(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Constructor que genera automáticamente un id
    public Cafe(String nombre, double precio) {
        this(UUID.randomUUID().toString(), nombre, precio);
    }
}
