package com.example.boveda_datos.repositories;

import com.example.boveda_datos.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}

