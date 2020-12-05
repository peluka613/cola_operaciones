package com.cola.operaciones.sesion.service;

import com.cola.operaciones.sesion.model.data.Sesion;
import com.cola.operaciones.sesion.repository.SesionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SesionServiceImplTest {

    @Mock
    private SesionRepository sesionRepositoryDto;

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Test
    void createSesion() {
        Sesion sesion = new Sesion();
        when(sesionRepositoryDto.save(any())).thenReturn(sesion);

        Sesion returnedSesion = sesionService.createSesion();
        assertNotNull(returnedSesion.getSesionId());
    }

    @Test
    void findSesionBySesionId() {
        Sesion sesion = new Sesion();
        sesion.setId(1L);
        sesion.setSesionId(UUID.randomUUID().toString());
        when(sesionRepositoryDto.findBySesionId(anyString())).thenReturn(sesion);

        Sesion returnedSesion = sesionService.findSesionBySesionId(sesion.getSesionId());
        assertEquals(1L, returnedSesion.getId());
        assertEquals(sesion.getSesionId(), returnedSesion.getSesionId());
    }

    @Test
    void findAll() {
        List<Sesion> sesions = new ArrayList<>();
        when(sesionRepositoryDto.findAll()).thenReturn(sesions);

        assertNotNull(sesionService.findAll());
    }
}