package com.redsismica.service;

import com.redsismica.model.Empleado;
import com.redsismica.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    public List<Empleado> getAll() {
        return repository.findAll();
    }

    public Empleado getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Empleado save(Empleado empleado) {
        return repository.save(empleado);
    }

    public Empleado update(Long id, Empleado empleado) {
        if (repository.existsById(id)) {
            empleado.setId(id);
            return repository.save(empleado);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}