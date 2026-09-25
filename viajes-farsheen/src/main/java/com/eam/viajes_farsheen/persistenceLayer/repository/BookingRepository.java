package com.eam.viajes_farsheen.persistenceLayer.repository;

import com.eam.viajes_farsheen.persistenceLayer.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByCustomerId(Long customerId);

}
