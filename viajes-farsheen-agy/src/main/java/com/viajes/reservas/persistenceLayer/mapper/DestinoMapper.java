package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.destino.DestinoCreateDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DestinoMapper {

    DestinoDTO toDTO(DestinoEntity entity);

    List<DestinoDTO> toDTOList(List<DestinoEntity> entities);

    @Mapping(target = "id", ignore = true)
    DestinoEntity toEntity(DestinoCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(DestinoUpdateDTO dto, @MappingTarget DestinoEntity entity);
}
