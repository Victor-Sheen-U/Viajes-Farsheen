package com.viajes.reservas.businessLayer.service.impl;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteCreateDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteUpdateDTO;
import com.viajes.reservas.businessLayer.service.ClienteService;
import com.viajes.reservas.persistenceLayer.dao.ClienteDAO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import com.viajes.reservas.persistenceLayer.mapper.ClienteMapper;
import com.viajes.reservas.presentationLayer.exception.BadRequestException;
import com.viajes.reservas.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        return clienteDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findByEmail(String email) {
        return clienteDAO.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findByDocumentoIdentidad(String documentoIdentidad) {
        return clienteDAO.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con documento: " + documentoIdentidad));
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteCreateDTO dto) {
        if (clienteDAO.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Ya existe un cliente registrado con el email: " + dto.getEmail());
        }
        if (clienteDAO.existsByDocumentoIdentidad(dto.getDocumentoIdentidad())) {
            throw new BadRequestException("Ya existe un cliente con el documento de identidad: " + dto.getDocumentoIdentidad());
        }

        ClienteEntity entity = clienteMapper.toEntity(dto);
        return clienteDAO.save(entity);
    }

    @Override
    @Transactional
    public ClienteDTO update(Long id, ClienteUpdateDTO dto) {
        ClienteEntity entity = clienteDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (dto.getEmail() != null && !dto.getEmail().equalsIgnoreCase(entity.getEmail())) {
            if (clienteDAO.existsByEmail(dto.getEmail())) {
                throw new BadRequestException("El email " + dto.getEmail() + " ya está en uso por otro cliente");
            }
        }

        if (dto.getDocumentoIdentidad() != null && !dto.getDocumentoIdentidad().equals(entity.getDocumentoIdentidad())) {
            if (clienteDAO.existsByDocumentoIdentidad(dto.getDocumentoIdentidad())) {
                throw new BadRequestException("El documento " + dto.getDocumentoIdentidad() + " ya está en uso");
            }
        }

        clienteMapper.updateEntityFromDTO(dto, entity);
        return clienteDAO.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clienteDAO.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteDAO.deleteById(id);
    }
}
