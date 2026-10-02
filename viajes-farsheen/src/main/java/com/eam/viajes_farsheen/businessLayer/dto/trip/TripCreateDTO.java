package com.eam.viajes_farsheen.businessLayer.dto.trip;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos obligatorios para registrar un nuevo trip")

public class TripCreateDTO {

    @NotBlank (message = "El titulo no puede estar vacio")
    @Size (min = 10, max =100, message = "El titulo debe de tener un tamaño entre 10 y 100 caracteres")
    @Schema (description = "Titulo que le es asignado a el viaje", example = "Un rosesito por medallo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @NotBlank(message = "La descripcion es obligatoria")
    @Size (max = 150, message = "La descripcion puede tener un tamaño de maximo 150 caracteres")
    @Schema (description = "Descripcion que se le es añadida a el viaje", example = "Paquete con todo incluido con alojamiento y comida", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;


    @NotNull(message = "El precio del viaje es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "el precio debe de ser mayor a 0.0")
    @Schema (description = "Precio total del costo del viaje", example = "1250000.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;



    @NotNull(message = "La duracion es obligatoria")
    @Schema (description = "Indicador de la duracion del viaje en dias", example = "5")
    private Integer duration;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Schema (description = "Indicador de la fecha de inicio del viaje", example = "2025-04-10")
    private LocalDate departureDate;

    @NotNull(message = "La fecha de llegada es obligatoria")
    @Schema (description = "Indicador de la fecha de fin del viaje", example = "2025-04-15")
    private LocalDate arrivalDate;

    @NotNull(message = "Los cupos disponibles son obligatorios")
    @Schema (description = "Indicador de los puestos disponibles para el viaje", example = "15")
    private Integer availableSpots;

    @NotNull(message = "El id del destino es obligatorio")
    @Schema (description = "Destino al que pertenece el viaje", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long destinationId;
}
