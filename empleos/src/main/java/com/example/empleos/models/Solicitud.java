package com.example.empleos.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Solicitudes",
        uniqueConstraints = {
                @UniqueConstraint(name = "Vacante_Usuario_UNIQUE",
                        columnNames = {"idVacante", "idUsuario"})
        })
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "archivo", nullable = false, length = 250)
    private String archivo;

    @Column(name = "comentarios", columnDefinition = "text")
    private String comentarios;

    public Solicitud() {
        this.fecha = LocalDate.now();
    }

    // FK a Vacante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVacante", nullable = false)
    private Vacante vacante;

    // FK a Usuario (quién postula)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
}

