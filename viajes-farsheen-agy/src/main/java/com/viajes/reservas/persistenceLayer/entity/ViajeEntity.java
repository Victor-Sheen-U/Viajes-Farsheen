package com.viajes.reservas.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "viajes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "fecha_llegada", nullable = false)
    private LocalDate fechaLlegada;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    @Column(length = 50)
    private String estado; // DISPONIBLE, AGOTADO, CANCELADO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", nullable = false)
    private DestinoEntity destino;
}
