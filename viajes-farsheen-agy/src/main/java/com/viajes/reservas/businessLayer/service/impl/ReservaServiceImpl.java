package com.viajes.reservas.businessLayer.service.impl;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaCreateDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaUpdateDTO;
import com.viajes.reservas.businessLayer.service.ReservaService;
import com.viajes.reservas.persistenceLayer.dao.ClienteDAO;
import com.viajes.reservas.persistenceLayer.dao.ReservaDAO;
import com.viajes.reservas.persistenceLayer.dao.ViajeDAO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import com.viajes.reservas.persistenceLayer.entity.ReservaEntity;
import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
import com.viajes.reservas.persistenceLayer.mapper.ReservaMapper;
import com.viajes.reservas.presentationLayer.exception.BadRequestException;
import com.viajes.reservas.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaDAO reservaDAO;
    private final ClienteDAO clienteDAO;
    private final ViajeDAO viajeDAO;
    private final ReservaMapper reservaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> findAll() {
        return reservaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaDTO findById(Long id) {
        return reservaDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> findByClienteId(Long clienteId) {
        if (!clienteDAO.existsById(clienteId)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
        return reservaDAO.findByClienteId(clienteId);
    }

    @Override
    @Transactional
    public ReservaDTO create(ReservaCreateDTO dto) {
        ClienteEntity cliente = clienteDAO.findEntityById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getClienteId()));

        ViajeEntity viaje = viajeDAO.findEntityById(dto.getViajeId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + dto.getViajeId()));

        if ("CANCELADO".equalsIgnoreCase(viaje.getEstado())) {
            throw new BadRequestException("El viaje seleccionado ha sido cancelado");
        }

        if (viaje.getCuposDisponibles() < dto.getNumeroPersonas()) {
            throw new BadRequestException("No hay cupos suficientes para este viaje. Cupos disponibles: " + viaje.getCuposDisponibles());
        }

        BigDecimal precioTotal = viaje.getPrecio().multiply(BigDecimal.valueOf(dto.getNumeroPersonas()));

        int nuevosCupos = viaje.getCuposDisponibles() - dto.getNumeroPersonas();
        viaje.setCuposDisponibles(nuevosCupos);
        if (nuevosCupos == 0) {
            viaje.setEstado("AGOTADO");
        }
        viajeDAO.saveEntity(viaje);

        ReservaEntity reserva = reservaMapper.toEntity(dto);
        reserva.setCliente(cliente);
        reserva.setViaje(viaje);
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setPrecioTotal(precioTotal);
        reserva.setEstado("CONFIRMADA");

        return reservaDAO.save(reserva);
    }

    @Override
    @Transactional
    public ReservaDTO update(Long id, ReservaUpdateDTO dto) {
        ReservaEntity reserva = reservaDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        if (dto.getNumeroPersonas() != null && !dto.getNumeroPersonas().equals(reserva.getNumeroPersonas())) {
            int diferencia = dto.getNumeroPersonas() - reserva.getNumeroPersonas();
            ViajeEntity viaje = reserva.getViaje();

            if (diferencia > 0 && viaje.getCuposDisponibles() < diferencia) {
                throw new BadRequestException("No hay suficientes cupos para aumentar la reserva. Disponibles: " + viaje.getCuposDisponibles());
            }

            viaje.setCuposDisponibles(viaje.getCuposDisponibles() - diferencia);
            if (viaje.getCuposDisponibles() == 0) {
                viaje.setEstado("AGOTADO");
            } else if ("AGOTADO".equalsIgnoreCase(viaje.getEstado()) && viaje.getCuposDisponibles() > 0) {
                viaje.setEstado("DISPONIBLE");
            }
            viajeDAO.saveEntity(viaje);

            reserva.setNumeroPersonas(dto.getNumeroPersonas());
            reserva.setPrecioTotal(viaje.getPrecio().multiply(BigDecimal.valueOf(dto.getNumeroPersonas())));
        }

        if (dto.getEstado() != null) {
            reserva.setEstado(dto.getEstado());
        }

        return reservaDAO.save(reserva);
    }

    @Override
    @Transactional
    public void cancelar(Long id) {
        ReservaEntity reserva = reservaDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        if (!"CANCELADA".equalsIgnoreCase(reserva.getEstado())) {
            ViajeEntity viaje = reserva.getViaje();
            if (viaje != null) {
                viaje.setCuposDisponibles(viaje.getCuposDisponibles() + reserva.getNumeroPersonas());
                if ("AGOTADO".equalsIgnoreCase(viaje.getEstado())) {
                    viaje.setEstado("DISPONIBLE");
                }
                viajeDAO.saveEntity(viaje);
            }
            reserva.setEstado("CANCELADA");
            reservaDAO.saveEntity(reserva);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ReservaEntity reserva = reservaDAO.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        if (!"CANCELADA".equalsIgnoreCase(reserva.getEstado())) {
            ViajeEntity viaje = reserva.getViaje();
            if (viaje != null) {
                viaje.setCuposDisponibles(viaje.getCuposDisponibles() + reserva.getNumeroPersonas());
                if ("AGOTADO".equalsIgnoreCase(viaje.getEstado())) {
                    viaje.setEstado("DISPONIBLE");
                }
                viajeDAO.saveEntity(viaje);
            }
        }
        reservaDAO.deleteById(id);
    }
}
