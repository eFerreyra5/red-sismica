package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.service.OrdenDeInspeccionService;

@RestController
@RequestMapping("/api/orden-de-inspeccion")
public class OrdenDeInspeccionController {

    @Autowired
    private OrdenDeInspeccionService ordenDeInspeccionService;

    @GetMapping
    public List<OrdenDeInspeccion> getAllOrdenes() {
        return ordenDeInspeccionService.getAll();
    }

    @GetMapping("/{id}")
    public OrdenDeInspeccion getOrdenById(@PathVariable Long id) {
        return ordenDeInspeccionService.getById(id);
    }

    @PostMapping
    public OrdenDeInspeccion createOrden(@RequestBody OrdenDeInspeccion orden) {
        return ordenDeInspeccionService.save(orden);
    }

    @PutMapping("/{id}")
    public OrdenDeInspeccion updateOrden(@PathVariable Long id, @RequestBody OrdenDeInspeccion orden) {
        return ordenDeInspeccionService.update(id, orden);
    }

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        ordenDeInspeccionService.delete(id);
    }
}