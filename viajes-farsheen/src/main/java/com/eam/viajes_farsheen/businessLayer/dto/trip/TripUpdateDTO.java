package com.eam.viajes_farsheen.businessLayer.dto.trip;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un viaje existente")
public class TripUpdateDTO {

    @Size(min = 5, max = 100, message = "El titulo debe tener entre 5 y 100 caracteres")
    @Schema(description = "Nuevo titulo del viaje", example = "Semana Santa de relax total")
    private String title;

    @Size(max = 200, message = "La descripcion no puede superar 200 caracteres")
    @Schema(description = "Nueva descripcion", example = "Actualizacion del paquete con nuevas actividades")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Schema(description = "Nuevo precio del viaje", example = "1350000.00")
    private BigDecimal price;

    @Schema(description = "Duracion en dias", example = "6")
    private Integer duration;

    @Schema(description = "Nueva fecha de salida", example = "2025-04-11")
    private LocalDate departureDate;

    @Schema(description = "Nueva fecha de regreso", example = "2025-04-17")
    private LocalDate arrivalDate;

    @Schema(description = "Nuevos cupos disponibles", example = "10")
    private Integer availableSpots;

    @Schema(description = "Estado del viaje (DISPONIBLE, AGOTADO, CANCELADO)", example = "DISPONIBLE")
    private String state;

}
