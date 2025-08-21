package com.redsismica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motivo_tipo")
public class MotivoTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    //constructor vacio para JPA
    public MotivoTipo() {}

    //constructor    
    public MotivoTipo(String descripcion) {
        this.descripcion = descripcion;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() { //metodo 26 secuencia
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

}
