package com.viajes.reservas.businessLayer.dto.reserva;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para realizar una nueva Reserva")
public class ReservaCreateDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    @Schema(description = "ID del cliente que realiza la reserva", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long clienteId;

    @NotNull(message = "El ID del viaje es obligatorio")
    @Schema(description = "ID del viaje a reservar", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long viajeId;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "El número de personas debe ser como mínimo 1")
    @Schema(description = "Cantidad de personas a incluir en la reserva", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numeroPersonas;
}
