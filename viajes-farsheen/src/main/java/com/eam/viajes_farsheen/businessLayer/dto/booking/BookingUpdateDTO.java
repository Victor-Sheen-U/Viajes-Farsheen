package com.eam.viajes_farsheen.businessLayer.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos modificables de una reserva o booking")
public class BookingUpdateDTO {

    @Min(value = 1, message = "El numero de personas debe ser minimo 1")
    @Schema(description = "Nueva cantidad de personas para la reserva", example = "3")
    private Integer numberOfPersons;

    @Schema(description = "Nuevo estado de la reserva (CONFIRMADA, CANCELADA, PENDIENTE)", example = "CANCELADA")
    private String status;

    @Schema(description = "Fecha actualizada de la reserva", example = "2025-04-02T15:30:00")
    private LocalDateTime bookingDate;

}
