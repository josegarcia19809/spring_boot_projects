package com.example.jpa_relationship.repositories;

import com.example.jpa_relationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("select c from Client c left join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);

    // Mostrar cliente y sus registros asociados: facturas y direcciones
    @Query("""
    select distinct c 
    from Client c
    left join fetch c.addresses
    left join fetch c.invoices
    where c.id = ?1
""")
    Optional<Client> findOneWithAddressesAndInvoices(Long id);

    @Query("""
    select distinct c
    from Client c
    left join fetch c.addresses
    left join fetch c.invoices
    left join fetch c.details
    where c.id = ?1
""")
    Optional<Client> findOneWithAddressesInvoicesAndDetails(Long id);
}
