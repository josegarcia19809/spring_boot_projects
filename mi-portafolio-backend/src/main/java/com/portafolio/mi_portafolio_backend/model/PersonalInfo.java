package com.portafolio.mi_portafolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private Long id; // Clave primaria
    private String nombre;
    private String apellido;
    private String titulo; // Ej: "Desarrollador Full Stack"
    private String descripcionPerfil; // Texto largo de "¿Quién soy?"
    private String urlImagenPerfil; // URL o ruta a la imagen de perfil
    private Integer aniosExperiencia;
    private String correo;
    private String telefono;
    private String urlLinkedin;
    private String urlGithub;
}