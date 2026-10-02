package com.eam.viajes_farsheen.presentationLayer.controller;

import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.destination.DestinationDTO;
import com.eam.viajes_farsheen.businessLayer.service.DestinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/destinos", "/api/v1/destinations"})
@RequiredArgsConstructor
@Tag(name = "Destinos", description = "Endpoints para administrar y consultar los destinos turisticos")
public class DestinationController {

    private final DestinationService destinationService;

    // listar todos los destinos registrados
    @GetMapping
    @Operation(summary = "Listar destinos", description = "Retorna todos los destinos de viaje disponibles")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<DestinationDTO>> getAll() {
        return ResponseEntity.ok(destinationService.findAll());
    }

    // buscar destino por su id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener destino por ID", description = "Retorna un destino en base a su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Destino encontrado"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    public ResponseEntity<DestinationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(destinationService.findById(id));
    }

    // registrar un nuevo destino
    @PostMapping
    @Operation(summary = "Crear nuevo destino", description = "Permite registrar un nuevo destino turistico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Destino creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    })
    public ResponseEntity<DestinationDTO> create(@Valid @RequestBody DestinationCreateDTO dto) {
        return new ResponseEntity<>(destinationService.create(dto), HttpStatus.CREATED);
    }

    // eliminar un destino
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar destino", description = "Elimina un destino por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Destino eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        destinationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
