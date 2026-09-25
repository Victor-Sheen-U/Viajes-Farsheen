package com.viajes.reservas.businessLayer.dto.viaje;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
@Schema(description = "Datos para actualizar una oferta de Viaje")
public class ViajeUpdateDTO {

    @Schema(description = "Título o nombre del viaje", example = "Semana Santa en Cartagena VIP")
    private String titulo;

    @Schema(description = "Descripción del paquete turístico", example = "Paquete todo incluido con vuelos y hotel 5 estrellas")
    private String descripcion;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Schema(description = "Precio por persona", example = "1350000.00")
    private BigDecimal precio;

    @Min(value = 1, message = "La duración mínima es de 1 día")
    @Schema(description = "Duración en días", example = "6")
    private Integer duracionDias;

    @Schema(description = "Fecha de salida", example = "2025-04-10")
    private LocalDate fechaSalida;

    @Schema(description = "Fecha de llegada", example = "2025-04-16")
    private LocalDate fechaLlegada;

    @Min(value = 0, message = "Los cupos no pueden ser negativos")
    @Schema(description = "Cantidad de cupos disponibles", example = "20")
    private Integer cuposDisponibles;

    @Schema(description = "Estado de disponibilidad", example = "DISPONIBLE")
    private String estado;

    @Schema(description = "ID del destino asociado", example = "1")
    private Long destinoId;
}
