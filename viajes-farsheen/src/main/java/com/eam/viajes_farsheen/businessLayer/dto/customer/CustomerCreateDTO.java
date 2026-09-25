package com.eam.viajes_farsheen.businessLayer.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos obligatorios para registrar un nuevo customer")

public class CustomerCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 80, message = "El nombre debe de tener un tamaño entre 3 y 80 caracteres")
    @Schema(description = "Nombre/s del customer", example = "Jhon Edison", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 80, message = "El apellido debe de tener un tamaño entre 3 y 80 caracteres")
    @Schema(description = "Apellido/s del customer", example = "Arias Zuleta", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastname;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo debe de tener un tamaño maximo de 100 caracteres")
    @Schema(description = "Correo unico del del customer", example = "victorarias@eam.edu.co", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;


    @Size(max = 20, message = "El telefono debe de tener un tamaño maximo de 20 digitos")
    @Pattern(regexp = "^[+0-9 ]*$", message = "El teléfono solo puede contener números, espacios o el signo +")
    @Schema(description = "Telefono unico del del customer", example = "+57 3136307251")
    private String phone;

    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(min = 5, max = 20, message = "El documento debe de tener un tamaño entre 5 y 20 caracteres")
    @Schema(description = "Documento de identificacion unico del del customer", example = "victorarias@eam.edu.co", requiredMode = Schema.RequiredMode.REQUIRED)
    private String identityDocument;






}
