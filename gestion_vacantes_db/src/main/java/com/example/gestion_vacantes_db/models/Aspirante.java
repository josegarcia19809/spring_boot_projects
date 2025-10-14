package com.example.gestion_vacantes_db.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aspirante extends Usuario {

    private String habilidades;
}
