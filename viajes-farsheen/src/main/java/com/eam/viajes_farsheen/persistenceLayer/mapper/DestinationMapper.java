package com.eam.viajes_farsheen.persistenceLayer.mapper;

import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.DestinationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// mapper con mapstruct para destinos
@Mapper(componentModel = "spring")
public interface DestinationMapper {

    DestinationDTO toDTO(DestinationEntity entity);

    List<DestinationDTO> toDTOList(List<DestinationEntity> entities);

    @Mapping(target = "id", ignore = true)
    DestinationEntity toEntity(DestinationCreateDTO dto);
}
