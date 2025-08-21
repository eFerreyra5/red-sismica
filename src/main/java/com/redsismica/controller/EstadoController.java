package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.Estado;
import com.redsismica.service.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> getAllEstados() {
        return estadoService.getAll();
    }

    @GetMapping("/{id}")
    public Estado getEstadotById(@PathVariable Long id) {
        return estadoService.getById(id);
    }

    @PostMapping
    public Estado createEstado(@RequestBody Estado estado) {
        return estadoService.save(estado);
    }

    @PutMapping("/{id}")
    public Estado updateEstado(@PathVariable Long id, @RequestBody Estado estado) {
        return estadoService.update(id, estado);
    }

    @DeleteMapping("/{id}")
    public void deleteEstado(@PathVariable Long id) {
        estadoService.delete(id);
    }
}