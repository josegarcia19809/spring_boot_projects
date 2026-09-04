package com.example.biblioteca_jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaJdbcApplication.class, args);
        System.out.println("Biblioteca JDBC Application started...");
    }

}
