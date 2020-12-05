package com.cola.operaciones.operador.repository;

import com.cola.operaciones.operador.model.data.Operando;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperandoRepository extends JpaRepository<Operando, Long> {
    List<Operando> findAllBySesionId(String sesionId);
}
