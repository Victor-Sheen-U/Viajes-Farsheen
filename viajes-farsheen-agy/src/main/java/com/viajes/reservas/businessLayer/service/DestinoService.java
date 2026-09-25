package com.viajes.reservas.businessLayer.service;

import com.viajes.reservas.businessLayer.dto.destino.DestinoCreateDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoUpdateDTO;

import java.util.List;

public interface DestinoService {

    List<DestinoDTO> findAll();

    DestinoDTO findById(Long id);

    List<DestinoDTO> findByPais(String pais);

    DestinoDTO create(DestinoCreateDTO dto);

    DestinoDTO update(Long id, DestinoUpdateDTO dto);

    void delete(Long id);
}
