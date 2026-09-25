package com.viajes.reservas.presentationLayer.controller;

import com.viajes.reservas.businessLayer.dto.viaje.ViajeCreateDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeDTO;
import com.viajes.reservas.businessLayer.dto.viaje.ViajeUpdateDTO;
import com.viajes.reservas.businessLayer.service.ViajeService;
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
@RequestMapping("/api/v1/viajes")
@RequiredArgsConstructor
@Tag(name = "Viajes", description = "Operaciones para consultar y administrar el catálogo de viajes")
public class ViajeController {

    private final ViajeService viajeService;

    // HU1: Consultar Viajes Disponibles (200 OK)
    @GetMapping
    @Operation(summary = "Listar todos los viajes", description = "Retorna el listado completo de viajes y paquetes turísticos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente")
    public ResponseEntity<List<ViajeDTO>> getAll() {
        return ResponseEntity.ok(viajeService.findAll());
    }

    // HU2: Ver Detalles de un Viaje (200 OK o 404 Not Found)
    @GetMapping("/{id}")
    @Operation(summary = "Obtener viaje por ID", description = "Retorna el detalle completo de un viaje específico por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    public ResponseEntity<ViajeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(viajeService.findById(id));
    }

    @GetMapping("/destino/{destinoId}")
    @Operation(summary = "Filtrar viajes por destino", description = "Retorna todos los viajes programados para un destino turístico específico")
    @ApiResponse(responseCode = "200", description = "Viajes encontrados para el destino indicado")
    public ResponseEntity<List<ViajeDTO>> getByDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(viajeService.findByDestino(destinoId));
    }

    // Crear Viaje (201 Created)
    @PostMapping
    @Operation(summary = "Crear nuevo viaje", description = "Permite a los administradores publicar una nueva oferta turística de viaje")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Viaje creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Destino referenciado no existe")
    })
    public ResponseEntity<ViajeDTO> create(@Valid @RequestBody ViajeCreateDTO dto) {
        return new ResponseEntity<>(viajeService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar viaje", description = "Modifica los datos de una oferta de viaje existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Viaje o destino no encontrado")
    })
    public ResponseEntity<ViajeDTO> update(@PathVariable Long id, @Valid @RequestBody ViajeUpdateDTO dto) {
        return ResponseEntity.ok(viajeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar viaje", description = "Elimina una oferta de viaje por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        viajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
