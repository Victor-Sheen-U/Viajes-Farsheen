package com.viajes.reservas.businessLayer.dto.viaje;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Respuesta con la información completa de una oferta de Viaje")
public class ViajeDTO {

    @Schema(description = "ID único del viaje", example = "1")
    private Long id;

    @Schema(description = "Título comercial de la oferta de viaje", example = "Semana Santa en Cartagena")
    private String titulo;

    @Schema(description = "Descripción del paquete de viaje", example = "Paquete todo incluido con vuelos y hotel 4 estrellas")
    private String descripcion;

    @Schema(description = "Precio por persona", example = "1250000.00")
    private BigDecimal precio;

    @Schema(description = "Duración total en días", example = "5")
    private Integer duracionDias;

    @Schema(description = "Fecha de inicio o salida del viaje", example = "2025-04-10")
    private LocalDate fechaSalida;

    @Schema(description = "Fecha de retorno o llegada del viaje", example = "2025-04-15")
    private LocalDate fechaLlegada;

    @Schema(description = "Cupos disponibles en el viaje", example = "15")
    private Integer cuposDisponibles;

    @Schema(description = "Estado de disponibilidad (DISPONIBLE, AGOTADO, CANCELADO)", example = "DISPONIBLE")
    private String estado;

    @Schema(description = "ID del destino asociado", example = "1")
    private Long destinoId;

    @Schema(description = "Nombre del destino asociado", example = "Cartagena Colonial y Playas")
    private String destinoNombre;
}
