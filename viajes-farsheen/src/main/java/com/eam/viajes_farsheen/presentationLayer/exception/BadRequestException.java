package com.eam.viajes_farsheen.presentationLayer.exception;

// excepcion para datos invalidos o cupos insuficientes (400)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
