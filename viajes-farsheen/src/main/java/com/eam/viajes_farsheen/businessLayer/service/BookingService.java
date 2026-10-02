package com.eam.viajes_farsheen.businessLayer.service;

import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingUpdateDTO;

import java.util.List;

public interface BookingService {

    List<BookingDTO> findAll();

    BookingDTO findById(Long id);


    BookingDTO create(BookingCreateDTO dto);


    List<BookingDTO> findByCustomerId(Long customerId);

    BookingDTO update(Long id, BookingUpdateDTO dto);


    void cancel(Long id);
}
