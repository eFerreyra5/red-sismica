package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.Sismografo;
import com.redsismica.service.SismografoService;

@RestController
@RequestMapping("/api/sismografo")
public class SismografoController {

    @Autowired
    private SismografoService sismografoService;

    @GetMapping
    public List<Sismografo> getAllSismografos() {
        return sismografoService.getAll();
    }

    @GetMapping("/{id}")
    public Sismografo getSismografoById(@PathVariable Long id) {
        return sismografoService.getById(id);
    }

    @PostMapping
    public Sismografo createSismografo(@RequestBody Sismografo sismografo) {
        return sismografoService.save(sismografo);
    }

    @PutMapping("/{id}")
    public Sismografo updateSismografo(@PathVariable Long id, @RequestBody Sismografo sismografo) {
        return sismografoService.update(id, sismografo);
    }

    @DeleteMapping("/{id}")
    public void deleteSismografo(@PathVariable Long id) {
        sismografoService.delete(id);
    }
}