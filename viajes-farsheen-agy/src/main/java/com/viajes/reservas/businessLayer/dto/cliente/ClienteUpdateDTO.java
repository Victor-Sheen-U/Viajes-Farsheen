package com.viajes.reservas.businessLayer.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un Cliente existente")
public class ClienteUpdateDTO {

    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    @Schema(description = "Nombre del cliente", example = "Carlos Andres")
    private String nombre;

    @Size(max = 80, message = "El apellido no puede superar 80 caracteres")
    @Schema(description = "Apellido del cliente", example = "Gomez Perez")
    private String apellido;

    @Email(message = "El formato de email no es válido")
    @Size(max = 100, message = "El email no puede superar 100 caracteres")
    @Schema(description = "Correo electrónico del cliente", example = "carlos.andres@mail.com")
    private String email;

    @Size(max = 30, message = "El teléfono no puede superar 30 caracteres")
    @Schema(description = "Teléfono de contacto", example = "+57 3009876543")
    private String telefono;

    @Size(max = 50, message = "El documento no puede superar 50 caracteres")
    @Schema(description = "Documento de identidad", example = "10203040")
    private String documentoIdentidad;
}
