package com.example.gestion_vacantes_db.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String correo;
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    private Role rol;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean activo;
}

