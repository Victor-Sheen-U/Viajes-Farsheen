package com.eam.viajes_farsheen.persistenceLayer.dao;

import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.DestinationEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.DestinationMapper;
import com.eam.viajes_farsheen.persistenceLayer.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DAO para destinos
@Repository
@RequiredArgsConstructor
public class DestinationDao {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;

    public List<DestinationDTO> findAll() {
        return destinationMapper.toDTOList(destinationRepository.findAll());
    }

    public Optional<DestinationDTO> findById(Long id) {
        return destinationRepository.findById(id).map(destinationMapper::toDTO);
    }

    public Optional<DestinationEntity> findEntityById(Long id) {
        return destinationRepository.findById(id);
    }

    public DestinationDTO save(DestinationEntity entity) {
        DestinationEntity guardado = destinationRepository.save(entity);
        return destinationMapper.toDTO(guardado);
    }

    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return destinationRepository.existsById(id);
    }

    public List<DestinationDTO> findByCountry(String country) {
        return destinationMapper.toDTOList(destinationRepository.findByCountryIgnoreCase(country));
    }
}
