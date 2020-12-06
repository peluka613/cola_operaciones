package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.repository.jdbc.SesionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SesionServiceImplTest {

    @Mock
    private SesionRepository sesionRepository;

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Test
    void countBySesionIdWhenNotNullResult() {
        when(sesionRepository.countBySesionId(any())).thenReturn(1);
        assertEquals(1.0, sesionService.countBySesionId("sesionId"));
    }

    @Test
    void countBySesionIdWhenNullResult() {
        when(sesionRepository.countBySesionId(any())).thenReturn(null);
        assertEquals(0.0, sesionService.countBySesionId("sesionId"));
    }
}