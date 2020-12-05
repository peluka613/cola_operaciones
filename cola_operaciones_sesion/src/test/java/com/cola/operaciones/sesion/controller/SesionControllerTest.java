package com.cola.operaciones.sesion.controller;

import com.cola.operaciones.sesion.model.data.Sesion;
import com.cola.operaciones.sesion.service.SesionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SesionController.class)
class SesionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SesionService sesionService;

    @Test
    void sesiones() throws Exception {
        Sesion sesion = new Sesion();
        sesion.setSesionId(UUID.randomUUID().toString());
        List<Sesion> sesions = Arrays.asList(sesion);
        when(sesionService.findAll()).thenReturn(sesions);

        this.mockMvc.perform(get("/sesion"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(sesion.getSesionId())));
    }

    @Test
    void createSesion() throws Exception {
        Sesion sesion = new Sesion();
        sesion.setSesionId(UUID.randomUUID().toString());
        when(sesionService.createSesion()).thenReturn(sesion);

        this.mockMvc.perform(post("/sesion"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(sesion.getSesionId())));
    }
}