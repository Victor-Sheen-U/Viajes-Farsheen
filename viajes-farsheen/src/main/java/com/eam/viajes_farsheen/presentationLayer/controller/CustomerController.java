package com.eam.viajes_farsheen.presentationLayer.controller;

import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerCreateDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerDTO;
import com.eam.viajes_farsheen.businessLayer.dto.customer.CustomerUpdateDTO;
import com.eam.viajes_farsheen.businessLayer.service.CustomerService;
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
@RequestMapping({"/api/v1/clientes", "/api/v1/customers"})
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gestion y registro de clientes en la plataforma")
public class CustomerController {

    private final CustomerService customerService;

    // listar todos los clientes
    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna el listado completo de clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    // obtener cliente por su id
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna el detalle de un cliente dado su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    // registrar nuevo cliente
    @PostMapping
    @Operation(summary = "Registrar cliente", description = "Crea un nuevo cliente validando documento y correo unico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente registrado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos o correo/cedula ya existentes")
    })
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerCreateDTO dto) {
        return new ResponseEntity<>(customerService.create(dto), HttpStatus.CREATED);
    }

    // actualizar informacion de un cliente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Modifica los datos basicos de un cliente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDTO dto) {
        return ResponseEntity.ok(customerService.update(id, dto));
    }

    // eliminar cliente
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
