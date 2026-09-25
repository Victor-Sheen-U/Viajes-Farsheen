package com.eam.viajes_farsheen.businessLayer.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos modificables del customer")

public class CustomerUpdateDTO {

    @NotBlank(message = "El nombre no puede estar vacio si lo quiere actualizar")
    @Size(min = 3, max = 80, message = "El nombre debe de tener un tamaño entre 3 y 80 caracteres")
    @Schema(description = "Nombre/s actualizados", example = "bicho verde")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacio si lo quiere actualizar")
    @Size(min = 3, max = 80, message = "El apellido debe de tener un tamaño entre 3 y 80 caracteres")
    @Schema(description = "Apellido/s actualizados", example = "hmnn nose")
    private String lastname;

    @Size(max = 20, message = "El telefono debe de tener un tamaño maximo de 20 digitos")
    @Pattern(regexp = "^[+0-9 ]*$", message = "El teléfono solo puede contener números, espacios o el signo +")
    @Schema(description = "Nuevo número de telefono del customer", example = "+57 3119876543")
    private String phone;



}
