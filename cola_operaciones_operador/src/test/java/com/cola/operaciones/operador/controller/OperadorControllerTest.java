package com.cola.operaciones.operador.controller;

import com.cola.operaciones.operador.exception.BadSesionIdException;
import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.service.OperandoService;
import com.cola.operaciones.operador.utils.SesionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperadorController.class)
class OperadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperandoService operandoService;

    @MockBean
    private SesionUtils sesionUtils;

    public static final String TEST_SESION_ID = "testSesionId";

    @Test
    void operandosNullSesionId() throws Exception {
        Operando operando = new Operando();
        operando.setSesionId(TEST_SESION_ID);
        operando.setValor(1.0);
        List<Operando> operandos = Arrays.asList(operando);

        when(operandoService.findAll()).thenReturn(operandos);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operando")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(operando.getValor().toString())));
    }

    @Test
    void operandosValidSesionId() throws Exception {
        Operando operando = new Operando();
        operando.setSesionId(TEST_SESION_ID);
        operando.setValor(1.0);
        List<Operando> operandos = Arrays.asList(operando);

        when(operandoService.findAllBySesionId(TEST_SESION_ID)).thenReturn(operandos);
        doNothing().when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operando")
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .param("sesionId", TEST_SESION_ID);

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(operando.getValor().toString())));
    }

    @Test
    void operandosInvalidSesionId() throws Exception {
        doThrow(BadSesionIdException.class).when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operando")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .param("sesionId", TEST_SESION_ID);

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void addOperandoValidSesionId() throws Exception {
        Operando operando = new Operando();
        operando.setSesionId(TEST_SESION_ID);
        operando.setValor(5.0);

        when(operandoService.addOperando(any())).thenReturn(operando);
        doNothing().when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/operando")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .content("{\"sesionId\":\"" + TEST_SESION_ID + "\",\"valor\":5}");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(operando.getValor().toString())));
    }

    @Test
    void addOperandoInvalidSesionId() throws Exception {
        doThrow(BadSesionIdException.class).when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/operando")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .content("{\"sesionId\":\"" + TEST_SESION_ID + "\",\"valor\":5}");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}