package com.eam.viajes_farsheen.businessLayer.dto.booking;

import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos obligatorios para registrar una nuevo booking")

public class BookingCreateDTO {

    @NotBlank(message = "La fecha y hora de la reserva es obligatoria")
    @Schema(description = "Fecha y hora de la reservacion del viaje que se asigna", example = "2026-09-28 14:55:00 pm", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime bookingDate;

    @NotBlank(message = "El numero de personas que viajan es obligatorio")
    @Schema(description = "Informacion de la cantidad de personas que viajan", example = "3",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numberOfPersons;

    @NotBlank(message = "")
    @Schema(description = "Estado actual del viaje Confirmado/Pendiente/Cancelado")
    private String status;

    @NotBlank(message = "Es necesario asignar un c1iente obligatorio")
    @Schema(description = "Cliente que esta asignado para esta reserva", example = "Matias Buitrago")
    private CustomerEntity customer;

    @NotBlank(message = "Es obligatorio asignar un viaje")
    @Schema(description = "Viaje que esta asignado para esta reserva", example = "viaje-001")
    private TripEntity trip;
}
