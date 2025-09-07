package com.josegarcia.refacciones.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor      // Genera un constructor vacío
@AllArgsConstructor     // Genera un constructor con todos los campos
public class Refaccion {

    private int id;
    private String nombre_refaccion;
    private String categoria;
    private int anio_fabricacion;
    private float precio_unitario;
}

