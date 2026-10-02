package com.eam.viajes_farsheen.presentationLayer.controller;

import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingDTO;
import com.eam.viajes_farsheen.businessLayer.dto.booking.BookingUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.BookingService;
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
@RequestMapping({"/api/v1/reservas", "/api/v1/bookings"})
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Gestion de reservas de viajes realizadas por clientes")
public class BookingController {

    private final BookingService bookingService;

    // listar todas las reservas generales
    @GetMapping
    @Operation(summary = "Listar todas las reservas", description = "Retorna el historial completo de reservas")
    @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida")
    public ResponseEntity<List<BookingDTO>> getAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    // ver detalle de una reserva especifica
    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID", description = "Retorna la informacion detallada de una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.findById(id));
    }


    @PostMapping
    @Operation(summary = "Realizar una reserva", description = "Crea una nueva reserva asociada a un cliente y un viaje")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos o cupos insuficientes"),
            @ApiResponse(responseCode = "404", description = "Cliente o Viaje no existe")
    })
    public ResponseEntity<BookingDTO> create(@Valid @RequestBody BookingCreateDTO dto) {
        return new ResponseEntity<>(bookingService.create(dto), HttpStatus.CREATED);
    }


    @GetMapping({"/cliente/{clienteId}", "/customer/{clienteId}"})
    @Operation(summary = "Historial de reservas por cliente", description = "Obtiene todas las reservas asociadas a un cliente especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<List<BookingDTO>> getByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(bookingService.findByCustomerId(clienteId));
    }

    // modificar reserva existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva", description = "Permite modificar numero de personas o estado de una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva actualizada"),
            @ApiResponse(responseCode = "400", description = "Cupos insuficientes para modificacion"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @Valid @RequestBody BookingUpdateDTO dto) {
        return ResponseEntity.ok(bookingService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar una reserva", description = "Cancela la reserva y libera los cupos del viaje")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        bookingService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
