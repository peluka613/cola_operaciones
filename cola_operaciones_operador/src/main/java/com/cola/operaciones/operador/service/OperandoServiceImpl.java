package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.repository.OperandoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperandoServiceImpl implements OperandoService {

    private OperandoRepository operandoRepository;

    @Autowired
    public OperandoServiceImpl(OperandoRepository operandoRepository) {
        this.operandoRepository = operandoRepository;
    }

    @Override
    public Operando addOperando(Operando operando) {
        synchronized (this) {
            operandoRepository.save(operando);
        }
        return operando;
    }

    @Override
    public List<Operando> findAll() {
        return operandoRepository.findAll();
    }

    @Override
    public List<Operando> findAllBySesionId(String sesionId) {
        return operandoRepository.findAllBySesionId(sesionId);
    }

    @Override
    public void delete(Operando operando) {
        operandoRepository.delete(operando);
    }
}
