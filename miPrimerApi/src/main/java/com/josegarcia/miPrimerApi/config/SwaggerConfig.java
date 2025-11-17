package com.josegarcia.miPrimerApi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .openapi("3.0.3") // usar 3.0.x en lugar de 3.1.0
                .info(new Info()
                        .title("API de Cafés")
                        .version("1.0")
                        .description("Documentación de la API para un CRUD de cafés"));
    }
}

