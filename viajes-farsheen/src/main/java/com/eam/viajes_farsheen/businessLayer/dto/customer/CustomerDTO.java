package com.eam.viajes_farsheen.businessLayer.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Descriptor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion publica en las consultas del customer")



public class CustomerDTO {

    @Schema(description = "El identificador unico que se asigna en el sistema", example = 1, accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre/s del customer", example = "Victor Sheen")
    private String name;

    @Schema(description = "Apellido/s del customer", example = "Farfan Aranzazu")
    private String lastname;

    @Schema(description = "Numero de telefono del customer", example = "+57 3137294712")
    private String phone;

    @Schema(description = "Documento de identidad ofical del customer (Cedula o pasaporte)", example = "1092458721")
    private String identityDocument;
}
