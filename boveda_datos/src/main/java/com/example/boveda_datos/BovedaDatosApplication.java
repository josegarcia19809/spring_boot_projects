package com.example.boveda_datos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BovedaDatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BovedaDatosApplication.class, args);
        System.out.println("La aplicación Gestor de bóveda de datos inició en 8080...");
	}

}
