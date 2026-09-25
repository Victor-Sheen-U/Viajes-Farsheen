package com.eam.viajes_farsheen.businessLayer.dto.booking;

import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Descriptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion publica en las consultas del booking")

public class BookingDTO {

    @Schema(description = "Identificador unico que se asigna en el sistema")
    private Long id;

    @Schema(description = "Fecha y hora de la reservacion del viaje")
    private LocalDateTime bookingDate;

    @Schema(description = "Informacion de la cantidad de personas que viajan")
    private Integer numberOfPersons;

    @Schema(description = "Precio total del viaje")
    private BigDecimal totalPrice;

    @Schema(description = "Estado actual del viaje Confirmado/Pendiente/Cancelado")
    private String status;

    @Schema(description = "Cliente que esta asignado para esta reserva")
    private CustomerEntity customer;

    @Schema(description = "Viaje que esta asignado para esta reserva")
    private TripEntity trip;
}
