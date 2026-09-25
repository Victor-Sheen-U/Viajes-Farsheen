package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeCreateDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViajeMapper {

    @Mapping(target = "destinoId", source = "destino.id")
    @Mapping(target = "destinoNombre", source = "destino.nombre")
    ViajeDTO toDTO(ViajeEntity entity);

    List<ViajeDTO> toDTOList(List<ViajeEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "destino", ignore = true)
    @Mapping(target = "estado", constant = "DISPONIBLE")
    ViajeEntity toEntity(ViajeCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "destino", ignore = true)
    void updateEntityFromDTO(ViajeUpdateDTO dto, @MappingTarget ViajeEntity entity);
}
