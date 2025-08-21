package com.redsismica.service;

import com.redsismica.model.CambioEstado;
import com.redsismica.repository.CambioEstadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class CambioEstadoService {

    @Autowired
    private CambioEstadoRepository repository;

    public List<CambioEstado> getAll() {
        return repository.findAll();
    }

    public CambioEstado getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CambioEstado save(CambioEstado cambioEstado) {
        return repository.save(cambioEstado);
    }

    public CambioEstado update(Long id, CambioEstado cambioEstado) {
        if (repository.existsById(id)) {
            cambioEstado.setId(id);
            return repository.save(cambioEstado);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}