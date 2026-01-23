package com.example.jpa_relationship.repositories;

import com.example.jpa_relationship.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
