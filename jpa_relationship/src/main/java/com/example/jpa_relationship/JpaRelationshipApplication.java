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

import java.util.Optional;

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
        removeAddress();
    }


    @Transactional
    public void removeAddress(){
        Optional<Client> optionalClient = clientRepository.findById(4L);
        optionalClient.ifPresent(client -> {
            client.removeAddress(client.getAddresses().get(0));
            clientRepository.save(client);
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
    public void agregarDireccionesAClienteExistenteOneToMany() {
        Optional<Client> optionalClient = clientRepository.findById(1L);
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
