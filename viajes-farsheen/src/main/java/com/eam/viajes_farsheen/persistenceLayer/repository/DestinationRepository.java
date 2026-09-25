package com.eam.viajes_farsheen.persistenceLayer.repository;

import com.eam.viajes_farsheen.persistenceLayer.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
    List<DestinationEntity> findByCountryIgnoreCase(String country);
}
