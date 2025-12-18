package com.example.empleos.repository;


import com.example.empleos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriasJPARepository extends JpaRepository<Categoria, Integer> {
}
