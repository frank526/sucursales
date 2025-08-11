package com.prueba.sucursales_inventario.domain.exception;

public class PersistenceOperationException extends RuntimeException {
    public PersistenceOperationException(String message) {
        super(message);
    }

    public PersistenceOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
