package com.viajes.reservas.persistenceLayer.dao;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
import com.viajes.reservas.persistenceLayer.mapper.ViajeMapper;
import com.viajes.reservas.persistenceLayer.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ViajeDAO {

    private final ViajeRepository viajeRepository;
    private final ViajeMapper viajeMapper;

    public List<ViajeDTO> findAll() {
        return viajeMapper.toDTOList(viajeRepository.findAll());
    }

    public Optional<ViajeDTO> findById(Long id) {
        return viajeRepository.findById(id).map(viajeMapper::toDTO);
    }

    public Optional<ViajeEntity> findEntityById(Long id) {
        return viajeRepository.findById(id);
    }

    public List<ViajeDTO> findByDestinoId(Long destinoId) {
        return viajeMapper.toDTOList(viajeRepository.findByDestinoId(destinoId));
    }

    public List<ViajeDTO> findByEstado(String estado) {
        return viajeMapper.toDTOList(viajeRepository.findByEstado(estado));
    }

    public ViajeDTO save(ViajeEntity entity) {
        ViajeEntity saved = viajeRepository.save(entity);
        return viajeMapper.toDTO(saved);
    }

    public ViajeEntity saveEntity(ViajeEntity entity) {
        return viajeRepository.save(entity);
    }

    public void deleteById(Long id) {
        viajeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return viajeRepository.existsById(id);
    }
}
