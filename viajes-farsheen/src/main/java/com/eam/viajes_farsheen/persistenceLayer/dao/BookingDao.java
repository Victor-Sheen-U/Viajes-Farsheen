package com.eam.viajes_farsheen.persistenceLayer.dao;

import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.BookingEntity;
import com.eam.viajes_farsheen.persistenceLayer.mapper.BookingMapper;
import com.eam.viajes_farsheen.persistenceLayer.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DAO para reservas
@Repository
@RequiredArgsConstructor
public class BookingDao {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDTO> findAll() {
        return bookingMapper.toDTOList(bookingRepository.findAll());
    }

    public Optional<BookingDTO> findById(Long id) {
        return bookingRepository.findById(id).map(bookingMapper::toDTO);
    }

    public Optional<BookingEntity> findEntityById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<BookingDTO> findByCustomerId(Long customerId) {
        return bookingMapper.toDTOList(bookingRepository.findByCustomerId(customerId));
    }

    public BookingDTO save(BookingEntity entity) {
        BookingEntity saved = bookingRepository.save(entity);
        return bookingMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }
}
