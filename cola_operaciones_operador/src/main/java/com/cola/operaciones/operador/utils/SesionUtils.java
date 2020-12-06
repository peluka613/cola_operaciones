package com.cola.operaciones.operador.utils;

import com.cola.operaciones.operador.exception.BadSesionIdException;
import com.cola.operaciones.operador.service.SesionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Defaul scope singleton
@Log4j2
public class SesionUtils {

    private SesionService sesionService;

    @Value("${exception.bad.sesion.id}")
    private String badSesionIdMessage;

    @Value("${exception.bad.sesion.mensaje}")
    private String badSesionMessage;

    @Autowired
    public SesionUtils(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    public void validateSesionId(String sesionId) {
        synchronized (this) {
            if (sesionService.countBySesionId(sesionId) == 0) {
                log.info(badSesionMessage, sesionId);
                throw new BadSesionIdException(badSesionIdMessage);
            }
        }
    }
}
