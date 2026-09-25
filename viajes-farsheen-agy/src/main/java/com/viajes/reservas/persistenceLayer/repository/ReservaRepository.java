package com.viajes.reservas.persistenceLayer.repository;

import com.viajes.reservas.persistenceLayer.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findByClienteId(Long clienteId);

    List<ReservaEntity> findByViajeId(Long viajeId);

    List<ReservaEntity> findByEstado(String estado);
}
