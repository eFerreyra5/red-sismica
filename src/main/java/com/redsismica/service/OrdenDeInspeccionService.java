package com.redsismica.service;

import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.repository.OrdenDeInspeccionRepository;
import com.redsismica.model.Estado;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenDeInspeccionService {

    @Autowired
    private OrdenDeInspeccionRepository repository;

    public List<OrdenDeInspeccion> getAll() {
        return repository.findAll();
    }

    public OrdenDeInspeccion getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public OrdenDeInspeccion save(OrdenDeInspeccion orden) {
        return repository.save(orden);
    }

    public OrdenDeInspeccion update(Long id, OrdenDeInspeccion orden) {
        if (repository.existsById(id)) {
            orden.setNumeroOrden(id);
            return repository.save(orden);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public OrdenDeInspeccion cerrarOrden(long numeroOrden, Estado nuevoEstado, String observacionCierre) {
        OrdenDeInspeccion orden = repository.findById(numeroOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.cerrar(nuevoEstado, LocalDateTime.now(), observacionCierre);
        return repository.save(orden);
    }
}