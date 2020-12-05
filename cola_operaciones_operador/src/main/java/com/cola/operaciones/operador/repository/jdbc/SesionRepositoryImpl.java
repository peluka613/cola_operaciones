package com.cola.operaciones.operador.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SesionRepositoryImpl implements SesionRepository {

    @Value("${jdbc.sesion.query.count}")
    private String countQuery;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SesionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer countBySesionId(String sesionId) {
        return jdbcTemplate.queryForObject(countQuery, new Object[]{sesionId}, Integer.class);
    }

}
