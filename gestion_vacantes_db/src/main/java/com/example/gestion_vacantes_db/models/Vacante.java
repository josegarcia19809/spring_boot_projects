package com.example.gestion_vacantes_db.models;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación con Empleador (muchas vacantes para un empleador)
    @ManyToOne
    @JoinColumn(name = "empleador_id", nullable = false)
    private Empleador empleador;

    private String titulo;
    private String descripcion;
    private String requisitos;

    private String ubicacionId;

    @Enumerated(EnumType.STRING)
    private TipoTrabajo tipoTrabajo;

    private BigDecimal salario;
    private LocalDateTime fechaPublicacion;

    @Column(nullable = true)
    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    private EstadoVacante estado;
}

