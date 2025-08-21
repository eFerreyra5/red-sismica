package com.redsismica.service;

import com.redsismica.model.Sesion;
import com.redsismica.repository.SesionRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class SesionService {

    @Autowired
    private SesionRepository repository;

    public List<Sesion> getAll() {
        return repository.findAll();
    }

    public Sesion getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Sesion save(Sesion sesion) {
        return repository.save(sesion);
    }

    public Sesion update(Long id, Sesion sesion) {
        if (repository.existsById(id)) {
            sesion.setId(id);
            return repository.save(sesion);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Sesion cerrarSesion(Long id) {
        Sesion sesion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));
        sesion.setFechaHoraFin(LocalDateTime.now());
        return repository.save(sesion);
    }
}