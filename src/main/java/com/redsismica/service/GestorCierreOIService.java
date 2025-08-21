package com.redsismica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.redsismica.model.Empleado;
import com.redsismica.model.Estado;
import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.model.Sesion;
import com.redsismica.model.Sismografo;
import com.redsismica.repository.EmpleadoRepository;
import com.redsismica.repository.SesionRepository;
import com.redsismica.repository.OrdenDeInspeccionRepository;
import com.redsismica.repository.SismografoRepository;
import com.redsismica.repository.MotivoTipoRepository;
import com.redsismica.repository.EstadoRepository;

@Service
public class GestorCierreOIService {

    private final SesionRepository sesionRepository;
    private final OrdenDeInspeccionRepository ordenDeInspeccionRepository;
    private final MotivoTipoRepository motivoTipoRepository;
    private final SismografoRepository sismografoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EstadoRepository estadoRepository;

    private Empleado empleadoLogueado;
    private List<OrdenDeInspeccion> ordenesCompRealizadas = new ArrayList<>();
    private List<List<Object>> datosOI = new ArrayList<>();
    List<OrdenDeInspeccion> ordenesOrdenadas = new ArrayList<>();
    private OrdenDeInspeccion ordenSeleccionada;
    private Estado estadoCerradaOI;
    private LocalDateTime fechaHoraActual;
    private String observacionCierre;
    private Estado estadoFDSSismografo;
    private long idSismografo;
    private Sismografo sismografo;
    List<String> emailsRR = new ArrayList<>();


    public GestorCierreOIService(
            SesionRepository sesionRepository,
            OrdenDeInspeccionRepository ordenDeInspeccionRepository, 
            MotivoTipoRepository motivoTipoRepository,
            SismografoRepository sismografoRepository,
            EmpleadoRepository empleadoRepository,
            EstadoRepository estadoRepository) {

        this.sesionRepository = sesionRepository;
        this.ordenDeInspeccionRepository = ordenDeInspeccionRepository;
        this.motivoTipoRepository = motivoTipoRepository;
        this.sismografoRepository = sismografoRepository;
        this.empleadoRepository = empleadoRepository;
        this.estadoRepository = estadoRepository;
    }

    //getters-setters
    public void setOrdenSeleccionada(Long idOrden) {
        this.ordenSeleccionada = ordenDeInspeccionRepository.findById(idOrden)
            .orElseThrow(() -> new RuntimeException("Orden de Inspección no encontrada"));
    }

    //metodo 3 secuencia
    public void registrarCierreOI() {
        this.empleadoLogueado = buscarEmpleadoLogueado();
        ordenesCompRealizadas = buscarOICompRealizadas(empleadoLogueado);
    }

    //metodo 4 secuencia
    public Empleado buscarEmpleadoLogueado() {
        Optional<Sesion> sesionActiva = sesionRepository.findByFechaHoraFinIsNull();
        if (sesionActiva.isPresent()) {
            return sesionActiva.get().obtenerRILogueado();
        }
        throw new RuntimeException("No hay sesión activa");
    }

    //metodo 7 secuencia
    public List<OrdenDeInspeccion> buscarOICompRealizadas(Empleado empleado) {
        ordenesCompRealizadas.clear();
        datosOI.clear();
        
        for (OrdenDeInspeccion orden : ordenDeInspeccionRepository.findAll()) {
            if (orden.esDeEmpleado(empleado)) {
                if (orden.esCompRealizada()) {
                    ordenesCompRealizadas.add(orden);
                    List<Object> datosOrden = orden.getDatosOI();
                    datosOI.add(datosOrden);
                }
            }
        }
        return ordenesCompRealizadas;
    }

    //metodo 17 secuencia
    public List<OrdenDeInspeccion> ordenarDatosPorFechaFinalizacion() {
        this.ordenesOrdenadas = ordenesCompRealizadas;
        
        ordenesOrdenadas.sort((o1, o2) -> 
            o2.getFechaHoraFinalizacion().compareTo(o1.getFechaHoraFinalizacion()) // más recientes primero
        );
    
        return ordenesOrdenadas;
    }

    //metodo 24 secuencia
    public List<String> habilitarActualizacionSismografo() {
        return buscarMotivoTipo();
    }

    //metodo 25 secuencia
    public List<String> buscarMotivoTipo() {
        List<String> descripciones = new ArrayList<>();
        motivoTipoRepository.findAll().forEach(motivo -> {
            descripciones.add(motivo.getDescripcion());
        });
        return descripciones;
    }

    //metodo 37 secuencia
    public Estado buscarEstadoCerradaOI() {
        for (Estado estado : estadoRepository.findAll()) {
            if (estado.esAmbitoOI()) {
                if (estado.esCerrada()) {
                    this.estadoCerradaOI = estado;
                    return estado;
                }
            }
        }
        throw new RuntimeException("No se encontró un estado 'Cerrada' para el ámbito OI");
    }

    //metodo 40 secuencia
    public LocalDateTime getFechaHoraActual() {
        this.fechaHoraActual = LocalDateTime.now();
        return fechaHoraActual;
    }

    //metodo 41 secuencia
    public void cerrarOI(OrdenDeInspeccion ordenSeleccionada) {
        ordenSeleccionada.cerrar(estadoCerradaOI, fechaHoraActual, observacionCierre);
        ordenDeInspeccionRepository.save(ordenSeleccionada);
    }

     //metodo 46 secuencia
     public Estado buscarEstadoFueraDeServicioSismografo() {
        for (Estado estado : estadoRepository.findAll()) {
            if (estado.esAmbitoSismografo()) {
                if (estado.esFueraDeServicio()) {
                    this.estadoFDSSismografo = estado;
                    return estado;
                }
            }
        }
        throw new RuntimeException("No se encontró un estado 'FueraDeServicio' para el ámbito Sismografo");
    }

    //metodo 49 secuencia
    public void ponerFueraDeServicioSismografo() {
        if (ordenSeleccionada == null) {
            throw new IllegalStateException("No hay orden seleccionada");
        }
    
        List<Object> datos = ordenSeleccionada.getDatosOI();
        idSismografo = (long) datos.get(4);
    
        sismografo = sismografoRepository.findById(idSismografo)
            .orElseThrow(() -> new RuntimeException("Sismógrafo no encontrado"));
    
        if (estadoFDSSismografo == null) {
            throw new IllegalStateException("Estado 'Fuera de Servicio' no definido en gestor");
        }
        if (fechaHoraActual == null) {
            throw new IllegalStateException("Fecha y hora actual no definida en gestor");
        }
        if (empleadoLogueado == null) {
            throw new IllegalStateException("Empleado logueado no definido en gestor");
        }
    
        sismografo.enviarAReparar(empleadoLogueado, estadoFDSSismografo, fechaHoraActual);
    
        sismografoRepository.save(sismografo);
    }

    //metodo 50 secuencia
    public List<String> buscarEmailRR() {
        empleadoRepository.findAll().forEach(empleado -> {
            if (empleado.esResponsableReparacion()) {
                emailsRR.add(empleado.getMail());
            }
        });
        return emailsRR;
    }
}