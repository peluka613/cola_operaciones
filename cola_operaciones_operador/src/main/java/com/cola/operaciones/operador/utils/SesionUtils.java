package com.cola.operaciones.operador.utils;

import com.cola.operaciones.operador.exception.BadSesionIdException;
import com.cola.operaciones.operador.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Defaul scope singleton
public class SesionUtils {

    private SesionService sesionService;

    @Value("${exception.bad.sesion.id}")
    private String badSesionIdMessage;

    @Autowired
    public SesionUtils(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    public void validateSesionId(String sesionId) {
        synchronized (this) {
            if (sesionService.countBySesionId(sesionId) == 0) {
                throw new BadSesionIdException(badSesionIdMessage);
            }
        }
    }
}
