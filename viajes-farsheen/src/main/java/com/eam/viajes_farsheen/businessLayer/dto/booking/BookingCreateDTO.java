package com.eam.viajes_farsheen.businessLayer.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos obligatorios para registrar una nueva reserva o booking")
public class BookingCreateDTO {

    @NotNull(message = "Es necesario asignar un cliente obligatorio")
    @Schema(description = "Id del cliente que realiza la reserva", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long customerId;

    @NotNull(message = "Es obligatorio asignar un viaje")
    @Schema(description = "Id del viaje que se va a reservar", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tripId;

    @NotNull(message = "El numero de personas que viajan es obligatorio")
    @Min(value = 1, message = "Debe ser al menos 1 persona la que viaje")
    @Schema(description = "Cantidad de personas que viajan", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numberOfPersons;

    // opcional por si quieren mandar fecha especifica, si no en el service le pongo la fecha actual
    @Schema(description = "Fecha y hora de la reserva (esto es opcional, si no se asigna simplemente toma la actual del sistema)", example = "2025-04-01T10:00:00")
    private LocalDateTime bookingDate;

}
