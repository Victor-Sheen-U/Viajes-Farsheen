package com.viajes.reservas.businessLayer.service;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaCreateDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaUpdateDTO;

import java.util.List;

public interface ReservaService {

    List<ReservaDTO> findAll();

    ReservaDTO findById(Long id);

    List<ReservaDTO> findByClienteId(Long clienteId);

    ReservaDTO create(ReservaCreateDTO dto);

    ReservaDTO update(Long id, ReservaUpdateDTO dto);

    void cancelar(Long id);

    void delete(Long id);
}
