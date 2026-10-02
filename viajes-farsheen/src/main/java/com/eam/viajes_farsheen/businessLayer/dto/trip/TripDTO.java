package com.eam.viajes_farsheen.businessLayer.dto.trip;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Descriptor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion publica en las consultas del trip")

public class TripDTO {

    @Schema(description = "Identificador unico para los trips")
    private Long id;

    @Schema (description = "Titulo que le es asignado a el viaje")
    private String title;

    @Schema (description = "Descripcion que se le es añadida a el viaje")
    private String description;

    @Schema (description = "Precio total del costo del viaje")
    private BigDecimal price;

    @Schema (description = "Indicador de la duracion del viaje en dias")
    private Integer duration;

    @Schema (description = "Indicador de la fecha de inicio del viaje")
    private LocalDate departureDate;

    @Schema (description = "Indicador de la fecha de fin del viaje")
    private LocalDate arrivalDate;

    @Schema (description = "Indicador de los puestos disponibles para el viaje")
    private Integer availableSpots;

    @Schema (description = "Representa el estado actual en el que se encuentra")
    private String state;

    @Schema (description = "Id del destino asignado a este viaje")
    private Long destinationId;

    @Schema (description = "Nombre del destino para mostrar en consultas")
    private String destinationName;
}
