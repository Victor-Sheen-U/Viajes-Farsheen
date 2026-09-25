package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.destino.DestinoCreateDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-25T11:01:02-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-java-compiler-worker-9.7.1.jar, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class DestinoMapperImpl implements DestinoMapper {

    @Override
    public DestinoDTO toDTO(DestinoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DestinoDTO.DestinoDTOBuilder destinoDTO = DestinoDTO.builder();

        destinoDTO.id( entity.getId() );
        destinoDTO.nombre( entity.getNombre() );
        destinoDTO.pais( entity.getPais() );
        destinoDTO.ciudad( entity.getCiudad() );
        destinoDTO.descripcion( entity.getDescripcion() );
        destinoDTO.clima( entity.getClima() );

        return destinoDTO.build();
    }

    @Override
    public List<DestinoDTO> toDTOList(List<DestinoEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DestinoDTO> list = new ArrayList<DestinoDTO>( entities.size() );
        for ( DestinoEntity destinoEntity : entities ) {
            list.add( toDTO( destinoEntity ) );
        }

        return list;
    }

    @Override
    public DestinoEntity toEntity(DestinoCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DestinoEntity.DestinoEntityBuilder destinoEntity = DestinoEntity.builder();

        destinoEntity.nombre( dto.getNombre() );
        destinoEntity.pais( dto.getPais() );
        destinoEntity.ciudad( dto.getCiudad() );
        destinoEntity.descripcion( dto.getDescripcion() );
        destinoEntity.clima( dto.getClima() );

        return destinoEntity.build();
    }

    @Override
    public void updateEntityFromDTO(DestinoUpdateDTO dto, DestinoEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNombre() != null ) {
            entity.setNombre( dto.getNombre() );
        }
        if ( dto.getPais() != null ) {
            entity.setPais( dto.getPais() );
        }
        if ( dto.getCiudad() != null ) {
            entity.setCiudad( dto.getCiudad() );
        }
        if ( dto.getDescripcion() != null ) {
            entity.setDescripcion( dto.getDescripcion() );
        }
        if ( dto.getClima() != null ) {
            entity.setClima( dto.getClima() );
        }
    }
}
