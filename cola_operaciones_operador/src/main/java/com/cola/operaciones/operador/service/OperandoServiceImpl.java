package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.repository.OperandoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OperandoServiceImpl implements OperandoService {

    private OperandoRepository operandoRepository;

    @Value("${message.new.operando}")
    private String newOperandoMessage;

    @Autowired
    public OperandoServiceImpl(OperandoRepository operandoRepository) {
        this.operandoRepository = operandoRepository;
    }

    @Override
    public Operando addOperando(Operando operando) {
        synchronized (this) {
            operandoRepository.save(operando);
            log.info(newOperandoMessage, operando.getSesionId(), operando.getValor());
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
