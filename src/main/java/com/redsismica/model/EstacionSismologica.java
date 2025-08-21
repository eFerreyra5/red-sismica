package com.redsismica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estacion_sismologica")
public class EstacionSismologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEstacion;

    @Column(name = "documento_certificacion_adq", nullable = false)
    private String documentoCertificacionAdq;

    @Column(name = "fecha_solicitud_certificacion", nullable = false)
    private LocalDate fechaSolicitudCertificacion;

    @Column(name = "latitud", nullable = false)
    private float latitud;

    @Column(name = "lonigtud", nullable = false)
    private float longitud;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nro_certificacion_adquisicion", nullable = false)
    private int nroCertificacionAdquisicion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sismografo_id", referencedColumnName = "id")
    private Sismografo sismografo;
    
    //contructor vacio para JPA
    public EstacionSismologica() {}

    //constructor
    public EstacionSismologica(long codigoEstacion, String documentoCertificacionAdq,
            LocalDate fechaSolicitudCertificacion, float latitud, float longitud, String nombre,
            int nroCertificacionAdquisicion, Sismografo sismografo) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
        this.sismografo = sismografo;
    }

    //getters-setters
    public Long getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(Long codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }

    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }

    public LocalDate getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(LocalDate fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getNombre() { //metodo 14 secuencia
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(int nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    public Sismografo getSismografo() {
        return sismografo;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    //metodo 15 secuencia
    public String getIdentificadorSismografo() {
        return sismografo.getIdentificadorSismografo();
    }

}
