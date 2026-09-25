package com.eam.viajes_farsheen.persistenceLayer.repository;

import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
    List<TripEntity> findByDestinationId(Long destinationId);

    List<TripEntity> findByState(String state);
}
