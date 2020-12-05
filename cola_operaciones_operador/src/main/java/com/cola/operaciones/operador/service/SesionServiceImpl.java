package com.cola.operaciones.operador.service;

import com.cola.operaciones.operador.repository.jdbc.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionServiceImpl implements SesionService {

    private SesionRepository sesionRepository;

    @Autowired
    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public int countBySesionId(String sesionId) {
        Integer count = sesionRepository.countBySesionId(sesionId);
        return count != null ? count : 0;
    }
}
