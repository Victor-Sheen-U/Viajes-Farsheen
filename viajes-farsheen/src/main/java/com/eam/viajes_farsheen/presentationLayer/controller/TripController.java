package com.eam.viajes_farsheen.presentationLayer.controller;

import com.eam.viajes_farsheen.businessLayer.dto.trip.TripCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripDTO;
import com.eam.viajes_farsheen.businessLayer.dto.trip.TripUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.TripService;
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
@RequestMapping({"/api/v1/viajes", "/api/v1/trips"})
@RequiredArgsConstructor
@Tag(name = "Viajes", description = "Operaciones para consultar y administrar el catalogo de viajes")
public class TripController {

    private final TripService tripService;


    @GetMapping
    @Operation(summary = "Listar todos los viajes", description = "Retorna el listado completo de viajes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<TripDTO>> getAll() {
        return ResponseEntity.ok(tripService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener viaje por ID", description = "Retorna el detalle completo de un viaje especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    public ResponseEntity<TripDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.findById(id));
    }

    // Crear oferta de viaje (201)
    @PostMapping
    @Operation(summary = "Crear nuevo viaje", description = "Permite a los administradores publicar una oferta de viaje")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Viaje creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos o cupos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Destino asignado no existe")
    })
    public ResponseEntity<TripDTO> create(@Valid @RequestBody TripCreateDTO dto) {
        return new ResponseEntity<>(tripService.create(dto), HttpStatus.CREATED);
    }

    // Actualizar datos del viaje
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar viaje", description = "Modifica los datos de un viaje existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    public ResponseEntity<TripDTO> update(@PathVariable Long id, @Valid @RequestBody TripUpdateDTO dto) {
        return ResponseEntity.ok(tripService.update(id, dto));
    }

    // Eliminar un viaje
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar viaje", description = "Elimina un paquete o viaje del catalogo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
