package com.viajes.reservas.businessLayer.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos requeridos para registrar un nuevo Cliente")
public class ClienteCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    @Schema(description = "Nombre del cliente", example = "Carlos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede superar 80 caracteres")
    @Schema(description = "Apellido del cliente", example = "Gomez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato de email no es válido")
    @Size(max = 100, message = "El email no puede superar 100 caracteres")
    @Schema(description = "Correo electrónico del cliente", example = "carlos.gomez@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Size(max = 30, message = "El teléfono no puede superar 30 caracteres")
    @Schema(description = "Teléfono de contacto", example = "+57 3001234567")
    private String telefono;

    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(max = 50, message = "El documento no puede superar 50 caracteres")
    @Schema(description = "Documento de identidad / Cédula / Pasaporte", example = "10203040", requiredMode = Schema.RequiredMode.REQUIRED)
    private String documentoIdentidad;
}
