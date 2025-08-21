package com.redsismica.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sismografo")
public class Sismografo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_adquisicion", nullable = false)
    private LocalDate fechaAdquisicion;

    @Column(name = "identificador_sismografo", nullable = false)
    private String identificadorSismografo;

    @Column(name = "nro_serie", nullable = false)
    private int nroSerie;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cambio_estadp_id") 
    private List<CambioEstado> cambioEstado;

    @ManyToOne
    @JoinColumn(name = "estado_actual_id")
    private Estado estadoActual;

    //Constructor vacio para JPA
    public Sismografo() {}
    
    //constructor
    public Sismografo(LocalDate fechaAdquisicion, String identificadorSismografo, int nroSerie,
            List<CambioEstado> cambioEstado, Estado estadoActual) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.cambioEstado = cambioEstado;
        this.estadoActual = estadoActual;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIdentificadorSismografo() { //metodo 16 secuencia
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(String identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
    }

    public List<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(List<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) { //metodo 58 secuencia
        this.estadoActual = estadoActual;
    }

    //metodo 51 secuencia
    public CambioEstado buscarUltimoCambioEstado() {
        if (cambioEstado !=null) {
            for (int i = 0; i < cambioEstado.size(); i++) {
                CambioEstado ce = cambioEstado.get(i);
                if (ce.esEstadoActual()) {
                    return ce;
                }
            }
        }
        return null;
    }

    //metodo 54 secuencia
    public void crearNuevoCambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleadoResponsable) {
        CambioEstado nuevo = new CambioEstado(fechaHoraInicio, estado, empleadoResponsable);
        this.cambioEstado.add(nuevo);
    }

    //metodo 50 secuencia
    public void enviarAReparar(Empleado empleadoResponsable, Estado estadoFueraDeServicio, LocalDateTime fechaHoraFin) {
        this.buscarUltimoCambioEstado();
        CambioEstado ultimoCambio = this.buscarUltimoCambioEstado();
        
        if (ultimoCambio != null) {
        ultimoCambio.setFechaHoraFin(fechaHoraFin);

        this.crearNuevoCambioEstado(LocalDateTime.now(), estadoFueraDeServicio, empleadoResponsable);

        this.setEstadoActual(estadoFueraDeServicio);
        }
    }

    

}
