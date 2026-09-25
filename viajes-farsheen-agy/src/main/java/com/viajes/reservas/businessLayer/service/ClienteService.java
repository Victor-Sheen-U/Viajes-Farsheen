package com.viajes.reservas.businessLayer.service;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteCreateDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteUpdateDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO findByEmail(String email);

    ClienteDTO findByDocumentoIdentidad(String documentoIdentidad);

    ClienteDTO create(ClienteCreateDTO dto);

    ClienteDTO update(Long id, ClienteUpdateDTO dto);

    void delete(Long id);
}
