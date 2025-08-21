package com.redsismica.service;

import com.redsismica.model.MotivoFueraDeServicio;
import com.redsismica.repository.MotivoFueraDeServicioRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class MotivoFueraDeServicioService {

    @Autowired
    private MotivoFueraDeServicioRepository repository;

    public List<MotivoFueraDeServicio> getAll() {
        return repository.findAll();
    }

    public MotivoFueraDeServicio getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MotivoFueraDeServicio save(MotivoFueraDeServicio motivo) {
        return repository.save(motivo);
    }

    public MotivoFueraDeServicio update(Long id, MotivoFueraDeServicio motivo) {
        if (repository.existsById(id)) {
            motivo.setId(id);
            return repository.save(motivo);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}