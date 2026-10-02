package com.eam.viajes_farsheen.persistenceLayer.mapper;

import com.eam.viajes_farsheen.businessLayer.dto.trip.TripCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// mapeo de viajes asociando los datos del destino
@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "destinationId", source = "destination.id")
    @Mapping(target = "destinationName", source = "destination.name")
    TripDTO toDTO(TripEntity entity);

    List<TripDTO> toDTOList(List<TripEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "destination", ignore = true) // se asocia manual en el servicio con el id
    @Mapping(target = "state", constant = "DISPONIBLE")
    TripEntity toEntity(TripCreateDTO dto);
}
