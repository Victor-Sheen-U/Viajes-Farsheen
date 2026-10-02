package com.eam.viajes_farsheen.persistenceLayer.dao;

import com.eam.viajes_farsheen.businessLayer.dto.trip.TripDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.TripMapper;
import com.eam.viajes_farsheen.persistenceLayer.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//DAO para viajes
@Repository
@RequiredArgsConstructor
public class TripDao {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    public List<TripDTO> findAll() {
        return tripMapper.toDTOList(tripRepository.findAll());
    }

    public Optional<TripDTO> findById(Long id) {
        return tripRepository.findById(id).map(tripMapper::toDTO);
    }

    public Optional<TripEntity> findEntityById(Long id) {
        return tripRepository.findById(id);
    }

    public TripDTO save(TripEntity entity) {
        TripEntity saved = tripRepository.save(entity);
        return tripMapper.toDTO(saved);
    }

    public TripEntity saveEntity(TripEntity entity) {
        return tripRepository.save(entity);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
    }

    public List<TripDTO> findByDestinationId(Long destinationId) {
        return tripMapper.toDTOList(tripRepository.findByDestinationId(destinationId));
    }

    public List<TripDTO> findByState(String state) {
        return tripMapper.toDTOList(tripRepository.findByState(state));
    }
}
