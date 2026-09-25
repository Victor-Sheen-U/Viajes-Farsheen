package com.viajes.reservas.presentationLayer.controller;

import com.viajes.reservas.businessLayer.dto.cliente.ClienteCreateDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteDTO;
import com.viajes.reservas.businessLayer.dto.cliente.ClienteUpdateDTO;
import com.viajes.reservas.businessLayer.service.ClienteService;
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
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones de administración y registro de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Retorna el listado completo de clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retorna la información detallada de un cliente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/documento/{documento}")
    @Operation(summary = "Buscar cliente por documento", description = "Retorna los datos de un cliente según su número de documento de identidad")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO> getByDocumento(@PathVariable String documento) {
        return ResponseEntity.ok(clienteService.findByDocumentoIdentidad(documento));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo cliente", description = "Registra un cliente en el sistema validando unicidad de correo y documento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o email/documento duplicado")
    })
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO dto) {
        return new ResponseEntity<>(clienteService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de contacto y personales de un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDTO dto) {
        return ResponseEntity.ok(clienteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente registrado por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
