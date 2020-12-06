package com.cola.operaciones.operador.controller;

import com.cola.operaciones.operador.exception.BadSesionIdException;
import com.cola.operaciones.operador.model.data.Operacion;
import com.cola.operaciones.operador.service.OperacionService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperacionController.class)
class OperacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperacionService operacionService;

    @MockBean
    private SesionUtils sesionUtils;

    public static final String TEST_SESION_ID = "testSesionId";

    @Test
    void operacionesNullSesionId() throws Exception {
        Operacion operacion = new Operacion();
        operacion.setSesionId(TEST_SESION_ID);
        operacion.setOperacion("suma");
        List<Operacion> operacions = Arrays.asList(operacion);

        when(operacionService.findAll()).thenReturn(operacions);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operacion")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(operacion.getOperacion())));
    }

    @Test
    void operacionesValidSesionId() throws Exception {
        Operacion operacion = new Operacion();
        operacion.setSesionId(TEST_SESION_ID);
        operacion.setOperacion("suma");
        List<Operacion> operacions = Arrays.asList(operacion);

        when(operacionService.findAllBySesionId(TEST_SESION_ID)).thenReturn(operacions);
        doNothing().when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operacion")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .param("sesionId", TEST_SESION_ID);

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(operacion.getOperacion())));
    }

    @Test
    void operacionesInvalidSesionId() throws Exception {
        doThrow(BadSesionIdException.class).when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/operacion")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .param("sesionId", TEST_SESION_ID);

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void realizarOperacionValidSesionId() throws Exception {
        Operacion operacion = new Operacion();
        operacion.setSesionId(TEST_SESION_ID);
        operacion.setOperacion("suma");

        when(operacionService.realizarOperacion(any(), anyBoolean())).thenReturn(operacion);
        doNothing().when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/operacion")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .content("{\"sesionId\":\"" + TEST_SESION_ID + "\",\"operacion\":\"suma\"}");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(operacion.getOperacion())));
    }

    @Test
    void realizarOperacionInvalidSesionId() throws Exception {
        doThrow(BadSesionIdException.class).when(sesionUtils).validateSesionId(TEST_SESION_ID);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/operacion")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .content("{\"sesionId\":\"" + TEST_SESION_ID + "\",\"operacion\":\"suma\"}");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}