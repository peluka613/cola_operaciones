package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.model.data.Operacion;
import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.operadores.OperadorConcreteFactory;
import com.cola.operaciones.operador.repository.OperacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OperacionServiceImplTest {

    public static final String TEST_SESION_ID = "testSesionId";

    @Mock
    private OperacionRepository operacionRepository;

    @Mock
    private OperandoService operandoService;

    @Mock
    private OperadorConcreteFactory operadorFactory;

    @InjectMocks
    private OperacionServiceImpl operacionService;

    @Test
    void realizarOperacionWhenAppendResult() {
        Operacion operacion = crearOperacionSuma();
        List<Operando> operandos = crearListaOperandos();

        when(operandoService.findAllBySesionId(operacion.getSesionId())).thenReturn(operandos);
        when(operadorFactory.crearOperador(operacion.getOperacion())).thenCallRealMethod();
        when(operacionRepository.save(any(Operacion.class))).thenReturn(operacion);
        doNothing().when(operandoService).delete(any(Operando.class));
        when(operandoService.addOperando(any(Operando.class))).thenReturn(null);

        operacionService.realizarOperacion(operacion, true);

        verify(operandoService).findAllBySesionId(operacion.getSesionId());
        verify(operacionRepository).save(any(Operacion.class));
        verify(operandoService, atLeastOnce()).delete(any(Operando.class));
        verify(operandoService).addOperando(any(Operando.class));
        assertEquals(5.0, operacion.getResultado());
    }

    @Test
    void realizarOperacionWhenNotAppendResult() {
        Operacion operacion = crearOperacionSuma();
        List<Operando> operandos = crearListaOperandos();

        when(operandoService.findAllBySesionId(operacion.getSesionId())).thenReturn(operandos);
        when(operadorFactory.crearOperador(operacion.getOperacion())).thenCallRealMethod();
        when(operacionRepository.save(any(Operacion.class))).thenReturn(operacion);
        doNothing().when(operandoService).delete(any(Operando.class));

        operacionService.realizarOperacion(operacion, false);

        verify(operandoService).findAllBySesionId(operacion.getSesionId());
        verify(operacionRepository).save(any(Operacion.class));
        verify(operandoService, atLeastOnce()).delete(any(Operando.class));
        assertEquals(5.0, operacion.getResultado());
    }

    @Test
    void realizarOperacionWhenInfinityResult() {
        Operacion operacion = crearOperacionSuma();
        operacion.setOperacion("division");
        List<Operando> operandos = crearListaOperandos();
        operandos.get(1).setValor(0.0);

        when(operandoService.findAllBySesionId(operacion.getSesionId())).thenReturn(operandos);
        when(operadorFactory.crearOperador(operacion.getOperacion())).thenCallRealMethod();
        assertThrows(IllegalArgumentException.class, () -> operacionService.realizarOperacion(operacion, false));
    }

    @Test
    void realizarOperacionWhenInsufficentOperadondos() {
        Operacion operacion = crearOperacionSuma();
        List<Operando> operandos = crearListaOperandos();
        operandos.remove(1);

        when(operandoService.findAllBySesionId(operacion.getSesionId())).thenReturn(operandos);
        assertThrows(IllegalArgumentException.class, () -> operacionService.realizarOperacion(operacion, false));
    }

    private Operacion crearOperacionSuma() {
        Operacion operacion = new Operacion();
        operacion.setSesionId(TEST_SESION_ID);
        operacion.setOperacion("suma");
        return operacion;
    }

    private List<Operando> crearListaOperandos() {
        List<Operando> operandos = new ArrayList<>();
        operandos.add(0, new Operando());
        operandos.get(0).setSesionId(TEST_SESION_ID);
        operandos.get(0).setValor(2.0);

        operandos.add(1, new Operando());
        operandos.get(1).setSesionId(TEST_SESION_ID);
        operandos.get(1).setValor(3.0);

        return operandos;
    }

    @Test
    void findAll() {
        when(operacionRepository.findAll()).thenReturn(new ArrayList<>());
        operacionService.findAll();
        verify(operacionRepository).findAll();
    }

    @Test
    void findAllBySesionId() {
        when(operacionRepository.findAllBySesionId(anyString())).thenReturn(new ArrayList<>());
        operacionService.findAllBySesionId("sesionId");
        verify(operacionRepository).findAllBySesionId(anyString());
    }
}