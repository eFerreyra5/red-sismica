package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.MotivoTipo;
import com.redsismica.service.MotivoTipoService;

@RestController
@RequestMapping("/api/motivo-tipo")
public class MotivoTipoController {

    @Autowired
    private MotivoTipoService motivoTipoService;

    @GetMapping
    public List<MotivoTipo> getAllMotivosTipo() {
        return motivoTipoService.getAll();
    }

    @GetMapping("/{id}")
    public MotivoTipo getMotivoTipoById(@PathVariable Long id) {
        return motivoTipoService.getById(id);
    }

    @PostMapping
    public MotivoTipo createMotivoTipo(@RequestBody MotivoTipo motivoTipo) {
        return motivoTipoService.save(motivoTipo);
    }

    @PutMapping("/{id}")
    public MotivoTipo updateMotivoTipo(@PathVariable Long id, @RequestBody MotivoTipo motivoTipo) {
        return motivoTipoService.update(id, motivoTipo);
    }

    @DeleteMapping("/{id}")
    public void deleteMotivoTipo(@PathVariable Long id) {
        motivoTipoService.delete(id);
    }
}