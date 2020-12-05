package com.cola.operaciones.sesion.service;

import com.cola.operaciones.sesion.model.data.Sesion;
import com.cola.operaciones.sesion.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SesionServiceImpl implements SesionService {

    private SesionRepository sesionRepository;

    @Autowired
    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public Sesion createSesion() {
        Sesion sesion = new Sesion();
        sesion.setSesionId(UUID.randomUUID().toString());
        synchronized (this) {
            sesionRepository.save(sesion);
        }
        return sesion;
    }

    @Override
    public Sesion findSesionBySesionId(String sesionId) {
        return sesionRepository.findBySesionId(sesionId);
    }

    @Override
    public List<Sesion> findAll() {
        return sesionRepository.findAll();
    }
}
