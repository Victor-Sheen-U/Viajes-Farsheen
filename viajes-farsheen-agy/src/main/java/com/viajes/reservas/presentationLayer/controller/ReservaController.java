package com.viajes.reservas.presentationLayer.controller;

import com.viajes.reservas.businessLayer.dto.reserva.ReservaCreateDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaDTO;
import com.viajes.reservas.businessLayer.dto.reserva.ReservaUpdateDTO;
import com.viajes.reservas.businessLayer.service.ReservaService;
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
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Gestión de reservas de viajes realizadas por clientes")
public class ReservaController {

    private final ReservaService reservaService;

    // Listar todas las reservas
    @GetMapping
    @Operation(summary = "Listar todas las reservas", description = "Retorna el listado global de reservas generadas en la plataforma")
    @ApiResponse(responseCode = "200", description = "Listado de reservas obtenido exitosamente")
    public ResponseEntity<List<ReservaDTO>> getAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    // Obtener reserva por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID", description = "Retorna el detalle completo de una reserva por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<ReservaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    // HU3: Realizar una Reserva (201 Created)
    @PostMapping
    @Operation(summary = "Realizar una reserva", description = "Crea una nueva reserva asociada a un cliente y a un viaje, validando cupos y liquidando el precio total")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o cupos insuficientes"),
            @ApiResponse(responseCode = "404", description = "Cliente o Viaje no existe")
    })
    public ResponseEntity<ReservaDTO> create(@Valid @RequestBody ReservaCreateDTO dto) {
        return new ResponseEntity<>(reservaService.create(dto), HttpStatus.CREATED);
    }

    // HU4: Consultar Reservas del Cliente (200 OK)
    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Historial de reservas por cliente", description = "Obtiene todas las reservas asociadas a un cliente identificado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historial de reservas obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<List<ReservaDTO>> getByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.findByClienteId(clienteId));
    }

    // Actualizar una Reserva
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva", description = "Modifica el número de personas o estado de una reserva existente reajustando cupos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Cupos insuficientes para el incremento solicitado"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<ReservaDTO> update(@PathVariable Long id, @Valid @RequestBody ReservaUpdateDTO dto) {
        return ResponseEntity.ok(reservaService.update(id, dto));
    }

    // HU5: Cancelar una Reserva (204 No Content)
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar una reserva", description = "Cancela la reserva, libera los cupos de regreso al viaje y retorna 204 No Content")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
