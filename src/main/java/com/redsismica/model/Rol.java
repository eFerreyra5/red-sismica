package com.redsismica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion_rol", nullable = false)
    private String descripcionRol;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    //constructor vacio para JPA
    public Rol() {}

    //constructor
    public Rol(String descripcionRol, String nombre) {
        this.descripcionRol = descripcionRol;
        this.nombre = nombre;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //metodo 61 secuencia
    public boolean esResponsableReparacion(){
        return this.getNombre().equalsIgnoreCase("Responsable de Reparacion");
    }

    
}
