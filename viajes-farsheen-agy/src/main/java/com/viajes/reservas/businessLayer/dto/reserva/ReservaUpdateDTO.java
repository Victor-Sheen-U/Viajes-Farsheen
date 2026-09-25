package com.viajes.reservas.businessLayer.dto.reserva;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para modificar una Reserva existente")
public class ReservaUpdateDTO {

    @Min(value = 1, message = "El número de personas debe ser como mínimo 1")
    @Schema(description = "Número actualizado de personas", example = "3")
    private Integer numeroPersonas;

    @Schema(description = "Estado de la reserva (CONFIRMADA, PENDIENTE, CANCELADA)", example = "CONFIRMADA")
    private String estado;
}
