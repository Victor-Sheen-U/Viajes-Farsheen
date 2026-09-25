package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaCreateDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.ReservaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNombreCompleto", expression = "java(entity.getCliente() != null ? entity.getCliente().getNombre() + \" \" + entity.getCliente().getApellido() : null)")
    @Mapping(target = "clienteEmail", source = "cliente.email")
    @Mapping(target = "viajeId", source = "viaje.id")
    @Mapping(target = "viajeTitulo", source = "viaje.titulo")
    @Mapping(target = "destinoNombre", source = "viaje.destino.nombre")
    ReservaDTO toDTO(ReservaEntity entity);

    List<ReservaDTO> toDTOList(List<ReservaEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "viaje", ignore = true)
    @Mapping(target = "fechaReserva", ignore = true)
    @Mapping(target = "precioTotal", ignore = true)
    @Mapping(target = "estado", ignore = true)
    ReservaEntity toEntity(ReservaCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "viaje", ignore = true)
    @Mapping(target = "fechaReserva", ignore = true)
    @Mapping(target = "precioTotal", ignore = true)
    void updateEntityFromDTO(ReservaUpdateDTO dto, @MappingTarget ReservaEntity entity);
}
