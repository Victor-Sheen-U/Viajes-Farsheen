package com.viajes.reservas.businessLayer.dto.viaje;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para publicar una nueva oferta de Viaje")
public class ViajeCreateDTO {

    @NotBlank(message = "El título del viaje es obligatorio")
    @Schema(description = "Título o nombre del viaje", example = "Semana Santa en Cartagena", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Schema(description = "Descripción del paquete turístico", example = "Paquete todo incluido con vuelos y hotel 4 estrellas")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Schema(description = "Precio por persona", example = "1250000.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal precio;

    @NotNull(message = "La duración en días es obligatoria")
    @Min(value = 1, message = "La duración mínima es de 1 día")
    @Schema(description = "Duración en días", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duracionDias;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Schema(description = "Fecha de salida", example = "2025-04-10", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaSalida;

    @NotNull(message = "La fecha de llegada es obligatoria")
    @Schema(description = "Fecha de llegada", example = "2025-04-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaLlegada;

    @NotNull(message = "Los cupos disponibles son obligatorios")
    @Min(value = 0, message = "Los cupos no pueden ser negativos")
    @Schema(description = "Cantidad de cupos disponibles", example = "15", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cuposDisponibles;

    @NotNull(message = "El ID del destino es obligatorio")
    @Schema(description = "ID del destino asociado", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long destinoId;
}
