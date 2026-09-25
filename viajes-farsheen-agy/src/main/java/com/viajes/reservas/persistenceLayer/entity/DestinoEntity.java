package com.viajes.reservas.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "destinos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String pais;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 50)
    private String clima;
}
