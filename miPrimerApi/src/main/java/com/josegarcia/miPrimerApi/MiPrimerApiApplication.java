package com.josegarcia.miPrimerApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiPrimerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiPrimerApiApplication.class, args);
        System.out.println("Ejecutando en puerto 8080...");
	}
}
