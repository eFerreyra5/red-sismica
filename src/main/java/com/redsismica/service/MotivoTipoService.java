package com.redsismica.service;

import com.redsismica.model.MotivoTipo;
import com.redsismica.repository.MotivoTipoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class MotivoTipoService {

    @Autowired
    private MotivoTipoRepository repository;

    public List<MotivoTipo> getAll() {
        return repository.findAll();
    }

    public MotivoTipo getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MotivoTipo save(MotivoTipo motivoTipo) {
        return repository.save(motivoTipo);
    }

    public MotivoTipo update(Long id, MotivoTipo motivoTipo) {
        if (repository.existsById(id)) {
            motivoTipo.setId(id);
            return repository.save(motivoTipo);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}