package com.portfolio.my_portfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPortfolioBackendApplication.class, args);
		System.out.println("Servidor ejecutándose en el puerto 8080...");
	}

}
