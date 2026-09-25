package com.viajes.reservas.businessLayer.service.impl;

import com.viajes.reservas.businessLayer.dto.destino.DestinoCreateDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoUpdateDTO;
import com.viajes.reservas.businessLayer.service.DestinoService;
import com.viajes.reservas.persistenceLayer.dao.DestinoDAO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import com.viajes.reservas.persistenceLayer.mapper.DestinoMapper;
import com.viajes.reservas.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinoServiceImpl implements DestinoService {

    private final DestinoDAO destinoDAO;
    private final DestinoMapper destinoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DestinoDTO> findAll() {
        return destinoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DestinoDTO findById(Long id) {
        return destinoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DestinoDTO> findByPais(String pais) {
        return destinoDAO.findByPais(pais);
    }

    @Override
    @Transactional
    public DestinoDTO create(DestinoCreateDTO dto) {
        DestinoEntity entity = destinoMapper.toEntity(dto);
        return destinoDAO.save(entity);
    }

    @Override
    @Transactional
    public DestinoDTO update(Long id, DestinoUpdateDTO dto) {
        DestinoEntity entity = destinoDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + id));

        destinoMapper.updateEntityFromDTO(dto, entity);
        return destinoDAO.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!destinoDAO.existsById(id)) {
            throw new ResourceNotFoundException("Destino no encontrado con ID: " + id);
        }
        destinoDAO.deleteById(id);
    }
}
