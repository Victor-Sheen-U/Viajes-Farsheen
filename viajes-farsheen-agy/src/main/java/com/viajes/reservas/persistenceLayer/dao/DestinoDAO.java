package com.viajes.reservas.persistenceLayer.dao;

import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import com.viajes.reservas.persistenceLayer.mapper.DestinoMapper;
import com.viajes.reservas.persistenceLayer.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DestinoDAO {

    private final DestinoRepository destinoRepository;
    private final DestinoMapper destinoMapper;

    public List<DestinoDTO> findAll() {
        return destinoMapper.toDTOList(destinoRepository.findAll());
    }

    public Optional<DestinoDTO> findById(Long id) {
        return destinoRepository.findById(id).map(destinoMapper::toDTO);
    }

    public Optional<DestinoEntity> findEntityById(Long id) {
        return destinoRepository.findById(id);
    }

    public List<DestinoDTO> findByPais(String pais) {
        return destinoMapper.toDTOList(destinoRepository.findByPaisIgnoreCase(pais));
    }

    public List<DestinoDTO> findByCiudad(String ciudad) {
        return destinoMapper.toDTOList(destinoRepository.findByCiudadIgnoreCase(ciudad));
    }

    public DestinoDTO save(DestinoEntity entity) {
        DestinoEntity saved = destinoRepository.save(entity);
        return destinoMapper.toDTO(saved);
    }

    public DestinoEntity saveEntity(DestinoEntity entity) {
        return destinoRepository.save(entity);
    }

    public void deleteById(Long id) {
        destinoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return destinoRepository.existsById(id);
    }
}
