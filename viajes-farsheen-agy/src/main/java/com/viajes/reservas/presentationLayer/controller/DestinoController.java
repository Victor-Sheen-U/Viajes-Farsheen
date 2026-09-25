package com.viajes.reservas.presentationLayer.controller;

import com.viajes.reservas.businessLayer.dto.destino.DestinoCreateDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoDTO;
import com.viajes.reservas.businessLayer.dto.destino.DestinoUpdateDTO;
import com.viajes.reservas.businessLayer.service.DestinoService;
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
@RequestMapping("/api/v1/destinos")
@RequiredArgsConstructor
@Tag(name = "Destinos", description = "Operaciones de administración y consulta de destinos turísticos")
public class DestinoController {

    private final DestinoService destinoService;

    @GetMapping
    @Operation(summary = "Listar todos los destinos", description = "Retorna el catálogo completo de destinos turísticos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de destinos obtenida exitosamente")
    public ResponseEntity<List<DestinoDTO>> getAll() {
        return ResponseEntity.ok(destinoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener destino por ID", description = "Retorna la información detallada de un destino turístico específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Destino encontrado"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    public ResponseEntity<DestinoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.findById(id));
    }

    @GetMapping("/pais/{pais}")
    @Operation(summary = "Filtrar destinos por país", description = "Retorna los destinos turísticos correspondientes al país indicado")
    @ApiResponse(responseCode = "200", description = "Destinos filtrados obtenidos exitosamente")
    public ResponseEntity<List<DestinoDTO>> getByPais(@PathVariable String pais) {
        return ResponseEntity.ok(destinoService.findByPais(pais));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo destino", description = "Registra un nuevo destino turístico en el catálogo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Destino creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<DestinoDTO> create(@Valid @RequestBody DestinoCreateDTO dto) {
        return new ResponseEntity<>(destinoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar destino", description = "Modifica los atributos de un destino turístico existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Destino actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    public ResponseEntity<DestinoDTO> update(@PathVariable Long id, @Valid @RequestBody DestinoUpdateDTO dto) {
        return ResponseEntity.ok(destinoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar destino", description = "Elimina un destino turístico por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Destino eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        destinoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
