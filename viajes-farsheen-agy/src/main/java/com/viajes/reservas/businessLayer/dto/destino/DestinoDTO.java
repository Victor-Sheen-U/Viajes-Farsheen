package com.viajes.reservas.businessLayer.dto.destino;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos con información de un Destino")
public class DestinoDTO {

    @Schema(description = "ID único del destino", example = "1")
    private Long id;

    @Schema(description = "Nombre descriptivo del destino turístico", example = "Cartagena Colonial y Playas")
    private String nombre;

    @Schema(description = "País del destino", example = "Colombia")
    private String pais;

    @Schema(description = "Ciudad del destino", example = "Cartagena")
    private String ciudad;

    @Schema(description = "Descripción detallada del destino", example = "Disfruta de la ciudad amurallada y las islas del Rosario")
    private String descripcion;

    @Schema(description = "Clima predominante", example = "Cálido Tropical")
    private String clima;
}
