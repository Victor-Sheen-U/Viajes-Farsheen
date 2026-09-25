package com.viajes.reservas.businessLayer.dto.destino;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos requeridos para crear un nuevo Destino")
public class DestinoCreateDTO {

    @NotBlank(message = "El nombre del destino no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del destino turístico", example = "Cartagena Colonial y Playas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    @Schema(description = "País donde se ubica el destino", example = "Colombia", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pais;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    @Schema(description = "Ciudad donde se ubica el destino", example = "Cartagena", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ciudad;

    @Schema(description = "Descripción del atractivo turístico", example = "Disfruta de la ciudad amurallada y las islas del Rosario")
    private String descripcion;

    @Size(max = 50, message = "El clima no puede exceder 50 caracteres")
    @Schema(description = "Clima representativo del destino", example = "Cálido Tropical")
    private String clima;
}
