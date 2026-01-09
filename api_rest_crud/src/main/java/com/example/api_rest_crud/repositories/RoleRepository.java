package com.example.api_rest_crud.repositories;

import com.example.api_rest_crud.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
