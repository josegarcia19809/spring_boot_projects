package com.example.spring_6_webapp.bootstrap;

import com.example.spring_6_webapp.domain.Author;
import com.example.spring_6_webapp.domain.Book;
import com.example.spring_6_webapp.domain.Publisher;
import com.example.spring_6_webapp.repositories.AuthorRepository;
import com.example.spring_6_webapp.repositories.BookRepository;
import com.example.spring_6_webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

// ========================================
        // 1. Crear editorial
        // ========================================

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Pearson");
        publisher.setAddress("I Fabela");
        publisher.setCity("Atlacomulco");
        publisher.setState("México");
        publisher.setZip("12345");

        Publisher savedPublisher = publisherRepository.save(publisher);


        // ========================================
        // 2. Crear primer autor
        // ========================================

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        Author savedAuthor = authorRepository.save(author);


        // ========================================
        // 3. Crear primer libro
        // ========================================

        Book book = new Book();
        book.setTitle("Java Developer");
        book.setIsbn("123456789");
        book.setPublisher(savedPublisher);

        Book savedBook = bookRepository.save(book);


        // ========================================
        // 4. Relacionar autor y libro
        // ========================================

        savedAuthor.getBooks().add(savedBook);
        savedBook.getAuthors().add(savedAuthor);

        authorRepository.save(savedAuthor);
        bookRepository.save(savedBook);


        // ========================================
        // 5. Crear segundo autor
        // ========================================

        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Smith");

        Author savedAuthor2 = authorRepository.save(author2);


        // ========================================
        // 6. Crear segundo libro
        // ========================================

        Book book2 = new Book();
        book2.setTitle("Spring Boot desde Cero");
        book2.setIsbn("987654321");
        book2.setPublisher(savedPublisher);

        Book savedBook2 = bookRepository.save(book2);


        // ========================================
        // 7. Relacionar segundo autor y libro
        // ========================================

        savedAuthor2.getBooks().add(savedBook2);
        savedBook2.getAuthors().add(savedAuthor2);

        authorRepository.save(savedAuthor2);
        bookRepository.save(savedBook2);


        // ========================================
        // 8. Mostrar resultados
        // ========================================

        System.out.println("=================================");
        System.out.println("       DATOS INICIALES");
        System.out.println("=================================");

        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

        System.out.println("=================================");
    }
}

