package com.viajes.reservas.businessLayer.dto.reserva;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con los datos de una Reserva")
public class ReservaDTO {

    @Schema(description = "ID único de la reserva", example = "1")
    private Long id;

    @Schema(description = "Fecha y hora en la que se generó la reserva", example = "2025-03-01T10:30:00")
    private LocalDateTime fechaReserva;

    @Schema(description = "Número de personas reservadas", example = "2")
    private Integer numeroPersonas;

    @Schema(description = "Precio total liquidado de la reserva", example = "2500000.00")
    private BigDecimal precioTotal;

    @Schema(description = "Estado de la reserva (CONFIRMADA, PENDIENTE, CANCELADA)", example = "CONFIRMADA")
    private String estado;

    @Schema(description = "ID del cliente que reserva", example = "1")
    private Long clienteId;

    @Schema(description = "Nombre completo del cliente", example = "Carlos Gomez")
    private String clienteNombreCompleto;

    @Schema(description = "Correo del cliente", example = "carlos.gomez@mail.com")
    private String clienteEmail;

    @Schema(description = "ID del viaje reservado", example = "1")
    private Long viajeId;

    @Schema(description = "Título del viaje reservado", example = "Semana Santa en Cartagena")
    private String viajeTitulo;

    @Schema(description = "Destino del viaje reservado", example = "Cartagena Colonial y Playas")
    private String destinoNombre;
}
