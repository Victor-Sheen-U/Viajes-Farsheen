package com.eam.viajes_farsheen.businessLayer.service;

import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO create(CustomerCreateDTO dto);

    CustomerDTO update(Long id, CustomerUpdateDTO dto);

    void delete(Long id);
}
