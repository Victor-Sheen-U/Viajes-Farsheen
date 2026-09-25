package com.eam.viajes_farsheen.persistenceLayer.repository;

import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByIdentityDocument(String identityDocument);

}
