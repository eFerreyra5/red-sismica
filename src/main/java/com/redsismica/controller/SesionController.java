package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.Sesion;
import com.redsismica.service.SesionService;

@RestController
@RequestMapping("/api/sesion")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping
    public List<Sesion> getAllSesiones() {
        return sesionService.getAll();
    }

    @GetMapping("/{id}")
    public Sesion getSesionById(@PathVariable Long id) {
        return sesionService.getById(id);
    }

    @PostMapping
    public Sesion createSesion(@RequestBody Sesion sesion) {
        return sesionService.save(sesion);
    }

    @PutMapping("/{id}")
    public Sesion updateSesion(@PathVariable Long id, @RequestBody Sesion sesion) {
        return sesionService.update(id, sesion);
    }

    @DeleteMapping("/{id}")
    public void deleteSesion(@PathVariable Long id) {
        sesionService.delete(id);
    }
}