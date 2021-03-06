package com.cola.operaciones.operador.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OperandoRequestDto {

    @NotNull
    private String sesionId;

    @NotNull
    private Double valor;
}
