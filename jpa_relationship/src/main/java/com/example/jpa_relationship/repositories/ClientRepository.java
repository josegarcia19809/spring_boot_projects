package com.example.jpa_relationship.repositories;

import com.example.jpa_relationship.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
