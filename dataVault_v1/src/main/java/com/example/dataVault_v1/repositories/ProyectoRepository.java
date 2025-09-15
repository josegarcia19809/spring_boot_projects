package com.example.dataVault_v1.repositories;

import com.example.dataVault_v1.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Aquí ya tienes métodos como save(), findById(), findAll(), deleteById() automáticamente.
}

