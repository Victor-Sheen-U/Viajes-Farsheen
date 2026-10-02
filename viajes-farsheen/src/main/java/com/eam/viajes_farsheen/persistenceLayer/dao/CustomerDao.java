package com.eam.viajes_farsheen.persistenceLayer.dao;

import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.CustomerMapper;
import com.eam.viajes_farsheen.persistenceLayer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DAO intermedio para operaciones de clientes
@Repository
@RequiredArgsConstructor
public class CustomerDao {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> findAll() {
        return customerMapper.toDTOList(customerRepository.findAll());
    }

    public Optional<CustomerDTO> findById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDTO);
    }

    public Optional<CustomerEntity> findEntityById(Long id) {
        return customerRepository.findById(id);
    }

    public CustomerDTO save(CustomerEntity entity) {
        CustomerEntity saved = customerRepository.save(entity);
        return customerMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    public Optional<CustomerEntity> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<CustomerEntity> findByIdentityDocument(String doc) {
        return customerRepository.findByIdentityDocument(doc);
    }
}
