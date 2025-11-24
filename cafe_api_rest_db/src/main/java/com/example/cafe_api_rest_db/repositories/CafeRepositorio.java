package com.example.cafe_api_rest_db.repositories;

import com.example.cafe_api_rest_db.models.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepositorio extends JpaRepository<Cafe, Long> {

}

