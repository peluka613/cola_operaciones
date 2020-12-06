package com.cola.operaciones.operador.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionResponseDto {
    private String operacion;
    private Double primerOperando;
    private Double segundoOperando;
    private Double resultado;
}
