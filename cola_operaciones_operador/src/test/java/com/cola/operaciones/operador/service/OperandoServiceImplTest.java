package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.repository.OperandoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OperandoServiceImplTest {

    @Mock
    private OperandoRepository operandoRepository;

    @InjectMocks
    private OperandoServiceImpl operandoService;

    @Test
    void addOperando() {
        Operando operando = new Operando();
        when(operandoRepository.save(any())).thenReturn(operando);
        operandoService.addOperando(operando);
        verify(operandoRepository).save(any());
    }

    @Test
    void findAll() {
        when(operandoRepository.findAll()).thenReturn(new ArrayList<>());
        operandoService.findAll();
        verify(operandoRepository).findAll();
    }

    @Test
    void findAllBySesionId() {
        when(operandoRepository.findAllBySesionId(anyString())).thenReturn(new ArrayList<>());
        operandoService.findAllBySesionId("sesionId");
        verify(operandoRepository).findAllBySesionId(anyString());
    }

    @Test
    void delete() {
        doNothing().when(operandoRepository).delete(any(Operando.class));
        operandoService.delete(new Operando());
        verify(operandoRepository).delete(any(Operando.class));
    }
}