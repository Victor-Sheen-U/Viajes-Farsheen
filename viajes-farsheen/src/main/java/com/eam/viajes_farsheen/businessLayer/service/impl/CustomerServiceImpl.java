package com.eam.viajes_farsheen.businessLayer.service.impl;

import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.CustomerService;
import com.eam.viajes_farsheen.persistenceLayer.dao.CustomerDao;
import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.CustomerMapper;
import com.eam.viajes_farsheen.presentationLayer.exception.BadRequestException;
import com.eam.viajes_farsheen.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        return customerDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id) {
        return customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public CustomerDTO create(CustomerCreateDTO dto) {
        // revisamos que el correo no este repetido en la bd
        if (customerDao.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Ya existe un cliente registrado con el correo: " + dto.getEmail());
        }

        // tampoco debe repetirse el documento
        if (customerDao.findByIdentityDocument(dto.getIdentityDocument()).isPresent()) {
            throw new BadRequestException("Ya existe un cliente con el documento de identidad: " + dto.getIdentityDocument());
        }

        CustomerEntity entidad = customerMapper.toEntity(dto);
        return customerDao.save(entidad);
    }

    @Override
    @Transactional
    public CustomerDTO update(Long id, CustomerUpdateDTO dto) {
        CustomerEntity entidad = customerDao.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar porque el cliente no existe con id: " + id));

        // actualizamos los campos si vienen en la peticion
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            entidad.setName(dto.getName());
        }
        if (dto.getLastname() != null && !dto.getLastname().trim().isEmpty()) {
            entidad.setLastname(dto.getLastname());
        }
        if (dto.getPhone() != null) {
            entidad.setPhone(dto.getPhone());
        }

        return customerDao.save(entidad);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!customerDao.existsById(id)) {
            throw new ResourceNotFoundException("No existe el cliente con id: " + id);
        }
        customerDao.deleteById(id);
    }
}
