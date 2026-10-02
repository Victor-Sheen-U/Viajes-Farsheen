package com.eam.viajes_farsheen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // configuracion de swagger openapi para la documentacion de la api
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Sistema de Gestion de Reservas - Viajes Farsheen")
                        .version("1.0.0")
                        .description("Documentacion interactiva de los endpoints para administracion de viajes, clientes, destinos y reservas turisticas.")
                        .contact(new Contact()
                                .name("Victor Sheen - EAM")
                                .email("arias.victor.9253@eam.edu.co")));
    }
}
