package com.example.boveda_datos.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
@NoArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String empresa;

    @Column(length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Documento> documentos;
}

