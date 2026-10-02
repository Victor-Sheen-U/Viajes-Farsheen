package com.eam.viajes_farsheen.persistenceLayer.mapper;

import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// mapeo de reservas conectando cliente y viaje
@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerFullName", expression = "java(entity.getCustomer() != null ? entity.getCustomer().getName() + \" \" + entity.getCustomer().getLastname() : null)")
    @Mapping(target = "tripId", source = "trip.id")
    @Mapping(target = "tripTitle", source = "trip.title")
    BookingDTO toDTO(BookingEntity entity);

    List<BookingDTO> toDTOList(List<BookingEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "trip", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    BookingEntity toEntity(BookingCreateDTO dto);
}
