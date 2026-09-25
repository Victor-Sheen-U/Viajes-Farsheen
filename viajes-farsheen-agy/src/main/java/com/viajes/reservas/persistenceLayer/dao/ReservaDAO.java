package com.viajes.reservas.persistenceLayer.dao;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.persistenceLayer.entity.ReservaEntity;
import com.viajes.reservas.persistenceLayer.mapper.ReservaMapper;
import com.viajes.reservas.persistenceLayer.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservaDAO {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public List<ReservaDTO> findAll() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    public Optional<ReservaDTO> findById(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }

    public Optional<ReservaEntity> findEntityById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<ReservaDTO> findByClienteId(Long clienteId) {
        return reservaMapper.toDTOList(reservaRepository.findByClienteId(clienteId));
    }

    public List<ReservaDTO> findByViajeId(Long viajeId) {
        return reservaMapper.toDTOList(reservaRepository.findByViajeId(viajeId));
    }

    public List<ReservaDTO> findByEstado(String estado) {
        return reservaMapper.toDTOList(reservaRepository.findByEstado(estado));
    }

    public ReservaDTO save(ReservaEntity entity) {
        ReservaEntity saved = reservaRepository.save(entity);
        return reservaMapper.toDTO(saved);
    }

    public ReservaEntity saveEntity(ReservaEntity entity) {
        return reservaRepository.save(entity);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return reservaRepository.existsById(id);
    }
}
