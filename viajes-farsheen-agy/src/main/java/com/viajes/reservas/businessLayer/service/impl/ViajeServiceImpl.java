package com.viajes.reservas.businessLayer.service.impl;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeCreateDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeUpdateDTO;
import com.viajes.reservas.businessLayer.service.ViajeService;
import com.viajes.reservas.persistenceLayer.dao.DestinoDAO;
import com.viajes.reservas.persistenceLayer.dao.ViajeDAO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
import com.viajes.reservas.persistenceLayer.mapper.ViajeMapper;
import com.viajes.reservas.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajeDAO viajeDAO;
    private final DestinoDAO destinoDAO;
    private final ViajeMapper viajeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ViajeDTO> findAll() {
        return viajeDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ViajeDTO findById(Long id) {
        return viajeDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViajeDTO> findByDestino(Long destinoId) {
        return viajeDAO.findByDestinoId(destinoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViajeDTO> findByEstado(String estado) {
        return viajeDAO.findByEstado(estado);
    }

    @Override
    @Transactional
    public ViajeDTO create(ViajeCreateDTO dto) {
        DestinoEntity destino = destinoDAO.findEntityById(dto.getDestinoId())
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + dto.getDestinoId()));

        ViajeEntity entity = viajeMapper.toEntity(dto);
        entity.setDestino(destino);
        if (entity.getEstado() == null) {
            entity.setEstado("DISPONIBLE");
        }
        return viajeDAO.save(entity);
    }

    @Override
    @Transactional
    public ViajeDTO update(Long id, ViajeUpdateDTO dto) {
        ViajeEntity entity = viajeDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + id));

        if (dto.getDestinoId() != null) {
            DestinoEntity destino = destinoDAO.findEntityById(dto.getDestinoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + dto.getDestinoId()));
            entity.setDestino(destino);
        }

        viajeMapper.updateEntityFromDTO(dto, entity);
        return viajeDAO.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!viajeDAO.existsById(id)) {
            throw new ResourceNotFoundException("Viaje no encontrado con ID: " + id);
        }
        viajeDAO.deleteById(id);
    }
}
