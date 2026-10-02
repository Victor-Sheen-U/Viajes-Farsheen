package com.eam.viajes_farsheen.businessLayer.service.impl;

import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.BookingService;
import com.eam.viajes_farsheen.persistenceLayer.dao.BookingDao;
import com.eam.viajes_farsheen.persistenceLayer.dao.CustomerDao;
import com.eam.viajes_farsheen.persistenceLayer.dao.TripDao;
import com.eam.viajes_farsheen.persistenceLayer.entity.BookingEntity;
import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.BookingMapper;
import com.eam.viajes_farsheen.presentationLayer.exception.BadRequestException;
import com.eam.viajes_farsheen.presentationLayer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final CustomerDao customerDao;
    private final TripDao tripDao;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> findAll() {
        return bookingDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDTO findById(Long id) {
        return bookingDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
    }


    @Override
    @Transactional
    public BookingDTO create(BookingCreateDTO dto) {
        // 1 verificamos que el cliente exista en el sistema
        CustomerEntity customer = customerDao.findEntityById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getCustomerId()));

        // 2 verificamos que el viaje seleccionado exista
        TripEntity trip = tripDao.findEntityById(dto.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + dto.getTripId()));

        // 3 validamos que si hayan cupos
        if (trip.getAvailableSpots() < dto.getNumberOfPersons()) {
            throw new BadRequestException("No hay cupos suficientes para este viaje. Cupos disponibles: " + trip.getAvailableSpots());
        }

        // 4 calculamos el precio total: precio del viaje * numero de personas
        BigDecimal totalPrice = trip.getPrice().multiply(BigDecimal.valueOf(dto.getNumberOfPersons()));

        // 5 descontamos los cupos del viaje
        int nuevosCupos = trip.getAvailableSpots() - dto.getNumberOfPersons();
        trip.setAvailableSpots(nuevosCupos);
        if (nuevosCupos == 0) {
            trip.setState("AGOTADO");
        }
        tripDao.save(trip); // guardamos el viaje actualizado con los nuevos cupos

        // 6. armamos y guardamos la entidad de reserva
        BookingEntity booking = bookingMapper.toEntity(dto);
        booking.setCustomer(customer);
        booking.setTrip(trip);
        booking.setTotalPrice(totalPrice);
        booking.setStatus("CONFIRMADA");
        booking.setBookingDate(dto.getBookingDate() != null ? dto.getBookingDate() : LocalDateTime.now());

        return bookingDao.save(booking);
    }


    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> findByCustomerId(Long customerId) {
        // verificamos que el cliente si exista
        if (!customerDao.existsById(customerId)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + customerId);
        }
        return bookingDao.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public BookingDTO update(Long id, BookingUpdateDTO dto) {
        BookingEntity booking = bookingDao.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        if (dto.getNumberOfPersons() != null && dto.getNumberOfPersons() > 0) {
            // si cambia el numero de personas recalculamos precio y cupos
            int diferencia = dto.getNumberOfPersons() - booking.getNumberOfPersons();
            TripEntity trip = booking.getTrip();
            if (diferencia > 0 && trip.getAvailableSpots() < diferencia) {
                throw new BadRequestException("No hay cupos suficientes para ampliar la reserva");
            }
            trip.setAvailableSpots(trip.getAvailableSpots() - diferencia);
            tripDao.save(trip);

            booking.setNumberOfPersons(dto.getNumberOfPersons());
            booking.setTotalPrice(trip.getPrice().multiply(BigDecimal.valueOf(dto.getNumberOfPersons())));
        }

        if (dto.getStatus() != null && !dto.getStatus().trim().isEmpty()) {
            booking.setStatus(dto.getStatus());
        }

        if (dto.getBookingDate() != null) {
            booking.setBookingDate(dto.getBookingDate());
        }

        return bookingDao.save(booking);
    }


    @Override
    @Transactional
    public void cancel(Long id) {
        // buscamos la reserva
        BookingEntity booking = bookingDao.findEntityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        // devolvemos los cupos al viaje si no estaba ya cancelada
        if (!"CANCELADA".equalsIgnoreCase(booking.getStatus())) {
            TripEntity trip = booking.getTrip();
            trip.setAvailableSpots(trip.getAvailableSpots() + booking.getNumberOfPersons());
            if ("AGOTADO".equalsIgnoreCase(trip.getState())) {
                trip.setState("DISPONIBLE");
            }
            tripDao.save(trip);

            // cambiamos estado a cancelada
            booking.setStatus("CANCELADA");
            bookingDao.save(booking);
        }
    }
}
