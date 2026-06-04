package com.codigo.spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aerolineaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aerolinea API")
                        .version("1.0.0")
                        .description("API REST desarrollada con Spring Boot para la gestion de aerolineas, aviones, vuelos, pilotos, pasajeros y boletos.")
                        .contact(new Contact()
                                .name("Fabrizio Allcca")));
    }
}
