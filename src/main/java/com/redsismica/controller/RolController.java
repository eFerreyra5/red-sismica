package com.redsismica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.redsismica.model.Rol;
import com.redsismica.service.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> getAllRoles() {
        return rolService.getAll();
    }

    @GetMapping("/{id}")
    public Rol getRolById(@PathVariable Long id) {
        return rolService.getById(id);
    }

    @PostMapping
    public Rol createRol(@RequestBody Rol rol) {
        return rolService.save(rol);
    }

    @PutMapping("/{id}")
    public Rol updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        return rolService.update(id, rol);
    }

    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable Long id) {
        rolService.delete(id);
    }
}