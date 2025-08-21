package com.redsismica.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orden_inspeccion")
public class OrdenDeInspeccion {

    @Column(name = "fecha_hora_cierre")
    private LocalDateTime fechaHoraCierre;
    
    @Column(name = "fecha_hora_finalizacion")
    private LocalDateTime fechaHoraFinalizacion;
    
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Id
    @Column(name = "numero_orden")
    private Long numeroOrden;

    @Column(name = "observacion_cierre")
    private String observacionCierre;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    public Estado estado;

    @ManyToOne
    @JoinColumn(name = "estacion_id", nullable = false)
    private EstacionSismologica estacion;

    //Constructor vacio para JPA
    public OrdenDeInspeccion() {}

    //constructor
    public OrdenDeInspeccion(LocalDateTime fechaHoraFinalizacion,
            LocalDateTime fechaHoraInicio, Long numeroOrden, Empleado empleado, Estado estado,
            EstacionSismologica estacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.numeroOrden = numeroOrden;
        this.empleado = empleado;
        this.estado = estado;
        this.estacion = estacion;
    }

    //getters-setters
    public LocalDateTime getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(LocalDateTime fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public LocalDateTime getFechaHoraFinalizacion() {  //metodo 13 secuencia
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDateTime fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Long getNumeroOrden() {  //metodo 12 secuencia
        return numeroOrden;
    }

    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getObservacionCierre() {
        return observacionCierre;
    }

    public void setObservacionCierre(String observacionCierre) { //metodo 45 secuencia
        this.observacionCierre = observacionCierre;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) { //metodo 43 secuencia
        this.estado = estado;
    }

    public EstacionSismologica getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionSismologica estacion) {
        this.estacion = estacion;
    }

    //metodo 8 secuencia
    public boolean esDeEmpleado(Empleado empleado){
        return this.getEmpleado() == empleado;
    }
    
    //metodo 9 secuencia
    public boolean esCompRealizada(){
        return estado.esCompRealizada();
    }

    public List<Object> getDatosOI() {
        List<Object> datosOI = new ArrayList<>();
    
        datosOI.add(this.getNumeroOrden());
        datosOI.add(this.getFechaHoraFinalizacion());
        datosOI.add(this.getEstacion().getNombre());
        datosOI.add(this.getEstacion().getIdentificadorSismografo());
    
        return datosOI;
    }
    
    //metodo 42 secuencia
    public void cerrar(Estado estado, LocalDateTime fechaHoraCierre, String observacionCierre){
        this.setEstado(estado);
        this.setFechaHoraCierre(fechaHoraCierre);
        this.setObservacionCierre(observacionCierre);
    }
}
