package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operacion;

import java.util.List;

public interface OperacionService {
    Operacion realizarOperacion(Operacion operacion, boolean appendResult);
    List<Operacion> findAll();
    List<Operacion> findAllBySesionId(String sesionId);
}
