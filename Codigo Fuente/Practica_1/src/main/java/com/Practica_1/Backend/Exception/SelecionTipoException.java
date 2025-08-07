package com.Practica_1.Backend.Exception;

public class SelecionTipoException extends Exception {
    public SelecionTipoException(String message) {
        super(message);
    }
    
    public SelecionTipoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SelecionTipoException(Throwable cause) {
        super(cause);
    }

}
