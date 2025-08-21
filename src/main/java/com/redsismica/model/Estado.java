package com.redsismica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ambito", nullable = false)
    private String ambito;

    @Column(name = "nombre_estado", nullable = false)
    private String nombreEstado;

    //Constructor vacio para JPA
    public Estado() {}

    //constructor
    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    //metodo 10 secuencia
    public boolean esCompRealizada(){
        return "CompletamenteRealizada".equals(this.nombreEstado);
    }

    //metodo 38 secuencia
    public boolean esAmbitoOI(){
        return "OrdenDeInspeccion".equals(this.ambito);
    }

    //metodo 39 secuencia
    public boolean esCerrada() {
        return "Cerrada".equals(this.nombreEstado);
    }

    //metodo 47 secuencia
    public boolean esAmbitoSismografo(){
        return "Sismografo".equals(this.ambito);
    }

    //metodo 48 secuencia
    public boolean esFueraDeServicio() {
        return "FueraDeServicio".equals(this.nombreEstado);
    }
}

