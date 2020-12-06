package com.cola.operaciones.operador.repository.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SesionRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private SesionRepositoryImpl repository;

    @Test
    void countBySesionId() {
        repository = new SesionRepositoryImpl(jdbcTemplate);
        when(jdbcTemplate.queryForObject(any(),any(),any(Class.class))).thenReturn(1);
        assertEquals(1, repository.countBySesionId("sesionId"));
    }
}