package com.viajes.reservas.persistenceLayer.repository;

import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByEmail(String email);

    Optional<ClienteEntity> findByDocumentoIdentidad(String documentoIdentidad);

    boolean existsByEmail(String email);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}
