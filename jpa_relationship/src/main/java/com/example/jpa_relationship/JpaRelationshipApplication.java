package com.example.jpa_relationship;

import com.example.jpa_relationship.entities.Address;
import com.example.jpa_relationship.entities.Client;
import com.example.jpa_relationship.entities.Invoice;
import com.example.jpa_relationship.repositories.ClientRepository;
import com.example.jpa_relationship.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication
public class JpaRelationshipApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // manyToOne();
        // AddFindByIdClient();
        // guardarClienteConDireccionesOneToMany();
        // agregarDireccionesAClienteExistenteOneToMany();
//        removeAddress();
//        oneToManyInvoiceBidireccional();
//        oneToManyInvoiceBidireccionalFindById();
//        agregarDireccionesAClienteExistenteOneToMany(6L);
        mostrarClienteCompleto(6L);
        removeInvoiceBidireccionalFindById();
        System.out.println(".".repeat(20));
        mostrarClienteCompleto(6L);
    }

    @Transactional
    public void removeInvoiceBidireccionalFindById() {
        Optional<Client> optionalClientDB = clientRepository.findOneWithInvoices(6L);
        optionalClientDB.ifPresent(client1 -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(10L);
            invoiceOptional.ifPresent(invoice -> {
                client1.getInvoices().remove(invoice);
                invoice.setClient(null);

                clientRepository.save(client1);
            });
        });
    }

    @Transactional
    public void mostrarClienteCompleto(Long id) {
        Optional<Client> optionalClient =
                clientRepository.findOneWithAddressesAndInvoices(id);

        optionalClient.ifPresent(client -> {
            System.out.println("👤 CLIENTE");
            System.out.println(client);

            System.out.println("\n📍 DIRECCIONES");
            client.getAddresses().forEach(address ->
                    System.out.println("  " + address)
            );

            System.out.println("\n🧾 FACTURAS");
            client.getInvoices().forEach(invoice ->
                    System.out.println("  " + invoice)
            );
        });
    }

    @Transactional
    public void oneToManyInvoiceBidireccionalFindById() {
        Optional<Client> optionalClient = clientRepository.findOneWithInvoices(6L);
        optionalClient.ifPresent(client1 -> {
            Invoice invoice1 = new Invoice("Compras de la escuela", 3000L);
            Invoice invoice2 = new Invoice("Compras del negocio", 3000L);

            client1.getInvoices().add(invoice1);
            client1.getInvoices().add(invoice2);

            invoice1.setClient(client1);
            invoice2.setClient(client1);

            clientRepository.save(client1);
        });
    }

    @Transactional
    public void oneToManyInvoiceBidireccional() {
        Client newClient = new Client("Rosa M", "Tijuana");

        Invoice invoice1 = new Invoice("Compras de la casa", 3000L);
        Invoice invoice2 = new Invoice("Compras de la oficina", 3000L);

        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoice1);
        invoices.add(invoice2);
        newClient.setInvoices(invoices);

        invoice1.setClient(newClient);
        invoice2.setClient(newClient);

        clientRepository.save(newClient);
    }


    @Transactional
    public void removeAddress(Long addressId) {
        Optional<Client> optionalClient = clientRepository.findById(4L);

        optionalClient.ifPresent(client -> {
            client.getAddresses()
                    .removeIf(address -> address.getId().equals(addressId));
        });
    }


    @Transactional
    public void guardarClienteConDireccionesOneToMany() {
        Client newClient = new Client("Carolina", "González");

        Address newAddress1 = new Address("Rio Grande", 1234);
        Address newAddress2 = new Address("Atlacomulco", 4321);

        newClient.addAddress(newAddress1);
        newClient.addAddress(newAddress2);

        clientRepository.save(newClient);
    }

    @Transactional
    public void agregarDireccionesAClienteExistenteOneToMany(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        optionalClient.ifPresent(client -> {
                    Address newAddress1 = new Address("San Felipe del Progreso", 1234);
                    Address newAddress2 = new Address("Atlacomulco", 4321);
                    client.addAddress(newAddress1);
                    client.addAddress(newAddress2);
                    clientRepository.save(client);
                }
        );
    }

    @Transactional
    public void manyToOne() {
        Client client = new Client("José", "García");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);
        Invoice invoiceDB = invoiceRepository.save(invoice);
        System.out.println(invoiceDB);
    }

    @Transactional
    public void AddFindByIdClient() {
        Optional<Client> clientOptional = clientRepository.findById(1L);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Invoice invoice = new Invoice("Papel", 200L);
            invoice.setClient(client);
            Invoice invoiceDB = invoiceRepository.save(invoice);
            System.out.println(invoiceDB);
        }
    }
}
