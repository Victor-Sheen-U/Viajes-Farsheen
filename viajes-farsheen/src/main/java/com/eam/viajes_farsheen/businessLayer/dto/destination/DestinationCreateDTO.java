package com.eam.viajes_farsheen.businessLayer.dto.destination;

import io.swagger.v3.oas.annotations.media.
        Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos obligatorios para registrar un nuevo destination")

public class DestinationCreateDTO {

    @NotBlank(message = "El nombre del destino de viaje es obligatorio")
    @Size(min = 5, max = 100, message = "El destino de viaje debe de tener un tamaño entre 5 y 100 caracteres")
    @Schema(description = "Nombre que es asignado a el destino de viaje", example = "Taiwan", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "El nombre del pais es obligatorio")
    @Size(min = 5, max = 30, message = "El pais debe de tener un tamaño entre 5 y 30 caracteres")
    @Schema(description = "Nombre que es asignado a el pais del destino de viaje", example = "Colombia", requiredMode = Schema.RequiredMode.REQUIRED)
    private String country;

    @NotBlank(message = "El nombre de la ciudad es obligatoria")
    @Size(min = 5, max = 50, message = "La ciudad debe de tener un tamaño entre 5 y 50 caracteres")
    @Schema(description = "Nombre que es asignado a la ciudad del destino de viaje", example = "Medallo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String city;

    @Size(max = 150, message = "La descripcion puede tener un tamaño maximo de 150 caracteres")
    @Schema(description = "Descripcion añadida a el destino de viaje", example = "Esto es una descripcion muy elaborada de ejemplo para el destino de viaje")
    private String description;

    @Size(max = 462, message = "La informacion del clima puede tener un tamaño maximo de 462 caracteres")
    @Schema(description = "Informacion del clima del destino de viaje", example = "Principalmente clima tropical con algunas temporadas de veranos mas intensos o inviernos con mas probabilidad de lluvia (dato curioso la probabilidad de lluvia no significa cual es la probabilidad de que llueva si no que indica la cantidad en porcentaje de partes en la region en la que llovera por ejemplo el 50% de probabilidad de lluvia en armenia quiere decir que aproximadamente en la mitad de la ciudad empezara a llover hablando en terminos de territorio)")
    private String weather;





}
