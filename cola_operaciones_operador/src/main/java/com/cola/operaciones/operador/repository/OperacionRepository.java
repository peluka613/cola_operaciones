package com.cola.operaciones.operador.repository;

import com.cola.operaciones.operador.model.data.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {
    List<Operacion> findAllBySesionId(String sesionId);
}
