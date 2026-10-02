package com.eam.viajes_farsheen.businessLayer.service.impl;

import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationDTO;
import com.eam.viajes_farsheen.businessLayer.service.DestinationService;
import com.eam.viajes_farsheen.persistenceLayer.dao.DestinationDao;
import com.eam.viajes_farsheen.persistenceLayer.entity.DestinationEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.DestinationMapper;
import com.eam.viajes_farsheen.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationDao destinationDao;
    private final DestinationMapper destinationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DestinationDTO> findAll() {
        return destinationDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DestinationDTO findById(Long id) {
        // si no lo encuentra lanzamos la excepcion 404
        return destinationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con el id: " + id));
    }

    @Override
    @Transactional
    public DestinationDTO create(DestinationCreateDTO dto) {
        // convertimos el dto a entity con mapstruct y guardamos usando el dao
        DestinationEntity entity = destinationMapper.toEntity(dto);
        return destinationDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!destinationDao.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar porque el destino no existe con id: " + id);
        }
        destinationDao.deleteById(id);
    }
}
