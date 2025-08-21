package com.redsismica.service;

import com.redsismica.model.Sismografo;
import com.redsismica.repository.SismografoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class SismografoService {

    @Autowired
    private SismografoRepository repository;

    public List<Sismografo> getAll() {
        return repository.findAll();
    }

    public Sismografo getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Sismografo save(Sismografo sismografo) {
        return repository.save(sismografo);
    }

    public Sismografo update(Long id, Sismografo sismografo) {
        if (repository.existsById(id)) {
            sismografo.setId(id);
            return repository.save(sismografo);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}