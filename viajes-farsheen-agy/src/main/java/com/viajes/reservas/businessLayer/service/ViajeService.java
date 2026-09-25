package com.viajes.reservas.businessLayer.service;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeCreateDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeUpdateDTO;

import java.util.List;

public interface ViajeService {

    List<ViajeDTO> findAll();

    ViajeDTO findById(Long id);

    List<ViajeDTO> findByDestino(Long destinoId);

    List<ViajeDTO> findByEstado(String estado);

    ViajeDTO create(ViajeCreateDTO dto);

    ViajeDTO update(Long id, ViajeUpdateDTO dto);

    void delete(Long id);
}
