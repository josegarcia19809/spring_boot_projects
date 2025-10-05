package com.example.boveda_datos.repositories;

import com.example.boveda_datos.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}

