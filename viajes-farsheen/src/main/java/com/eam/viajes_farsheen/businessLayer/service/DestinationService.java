package com.eam.viajes_farsheen.businessLayer.service;

import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationDTO;

import java.util.List;

public interface DestinationService {

    List<DestinationDTO> findAll();

    DestinationDTO findById(Long id);

    DestinationDTO create(DestinationCreateDTO dto);

    void delete(Long id);
}
