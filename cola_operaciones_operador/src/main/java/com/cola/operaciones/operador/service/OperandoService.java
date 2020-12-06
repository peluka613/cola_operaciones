package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operando;

import java.util.List;

public interface OperandoService {
    Operando addOperando(Operando operando);
    List<Operando> findAll();
    List<Operando> findAllBySesionId(String sesionId);
    void delete(Operando operando);
}
