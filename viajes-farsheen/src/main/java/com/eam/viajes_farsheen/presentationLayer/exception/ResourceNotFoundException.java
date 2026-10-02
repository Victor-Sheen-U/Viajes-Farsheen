package com.eam.viajes_farsheen.presentationLayer.exception;

// excepcion para cuando un recurso no existe (404)
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
