package com.viajes.reservas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Reservas de Viajes - Viajes Farsheen")
                        .version("1.0.0")
                        .description("API RESTful para la administración de destinos, viajes, clientes y reservas de viajes. Desarrollado con Spring Boot 3 y arquitectura en capas.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Farsheen")
                                .email("soporte@viajesfarsheen.com")
                                .url("https://viajesfarsheen.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
