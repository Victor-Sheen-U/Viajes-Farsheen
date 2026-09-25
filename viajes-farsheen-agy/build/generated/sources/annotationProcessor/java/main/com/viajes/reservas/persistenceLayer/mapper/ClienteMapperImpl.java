package com.viajes.reservas.persistenceLayer.mapper;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteCreateDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteUpdateDTO;
import com.viajes.reservas.persistenceLayer.entity.ClienteEntity;
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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO toDTO(ClienteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO.ClienteDTOBuilder clienteDTO = ClienteDTO.builder();

        clienteDTO.id( entity.getId() );
        clienteDTO.nombre( entity.getNombre() );
        clienteDTO.apellido( entity.getApellido() );
        clienteDTO.email( entity.getEmail() );
        clienteDTO.telefono( entity.getTelefono() );
        clienteDTO.documentoIdentidad( entity.getDocumentoIdentidad() );

        return clienteDTO.build();
    }

    @Override
    public List<ClienteDTO> toDTOList(List<ClienteEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( entities.size() );
        for ( ClienteEntity clienteEntity : entities ) {
            list.add( toDTO( clienteEntity ) );
        }

        return list;
    }

    @Override
    public ClienteEntity toEntity(ClienteCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ClienteEntity.ClienteEntityBuilder clienteEntity = ClienteEntity.builder();

        clienteEntity.nombre( dto.getNombre() );
        clienteEntity.apellido( dto.getApellido() );
        clienteEntity.email( dto.getEmail() );
        clienteEntity.telefono( dto.getTelefono() );
        clienteEntity.documentoIdentidad( dto.getDocumentoIdentidad() );

        return clienteEntity.build();
    }

    @Override
    public void updateEntityFromDTO(ClienteUpdateDTO dto, ClienteEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNombre() != null ) {
            entity.setNombre( dto.getNombre() );
        }
        if ( dto.getApellido() != null ) {
            entity.setApellido( dto.getApellido() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getTelefono() != null ) {
            entity.setTelefono( dto.getTelefono() );
        }
        if ( dto.getDocumentoIdentidad() != null ) {
            entity.setDocumentoIdentidad( dto.getDocumentoIdentidad() );
        }
    }
}
