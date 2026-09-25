package com.viajes.reservas.persistenceLayer.dao;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import com.viajes.reservas.persistenceLayer.mapper.ClienteMapper;
import com.viajes.reservas.persistenceLayer.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteDAO {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteDTO> findAll() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTO);
    }

    public Optional<ClienteEntity> findEntityById(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<ClienteDTO> findByEmail(String email) {
        return clienteRepository.findByEmail(email).map(clienteMapper::toDTO);
    }

    public Optional<ClienteDTO> findByDocumentoIdentidad(String documentoIdentidad) {
        return clienteRepository.findByDocumentoIdentidad(documentoIdentidad).map(clienteMapper::toDTO);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean existsByDocumentoIdentidad(String documentoIdentidad) {
        return clienteRepository.existsByDocumentoIdentidad(documentoIdentidad);
    }

    public ClienteDTO save(ClienteEntity entity) {
        ClienteEntity saved = clienteRepository.save(entity);
        return clienteMapper.toDTO(saved);
    }

    public ClienteEntity saveEntity(ClienteEntity entity) {
        return clienteRepository.save(entity);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }
}
