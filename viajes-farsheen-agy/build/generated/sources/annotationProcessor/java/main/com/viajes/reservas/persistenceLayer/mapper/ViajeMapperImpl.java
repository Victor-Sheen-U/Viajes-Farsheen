package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeCreateDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import com.viajes.reservas.persistenceLayer.entity.ViajeEntity;
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
public class ViajeMapperImpl implements ViajeMapper {

    @Override
    public ViajeDTO toDTO(ViajeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ViajeDTO.ViajeDTOBuilder viajeDTO = ViajeDTO.builder();

        viajeDTO.destinoId( entityDestinoId( entity ) );
        viajeDTO.destinoNombre( entityDestinoNombre( entity ) );
        viajeDTO.id( entity.getId() );
        viajeDTO.titulo( entity.getTitulo() );
        viajeDTO.descripcion( entity.getDescripcion() );
        viajeDTO.precio( entity.getPrecio() );
        viajeDTO.duracionDias( entity.getDuracionDias() );
        viajeDTO.fechaSalida( entity.getFechaSalida() );
        viajeDTO.fechaLlegada( entity.getFechaLlegada() );
        viajeDTO.cuposDisponibles( entity.getCuposDisponibles() );
        viajeDTO.estado( entity.getEstado() );

        return viajeDTO.build();
    }

    @Override
    public List<ViajeDTO> toDTOList(List<ViajeEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ViajeDTO> list = new ArrayList<ViajeDTO>( entities.size() );
        for ( ViajeEntity viajeEntity : entities ) {
            list.add( toDTO( viajeEntity ) );
        }

        return list;
    }

    @Override
    public ViajeEntity toEntity(ViajeCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ViajeEntity.ViajeEntityBuilder viajeEntity = ViajeEntity.builder();

        viajeEntity.titulo( dto.getTitulo() );
        viajeEntity.descripcion( dto.getDescripcion() );
        viajeEntity.precio( dto.getPrecio() );
        viajeEntity.duracionDias( dto.getDuracionDias() );
        viajeEntity.fechaSalida( dto.getFechaSalida() );
        viajeEntity.fechaLlegada( dto.getFechaLlegada() );
        viajeEntity.cuposDisponibles( dto.getCuposDisponibles() );

        viajeEntity.estado( "DISPONIBLE" );

        return viajeEntity.build();
    }

    @Override
    public void updateEntityFromDTO(ViajeUpdateDTO dto, ViajeEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTitulo() != null ) {
            entity.setTitulo( dto.getTitulo() );
        }
        if ( dto.getDescripcion() != null ) {
            entity.setDescripcion( dto.getDescripcion() );
        }
        if ( dto.getPrecio() != null ) {
            entity.setPrecio( dto.getPrecio() );
        }
        if ( dto.getDuracionDias() != null ) {
            entity.setDuracionDias( dto.getDuracionDias() );
        }
        if ( dto.getFechaSalida() != null ) {
            entity.setFechaSalida( dto.getFechaSalida() );
        }
        if ( dto.getFechaLlegada() != null ) {
            entity.setFechaLlegada( dto.getFechaLlegada() );
        }
        if ( dto.getCuposDisponibles() != null ) {
            entity.setCuposDisponibles( dto.getCuposDisponibles() );
        }
        if ( dto.getEstado() != null ) {
            entity.setEstado( dto.getEstado() );
        }
    }

    private Long entityDestinoId(ViajeEntity viajeEntity) {
        DestinoEntity destino = viajeEntity.getDestino();
        if ( destino == null ) {
            return null;
        }
        return destino.getId();
    }

    private String entityDestinoNombre(ViajeEntity viajeEntity) {
        DestinoEntity destino = viajeEntity.getDestino();
        if ( destino == null ) {
            return null;
        }
        return destino.getNombre();
    }
}
