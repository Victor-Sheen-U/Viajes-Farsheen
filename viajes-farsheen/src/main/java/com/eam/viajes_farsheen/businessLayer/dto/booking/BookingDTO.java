package com.eam.viajes_farsheen.businessLayer.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion publica en las consultas del booking")
public class BookingDTO {

    @Schema(description = "Identificador unico que se asigna en el sistema", example = "1")
    private Long id;

    @Schema(description = "Fecha y hora de la reservacion del viaje", example = "2025-03-01T10:30:00")
    private LocalDateTime bookingDate;

    @Schema(description = "Informacion de la cantidad de personas que viajan", example = "2")
    private Integer numberOfPersons;

    @Schema(description = "Precio total del viaje", example = "2500000.00")
    private BigDecimal totalPrice;

    @Schema(description = "Estado actual de la reserva Confirmado/Pendiente/Cancelado", example = "CONFIRMADA")
    private String status;

    @Schema(description = "Id del cliente asociado a la reserva", example = "1")
    private Long customerId;

    @Schema(description = "Nombre completo del cliente", example = "Carlos Gomez")
    private String customerFullName;

    @Schema(description = "Id del viaje reservado", example = "1")
    private Long tripId;

    @Schema(description = "Titulo del viaje reservado", example = "Semana Santa en Cartagena")
    private String tripTitle;
}
