package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.EstacionSismologica;
import com.redsismica.service.EstacionSismologicaService;

@RestController
@RequestMapping("/api/estacion-sismologica")
public class EstacionSismologicaController {

    @Autowired
    private EstacionSismologicaService estacionSismologicaService;

    @GetMapping
    public List<EstacionSismologica> getAllEstaciones() {
        return estacionSismologicaService.getAll();
    }

    @GetMapping("/{id}")
    public EstacionSismologica getEstacionById(@PathVariable Long id) {
        return estacionSismologicaService.getById(id);
    }

    @PostMapping
    public EstacionSismologica createEstacion(@RequestBody EstacionSismologica estacion) {
        return estacionSismologicaService.save(estacion);
    }

    @PutMapping("/{id}")
    public EstacionSismologica updateEstacion(@PathVariable Long id, @RequestBody EstacionSismologica estacion) {
        return estacionSismologicaService.update(id, estacion);
    }

    @DeleteMapping("/{id}")
    public void deleteEstacion(@PathVariable Long id) {
        estacionSismologicaService.delete(id);
    }
}