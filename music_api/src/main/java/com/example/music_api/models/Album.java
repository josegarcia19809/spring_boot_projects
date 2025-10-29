package com.example.music_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Date;

@Entity
@Table(name = "Albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lanzado;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false, length = 150)
    private String genero;
}

