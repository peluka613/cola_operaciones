package com.cola.operaciones.operador.exception;

import java.security.PrivilegedActionException;

public class BadSesionIdException extends IllegalArgumentException {
    public BadSesionIdException(String s) {
        super(s);
    }
}
