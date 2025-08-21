package com.redsismica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motivo_fuera_de_servicio")
public class MotivoFueraDeServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "motivo_tipo_id")
    private MotivoTipo motivoTipo;
    
    //constructor vacio para JPA
    public MotivoFueraDeServicio() {}

    //constructor
    public MotivoFueraDeServicio(String comentario, MotivoTipo motivoTipo) {
        this.comentario = comentario;
        this.motivoTipo = motivoTipo;
    }

    //getters-setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }  
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public MotivoTipo getMotivoTipo() {
        return motivoTipo;
    }

    public void setMotivoTipo(MotivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }

      
}
