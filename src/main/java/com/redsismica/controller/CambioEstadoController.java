package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.CambioEstado;
import com.redsismica.service.CambioEstadoService;

@RestController
@RequestMapping("/api/cambio-estado")
public class CambioEstadoController {

    @Autowired
    private CambioEstadoService cambioEstadoService;

    @GetMapping
    public List<CambioEstado> getAllCambios() {
        return cambioEstadoService.getAll();
    }

    @GetMapping("/{id}")
    public CambioEstado getCambiotById(@PathVariable Long id) {
        return cambioEstadoService.getById(id);
    }

    @PostMapping
    public CambioEstado createCambio(@RequestBody CambioEstado cambio) {
        return cambioEstadoService.save(cambio);
    }

    @PutMapping("/{id}")
    public CambioEstado updateCambio(@PathVariable Long id, @RequestBody CambioEstado cambio) {
        return cambioEstadoService.update(id, cambio);
    }

    @DeleteMapping("/{id}")
    public void deleteCambio(@PathVariable Long id) {
        cambioEstadoService.delete(id);
    }
}