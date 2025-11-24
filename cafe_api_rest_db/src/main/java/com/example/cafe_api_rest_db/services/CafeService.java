package com.example.cafe_api_rest_db.services;

// CafeService.java

import java.util.List;

import com.example.cafe_api_rest_db.models.Cafe;

public interface CafeService {
    List<Cafe> findAll();

    Cafe findById(Long id);

    Cafe save(Cafe cafe);

    Cafe update(Long id, Cafe cafe);

    void delete(Long id);
}

