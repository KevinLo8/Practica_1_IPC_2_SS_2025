package com.Practica_1.Backend.Exception;

public class AnalizadorException extends Exception {

    public AnalizadorException() {
        super();
    }

    public AnalizadorException(String mensaje) {
        super(".\n  En la parte:" + mensaje);
    }

    public AnalizadorException(String string, String mensaje) {
        super(".\n  " + mensaje);
    }
}
