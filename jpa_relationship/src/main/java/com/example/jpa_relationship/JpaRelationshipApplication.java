package com.example.jpa_relationship;

import com.example.jpa_relationship.entities.*;
import com.example.jpa_relationship.repositories.*;
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

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

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
//        mostrarClienteCompleto(6L);
//        removeInvoiceBidireccionalFindById();
//        System.out.println(".".repeat(20));
//        mostrarClienteCompleto(6L);
//        oneToOneClient();
//        oneToOneClientFindById();
//        mostrarClienteCompleto(1L);
//        addClientDetails(1L);
//        mostrarClienteCompleto(1L);
//        removeClientDetails(1L);
//        mostrarClienteCompleto(1L);

        //manyToManyStudentsExistentesCourses();
        manyToManyStudentsCoursesRemove();
        imprimirEstudiantesConCursos();
    }

    @Transactional
    public void manyToManyStudentsCoursesRemove() {
        Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(1L);
        if (studentOptionalDB.isPresent()) {
            Student studentDB = studentOptionalDB.get();
            Optional<Course> courseOptionalDB = courseRepository.findById(5L);
            if (courseOptionalDB.isPresent()) {
                Course courseDB = courseOptionalDB.get();
                studentDB.getCourses().remove(courseDB);

                studentRepository.save(studentDB);
            }
        }
    }

    @Transactional
    public void manyToManyStudentsExistentesCourses() {
        Optional<Student> studentOptional1 = studentRepository.findById(1L);
        Optional<Student> studentOptional2 = studentRepository.findById(2L);

        Student student1 = studentOptional1.orElse(null);
        Student student2 = studentOptional2.orElse(null);

        Course course1 = new Course("Swift Master", "Alfredo Molina");
        Course course2 = new Course("Kotlin Master", "Javier Molina");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course1));

        studentRepository.saveAll(List.of(student1, student2));
    }

    @Transactional
    public void manyToManyStudentsCourses() {
        Student student1 = new Student("Tony", "Stark");
        Student student2 = new Student("Bruce", "Banner");

        Course course1 = new Course("Java Master", "Alfredo Molina");
        Course course2 = new Course("Python Master", "Javier Molina");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course1));

        studentRepository.saveAll(List.of(student1, student2));
    }

    public void imprimirEstudiantesConCursos() {
        List<Student> students = studentRepository.findAllWithCourses();

        for (Student student : students) {
            System.out.println(student);
            student.getCourses().forEach(c -> System.out.println(" ".repeat(10) + c));
        }
    }


    @Transactional
    public void removeClientDetails(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow();

        client.removeDetails();

        clientRepository.save(client);
    }

    @Transactional
    public void addClientDetails(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow();

        ClientDetails details = new ClientDetails(true, 12500);
        client.setDetails(details);

        clientRepository.save(client);
    }


    @Transactional
    public void oneToOneClientFindById() {

        Optional<Client> clientOptional = clientRepository.findById(1L);
        clientOptional.ifPresent(client -> {
            ClientDetails clientDetails = new ClientDetails(false, 3500);
            clientDetails.setClient(client);
            clientDetailsRepository.save(clientDetails);
        });
    }

    @Transactional
    public void oneToOneClient() {
        Client client = new Client("Peter", "Parker");
        clientRepository.save(client);

        ClientDetails clientDetails = new ClientDetails(true, 2500);
        clientDetails.setClient(client);
        clientDetailsRepository.save(clientDetails);
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

            System.out.println("\n📄 DETALLES");
            System.out.println(client.getDetails());
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
