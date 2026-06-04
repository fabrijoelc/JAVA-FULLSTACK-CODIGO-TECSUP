package com.codigo.spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionAcademicaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión Académica API")
                        .version("1.0.0")
                        .description("API REST para registrar y consultar profesores, asignaturas, cursos, estudiantes y matrículas.")
                        .contact(new Contact()
                                .name("Fabrizio Allcca")
                                .email("fabrizio.allcca@tecsup.edu.pe"))
                        .license(new License()
                                .name("Proyecto académico")));
    }
}
