package com.cola.operaciones.operador.operadores;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OperadorConcreteFactory implements OperadorFactory {

    @Value("${exception.wrong.operator}")
    private String wronOperator;

    @Value("${exception.wrong.operator.message}")
    private String wronOperatorMessage;

    @Override
    public Operador crearOperador(String operacion) {
        Operador operador = null;

        switch (operacion) {
            case "suma":
                operador = (primerOperando, segundoOperando) -> primerOperando + segundoOperando;
                break;
            case "resta":
                operador = (primerOperando, segundoOperando) -> primerOperando - segundoOperando;
                break;
            case "multiplicacion":
                operador = (primerOperando, segundoOperando) -> primerOperando * segundoOperando;
                break;
            case "division":
                operador = (primerOperando, segundoOperando) -> primerOperando / segundoOperando;
                break;
            case "potenciacion":
                operador = (primerOperando, segundoOperando) -> Math.pow(primerOperando, segundoOperando);
                break;
            default:
                log.error(wronOperatorMessage, operacion);
                throw new IllegalArgumentException(wronOperator);
        }

        return operador;
    }
}
