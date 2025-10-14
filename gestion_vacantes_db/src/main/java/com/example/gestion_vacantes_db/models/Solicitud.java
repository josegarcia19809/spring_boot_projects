package com.example.gestion_vacantes_db.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación con Aspirante
    @ManyToOne
    @JoinColumn(name = "aspirante_id", nullable = false)
    private Aspirante aspirante;

    // Relación con Vacante
    @ManyToOne
    @JoinColumn(name = "vacante_id", nullable = false)
    private Vacante vacante;

    private LocalDateTime fechaSolicitud;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private String notaReclutador;

    private String cvAdjunto;

    private boolean activa;
}

