package com.portafolio.mi_portafolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiPortafolioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiPortafolioBackendApplication.class, args);
        System.out.println("Servidor en ejecución en puerto 8080...");
    }

}
