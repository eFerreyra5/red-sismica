package com.redsismica.service;

import com.redsismica.model.EstacionSismologica;
import com.redsismica.repository.EstacionSismologicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class EstacionSismologicaService {

    @Autowired
    private EstacionSismologicaRepository repository;

    public List<EstacionSismologica> getAll() {
        return repository.findAll();
    }

    public EstacionSismologica getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EstacionSismologica save(EstacionSismologica estacion) {
        return repository.save(estacion);
    }

    public EstacionSismologica update(Long id, EstacionSismologica estacion) {
        if (repository.existsById(id)) {
            estacion.setCodigoEstacion(id);
            return repository.save(estacion);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}