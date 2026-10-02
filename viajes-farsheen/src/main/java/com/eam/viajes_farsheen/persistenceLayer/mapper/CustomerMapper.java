package com.eam.viajes_farsheen.persistenceLayer.mapper;

import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerDTO;
import com.eam.viajes_farsheen.persistenceLayer.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// mapeo entre entidad de cliente y sus dtos
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(CustomerEntity entity);

    List<CustomerDTO> toDTOList(List<CustomerEntity> entities);

    @Mapping(target = "id", ignore = true)
    CustomerEntity toEntity(CustomerCreateDTO dto);
}
