package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operacion;
import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.operadores.Operador;
import com.cola.operaciones.operador.operadores.OperadorFactory;
import com.cola.operaciones.operador.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class OperacionServiceImpl implements OperacionService {

    private OperacionRepository operacionRepository;
    private OperandoService operandoService;
    private OperadorFactory operadorFactory;

    @Value("${exception.insufficient.operands}")
    private String insufficientOperandsMessage;

    @Autowired
    public OperacionServiceImpl(OperacionRepository operacionRepository, OperandoService operandoService, OperadorFactory operadorFactory) {
        this.operacionRepository = operacionRepository;
        this.operandoService = operandoService;
        this.operadorFactory = operadorFactory;
    }

    @Override
    public Operacion realizarOperacion(Operacion operacion, boolean appendResult) {
        List<Operando> operandos = operandoService.findAllBySesionId(operacion.getSesionId());

        synchronized (operandos) {
            try {
                final Queue<Operando> operandoQueue = new LinkedBlockingQueue<>(operandos);
                Operando primerOperando = operandoQueue.remove();
                Operando segundoOperando = operandoQueue.remove();

                operacion.setPrimerOperando(primerOperando.getValor());
                operacion.setSegundoOperando(segundoOperando.getValor());

                Operador operador = operadorFactory.crearOperador(operacion.getOperacion());
                operacion.setResultado(operador.realizarOperacion(operacion.getPrimerOperando(), operacion.getSegundoOperando()));

                operacionRepository.save(operacion);
                operandoService.delete(primerOperando);
                operandoService.delete(segundoOperando);
                if (appendResult) {
                    Operando nuevoOperando = new Operando();
                    nuevoOperando.setSesionId(operacion.getSesionId());
                    nuevoOperando.setValor(operacion.getResultado());
                    operandoService.addOperando(nuevoOperando);
                }
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(insufficientOperandsMessage, e);
            }
        }

        return operacion;
    }

    @Override
    public List<Operacion> findAll() {
        return operacionRepository.findAll();
    }

    @Override
    public List<Operacion> findAllBySesionId(String sesionId) {
        return operacionRepository.findAllBySesionId(sesionId);
    }
}
