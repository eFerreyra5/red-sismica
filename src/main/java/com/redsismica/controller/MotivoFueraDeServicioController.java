package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.MotivoFueraDeServicio;
import com.redsismica.service.MotivoFueraDeServicioService;

@RestController
@RequestMapping("/api/motivo-fuera-de-servicio")
public class MotivoFueraDeServicioController {

    @Autowired
    private MotivoFueraDeServicioService motivoFueraDeServicioService;

    @GetMapping
    public List<MotivoFueraDeServicio> getAllMotivos() {
        return motivoFueraDeServicioService.getAll();
    }

    @GetMapping("/{id}")
    public MotivoFueraDeServicio getMotivoById(@PathVariable Long id) {
        return motivoFueraDeServicioService.getById(id);
    }

    @PostMapping
    public MotivoFueraDeServicio createMotivo(@RequestBody MotivoFueraDeServicio motivo) {
        return motivoFueraDeServicioService.save(motivo);
    }

    @PutMapping("/{id}")
    public MotivoFueraDeServicio updateMotivo(@PathVariable Long id, @RequestBody MotivoFueraDeServicio motivo) {
        return motivoFueraDeServicioService.update(id, motivo);
    }

    @DeleteMapping("/{id}")
    public void deleteMotivo(@PathVariable Long id) {
        motivoFueraDeServicioService.delete(id);
    }
}