package com.example.api_rest_crud.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "no puede estar vacío")
    @Size(min = 1, max = 100, message = "debe tener entre 1 y 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "no puede ser nulo")
    @Min(value = 10, message = "debe ser mayor o igual a 10")
    @Column(nullable = false)
    private Integer precio;

    @NotBlank(message = "no puede estar vacío")
    @Column(nullable = false)
    private String descripcion;

    @NotBlank(message = "no puede estar vacío")
    @Column(nullable = false)
    private String imagen;
}
