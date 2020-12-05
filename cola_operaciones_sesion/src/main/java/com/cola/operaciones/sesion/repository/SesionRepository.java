package com.cola.operaciones.sesion.repository;

import com.cola.operaciones.sesion.model.data.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    Sesion findBySesionId(String sesionId);
}
