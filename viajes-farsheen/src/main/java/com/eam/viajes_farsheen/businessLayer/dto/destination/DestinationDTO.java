package com.eam.viajes_farsheen.businessLayer.dto.destination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Descriptor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion publica en las consultas del destination")

public class DestinationDTO {

    @Schema(description = "Identificador unico de cada destino de viaje", example = "1")
    private Long id;

    @Schema(description = "Nombre asignado a el destino de viaje", example = "Ohio")
    private String name;

    @Schema(description = "Pais del destino de viaje", example = "Israel")
    private String country;

    @Schema(description = "Ciudad del destino de viaje", example = "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch")
    private String city;

    @Schema(description = "Descripcion asignada a el destino de viaje (descripcion para una descripcion algo redundante jajajaj)")
    private String description;

    @Schema(description = "Informacion sobre el tipo de clima del destino de viaje", example = "bueno bonito y barato")
    private String weather;
}
