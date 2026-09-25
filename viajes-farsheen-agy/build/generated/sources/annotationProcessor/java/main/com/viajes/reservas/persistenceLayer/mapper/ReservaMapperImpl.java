package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaCreateDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
import com.viajes.reservas.persistenceLayer.entity.DestinoEntity;
import com.viajes.reservas.persistenceLayer.entity.ReservaEntity;
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
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public ReservaDTO toDTO(ReservaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReservaDTO.ReservaDTOBuilder reservaDTO = ReservaDTO.builder();

        reservaDTO.clienteId( entityClienteId( entity ) );
        reservaDTO.clienteEmail( entityClienteEmail( entity ) );
        reservaDTO.viajeId( entityViajeId( entity ) );
        reservaDTO.viajeTitulo( entityViajeTitulo( entity ) );
        reservaDTO.destinoNombre( entityViajeDestinoNombre( entity ) );
        reservaDTO.id( entity.getId() );
        reservaDTO.fechaReserva( entity.getFechaReserva() );
        reservaDTO.numeroPersonas( entity.getNumeroPersonas() );
        reservaDTO.precioTotal( entity.getPrecioTotal() );
        reservaDTO.estado( entity.getEstado() );

        reservaDTO.clienteNombreCompleto( entity.getCliente() != null ? entity.getCliente().getNombre() + " " + entity.getCliente().getApellido() : null );

        return reservaDTO.build();
    }

    @Override
    public List<ReservaDTO> toDTOList(List<ReservaEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ReservaDTO> list = new ArrayList<ReservaDTO>( entities.size() );
        for ( ReservaEntity reservaEntity : entities ) {
            list.add( toDTO( reservaEntity ) );
        }

        return list;
    }

    @Override
    public ReservaEntity toEntity(ReservaCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReservaEntity.ReservaEntityBuilder reservaEntity = ReservaEntity.builder();

        reservaEntity.numeroPersonas( dto.getNumeroPersonas() );

        return reservaEntity.build();
    }

    @Override
    public void updateEntityFromDTO(ReservaUpdateDTO dto, ReservaEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNumeroPersonas() != null ) {
            entity.setNumeroPersonas( dto.getNumeroPersonas() );
        }
        if ( dto.getEstado() != null ) {
            entity.setEstado( dto.getEstado() );
        }
    }

    private Long entityClienteId(ReservaEntity reservaEntity) {
        ClienteEntity cliente = reservaEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getId();
    }

    private String entityClienteEmail(ReservaEntity reservaEntity) {
        ClienteEntity cliente = reservaEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getEmail();
    }

    private Long entityViajeId(ReservaEntity reservaEntity) {
        ViajeEntity viaje = reservaEntity.getViaje();
        if ( viaje == null ) {
            return null;
        }
        return viaje.getId();
    }

    private String entityViajeTitulo(ReservaEntity reservaEntity) {
        ViajeEntity viaje = reservaEntity.getViaje();
        if ( viaje == null ) {
            return null;
        }
        return viaje.getTitulo();
    }

    private String entityViajeDestinoNombre(ReservaEntity reservaEntity) {
        ViajeEntity viaje = reservaEntity.getViaje();
        if ( viaje == null ) {
            return null;
        }
        DestinoEntity destino = viaje.getDestino();
        if ( destino == null ) {
            return null;
        }
        return destino.getNombre();
    }
}
