package com.example.boveda_datos.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documentos")
@Getter
@Setter
@NoArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    private String archivo;

    @Column(length = 500)
    private String descripcion;

    @NotBlank
    private String tipo; // Ej: "PDF", "Word", etc.

    private LocalDateTime fechaSubida = LocalDateTime.now();

    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
}

