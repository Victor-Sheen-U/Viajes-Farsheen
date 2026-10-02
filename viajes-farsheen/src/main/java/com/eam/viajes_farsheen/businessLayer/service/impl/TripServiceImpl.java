package com.eam.viajes_farsheen.businessLayer.service.impl;

import com.eam.viajes_farsheen.businessLayer.dto.trip.TripCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.TripService;
import com.eam.viajes_farsheen.persistenceLayer.dao.DestinationDao;
import com.eam.viajes_farsheen.persistenceLayer.dao.TripDao;
import com.eam.viajes_farsheen.persistenceLayer.entity.DestinationEntity;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.TripMapper;
import com.eam.viajes_farsheen.presentationLayer.exception.BadRequestException;
import com.eam.viajes_farsheen.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripDao tripDao;
    private final DestinationDao destinationDao;
    private final TripMapper tripMapper;


    @Override
    @Transactional(readOnly = true)
    public List<TripDTO> findAll() {
        return tripDao.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public TripDTO findById(Long id) {
        return tripDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public TripDTO create(TripCreateDTO dto) {
        // buscamos si el destino existe
        DestinationEntity destination = destinationDao.findEntityById(dto.getDestinationId())
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + dto.getDestinationId()));

        // validamos coherencia de fechas
        if (dto.getArrivalDate() != null && dto.getDepartureDate() != null) {
            if (dto.getArrivalDate().isBefore(dto.getDepartureDate())) {
                throw new BadRequestException("La fecha de llegada no puede ser anterior a la fecha de salida");
            }
        }

        if (dto.getAvailableSpots() != null && dto.getAvailableSpots() <= 0) {
            throw new BadRequestException("Los cupos disponibles deben ser mayores a 0");
        }

        TripEntity entity = tripMapper.toEntity(dto);
        entity.setDestination(destination);
        entity.setState("DISPONIBLE");

        return tripDao.save(entity);
    }

    @Override
    @Transactional
    public TripDTO update(Long id, TripUpdateDTO dto) {
        TripEntity trip = tripDao.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado para actualizar con ID: " + id));

        if (dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
            trip.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            trip.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            trip.setPrice(dto.getPrice());
        }
        if (dto.getDuration() != null) {
            trip.setDuration(dto.getDuration());
        }
        if (dto.getDepartureDate() != null) {
            trip.setDepartureDate(dto.getDepartureDate());
        }
        if (dto.getArrivalDate() != null) {
            trip.setArrivalDate(dto.getArrivalDate());
        }
        if (dto.getAvailableSpots() != null) {
            trip.setAvailableSpots(dto.getAvailableSpots());
        }
        if (dto.getState() != null) {
            trip.setState(dto.getState());
        }

        return tripDao.save(trip);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!tripDao.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar porque el viaje no existe con ID: " + id);
        }
        tripDao.deleteById(id);
    }
}
