package com.viajes.reservas.businessLayer.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con los datos de un Cliente")
public class ClienteDTO {

    @Schema(description = "ID único del cliente", example = "1")
    private Long id;

    @Schema(description = "Nombre(s) del cliente", example = "Carlos")
    private String nombre;

    @Schema(description = "Apellido(s) del cliente", example = "Gomez")
    private String apellido;

    @Schema(description = "Correo electrónico del cliente", example = "carlos.gomez@mail.com")
    private String email;

    @Schema(description = "Número telefónico de contacto", example = "+57 3001234567")
    private String telefono;

    @Schema(description = "Documento de identidad o cédula", example = "10203040")
    private String documentoIdentidad;
}
