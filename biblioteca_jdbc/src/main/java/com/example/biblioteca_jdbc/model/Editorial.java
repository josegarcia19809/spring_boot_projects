package com.example.biblioteca_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
}