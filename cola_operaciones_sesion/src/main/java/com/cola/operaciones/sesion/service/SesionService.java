package com.cola.operaciones.sesion.service;

import com.cola.operaciones.sesion.model.data.Sesion;

import java.util.List;

public interface SesionService {
    Sesion createSesion();
    Sesion findSesionBySesionId(String sesionId);
    List<Sesion> findAll();
}
