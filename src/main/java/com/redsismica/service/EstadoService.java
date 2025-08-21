package com.redsismica.service;

import com.redsismica.model.Estado;
import com.redsismica.repository.EstadoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> getAll() {
        return repository.findAll();
    }

    public Estado getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estado save(Estado estado) {
        return repository.save(estado);
    }

    public Estado update(Long id, Estado estado) {
        if (repository.existsById(id)) {
            estado.setId(id);
            return repository.save(estado);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}