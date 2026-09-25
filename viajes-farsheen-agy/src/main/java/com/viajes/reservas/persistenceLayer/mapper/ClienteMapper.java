package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteCreateDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(ClienteEntity entity);

    List<ClienteDTO> toDTOList(List<ClienteEntity> entities);

    @Mapping(target = "id", ignore = true)
    ClienteEntity toEntity(ClienteCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ClienteUpdateDTO dto, @MappingTarget ClienteEntity entity);
}
