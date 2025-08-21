package com.redsismica.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "cambio_estado")
public class CambioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleadoResponsable;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "motivo_fuera_de_servicio_id")
    private List<MotivoFueraDeServicio> motivoFueraDeServicio = new ArrayList<>();

    //constructor vacio para JPA
    public CambioEstado() {}

    //coonstructor
    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoResponsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.empleadoResponsable = empleadoResponsable;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { //metodo 53 secuencia
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }    

    //metodo 52 secuencia
    public boolean esEstadoActual() {
        return this.fechaHoraFin == null;
    }

    //metodo 56 secuencia
    public void crearMotivoFueraDeServicio(String comentario, MotivoTipo motivoTipo) {
        MotivoFueraDeServicio nuevo = new MotivoFueraDeServicio(comentario, motivoTipo);
        this.motivoFueraDeServicio.add(nuevo);
    }

    public List<MotivoFueraDeServicio> getMotivoFueraDeServicio() {
        return motivoFueraDeServicio;
    }    

}
