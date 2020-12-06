package com.cola.operaciones.operador.utils;

import com.cola.operaciones.operador.exception.BadSesionIdException;
import com.cola.operaciones.operador.service.SesionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SesionUtils.class})
@ExtendWith(SpringExtension.class)
class SesionUtilsTest {

    @MockBean
    private SesionService sesionService;

    private SesionUtils sesionUtils;

    @Test
    void validateSesionIdWhenValidSesionId() {
        sesionUtils = new SesionUtils(sesionService);
        when(sesionService.countBySesionId(anyString())).thenReturn(1);
        sesionUtils.validateSesionId("testId");
        verify(sesionService).countBySesionId(anyString());
    }

    @Test()
    void validateSesionIdWhenInvalidSesionId() {
        sesionUtils = new SesionUtils(sesionService);
        when(sesionService.countBySesionId(anyString())).thenReturn(0);
        assertThrows(BadSesionIdException.class, () -> sesionUtils.validateSesionId("testId"));
    }
}