package com.viajes.reservas.businessLayer.dto.destino;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un Destino existente")
public class DestinoUpdateDTO {

    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del destino turístico", example = "Cartagena de Indias y Playas")
    private String nombre;

    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    @Schema(description = "País del destino", example = "Colombia")
    private String pais;

    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    @Schema(description = "Ciudad del destino", example = "Cartagena")
    private String ciudad;

    @Schema(description = "Descripción actualizada", example = "Nueva descripción de las playas")
    private String descripcion;

    @Size(max = 50, message = "El clima no puede exceder 50 caracteres")
    @Schema(description = "Clima representativo", example = "Cálido Tropical 32°C")
    private String clima;
}
