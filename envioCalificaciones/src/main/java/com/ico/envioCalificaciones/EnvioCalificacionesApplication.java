package com.ico.envioCalificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnvioCalificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvioCalificacionesApplication.class, args);
		System.out.println("Ejecutando en puerto 8080...");
	}

}
