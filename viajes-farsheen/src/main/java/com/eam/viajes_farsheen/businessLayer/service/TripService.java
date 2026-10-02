package com.eam.viajes_farsheen.businessLayer.service;

import com.eam.viajes_farsheen.businessLayer.dto.trip.TripCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripUpdateDTO;

import java.util.List;

public interface TripService {

    List<TripDTO> findAll();

    TripDTO findById(Long id);

    TripDTO create(TripCreateDTO dto);

    TripDTO update(Long id, TripUpdateDTO dto);

    void delete(Long id);
}
