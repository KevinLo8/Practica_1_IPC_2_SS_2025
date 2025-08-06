package com.Practica_1.Backend.Exception;

public class TipoPagoException extends Exception {
    public TipoPagoException(String message) {
        super(message);
    }
    
    public TipoPagoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public TipoPagoException(Throwable cause) {
        super(cause);
    }

}
