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
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        Book book = new Book();
        book.setTitle("Java Developer");
        book.setIsbn("123456789");

        Author authorSaved = authorRepository.save(author);
        Book bookSaved = bookRepository.save(book);

        authorSaved.getBooks().add(bookSaved);
        authorRepository.save(authorSaved);

        // Segundo autor
        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Smith");

        // Segundo libro
        Book book2 = new Book();
        book2.setTitle("Spring Boot desde Cero");
        book2.setIsbn("987654321");

        Author author2Saved = authorRepository.save(author2);
        Book book2Saved = bookRepository.save(book2);

        author2Saved.getBooks().add(book2Saved);
        authorRepository.save(author2Saved);

        System.out.println("In BootstrapData");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Maria");
        publisher.setAddress("I Fabela");
        publisher.setCity("Atlacomulco");
        publisher.setState("México");
        publisher.setZip("12345");

        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());

    }
}
