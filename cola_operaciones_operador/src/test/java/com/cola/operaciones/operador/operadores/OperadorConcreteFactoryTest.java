package com.cola.operaciones.operador.operadores;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OperadorConcreteFactoryTest {

    private OperadorConcreteFactory operadorConcreteFactory = new OperadorConcreteFactory();

    @Test
    void crearOperadorForSuma() {
        Operador operador = operadorConcreteFactory.crearOperador("suma");
        assertEquals(5.0, operador.realizarOperacion(2, 3));
    }

    @Test
    void crearOperadorForResta() {
        Operador operador = operadorConcreteFactory.crearOperador("resta");
        assertEquals(2.0, operador.realizarOperacion(5, 3));
    }

    @Test
    void crearOperadorForMultiplicacion() {
        Operador operador = operadorConcreteFactory.crearOperador("multiplicacion");
        assertEquals(6.0, operador.realizarOperacion(2, 3));
    }

    @Test
    void crearOperadorForDivision() {
        Operador operador = operadorConcreteFactory.crearOperador("division");
        assertEquals(2.0, operador.realizarOperacion(10, 5));
    }

    @Test
    void crearOperadorForDivisionByZero() {
        Operador operador = operadorConcreteFactory.crearOperador("division");
        assertTrue(Double.isInfinite(operador.realizarOperacion(10, 0)));
    }

    @Test
    void crearOperadorForPotenciacion() {
        Operador operador = operadorConcreteFactory.crearOperador("potenciacion");
        assertEquals(9.0, operador.realizarOperacion(3, 2));
    }

    @Test
    void crearOperadorForException() {
        assertThrows(IllegalArgumentException.class, () -> operadorConcreteFactory.crearOperador("any"));
    }
}