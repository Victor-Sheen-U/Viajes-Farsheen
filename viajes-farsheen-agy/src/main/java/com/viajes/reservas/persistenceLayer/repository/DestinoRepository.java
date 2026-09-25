package com.viajes.reservas.persistenceLayer.repository;

import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<DestinoEntity, Long> {

    List<DestinoEntity> findByPaisIgnoreCase(String pais);

    List<DestinoEntity> findByCiudadIgnoreCase(String ciudad);
}
