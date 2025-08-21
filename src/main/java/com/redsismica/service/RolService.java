package com.redsismica.service;

import com.redsismica.model.Rol;
import com.redsismica.repository.RolRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public List<Rol> getAll() {
        return repository.findAll();
    }

    public Rol getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    public Rol update(Long id, Rol rol) {
        if (repository.existsById(id)) {
            rol.setId(id);
            return repository.save(rol);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}