package com.viajes.reservas.persistenceLayer.repository;

import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<ViajeEntity, Long> {

    List<ViajeEntity> findByDestinoId(Long destinoId);

    List<ViajeEntity> findByEstado(String estado);

    List<ViajeEntity> findByCuposDisponiblesGreaterThan(Integer cupos);
}
