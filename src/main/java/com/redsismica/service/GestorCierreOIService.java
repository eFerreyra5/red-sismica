package com.redsismica.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.redsismica.model.Empleado;
import com.redsismica.model.Estado;
import com.redsismica.model.MotivoTipo;
import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.model.Sesion;
import com.redsismica.model.Sismografo;
import com.redsismica.patron_iterator.IAgregado;
import com.redsismica.patron_iterator.IIterador;
import com.redsismica.patron_iterator.IteradorOI;
import com.redsismica.repository.EmpleadoRepository;
import com.redsismica.repository.SesionRepository;
import com.redsismica.repository.OrdenDeInspeccionRepository;
import com.redsismica.repository.SismografoRepository;
import com.redsismica.repository.MotivoTipoRepository;
import com.redsismica.repository.EstadoRepository;

@Service
public class GestorCierreOIService implements IAgregado{

    private final SesionRepository sesionRepository;
    private final OrdenDeInspeccionRepository ordenDeInspeccionRepository;
    private final MotivoTipoRepository motivoTipoRepository;
    private final SismografoRepository sismografoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EstadoRepository estadoRepository;

    private Empleado empleadoLogueado;
    private List<OrdenDeInspeccion> ordenesCompRealizadas = new ArrayList<>();
    private List<List<Object>> datosOI = new ArrayList<>();
    private List<OrdenDeInspeccion> ordenesOrdenadas = new ArrayList<>();
    private OrdenDeInspeccion ordenSeleccionada;
    private List<MotivoTipo> motivosSeleccionados = new ArrayList<>();
    private Map<Long, String> comentariosPorMotivo = new HashMap<>();
    private Estado estadoCerradaOI;
    private LocalDateTime fechaHoraActual;
    private String observacionCierre;
    private Estado estadoFDSSismografo;
    private long idSismografo;
    private Sismografo sismografo;
    private List<String> emailsRR = new ArrayList<>();


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

    public OrdenDeInspeccion getOrdenSeleccionada() {
        return this.ordenSeleccionada;
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

    //metodo 7 secuencia (SE COMENTA ESTE METODO YA QUE CORRESPONDE AL ANALISIS, AQUI VA LA IMPLEMENTACION DEL PATRON)
    // public List<OrdenDeInspeccion> buscarOICompRealizadas(Empleado empleado) {
    //     ordenesCompRealizadas.clear();
    //     datosOI.clear();
        
    //     for (OrdenDeInspeccion orden : ordenDeInspeccionRepository.findAll()) {
    //         if (orden.esDeEmpleado(empleado)) {
    //             if (orden.esCompRealizada()) {
    //                 ordenesCompRealizadas.add(orden);
    //                 List<Object> datosOrden = orden.getDatosOI();
    //                 datosOI.add(datosOrden);
    //             }
    //         }
    //     }
    //     return ordenesCompRealizadas;
    // }

    public IIterador crearIterador(Object[] elementos, Object[] filtros) {

        // Convertir manualmente cada elemento a OrdenDeInspeccion

        OrdenDeInspeccion[] ordenes = new OrdenDeInspeccion[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            ordenes[i] = (OrdenDeInspeccion) elementos[i];
        }

        return new IteradorOI(elementos, filtros);
    }

    public  List<OrdenDeInspeccion> buscarOICompRealizadas(Empleado empleado) {

        ordenesCompRealizadas.clear();
        datosOI.clear();

        List<OrdenDeInspeccion> ordenes = ordenDeInspeccionRepository.findAll();
        Object[] filtros = new Object[] { empleado };
        IIterador iterador = crearIterador(ordenes.toArray(), filtros);

        while (!iterador.haFinalizado()) {
            OrdenDeInspeccion oi = (OrdenDeInspeccion) iterador.actual();
    
            if (iterador.cumpleFiltro(filtros)) {
                ordenesCompRealizadas.add(oi);
                List<Object> datosOrden = oi.getDatosOI();
                datosOI.add(datosOrden);
            }
    
            iterador.siguiente();
        }
        return ordenesCompRealizadas;
    } 
    
    //metodo 17 secuencia
    public List<OrdenDeInspeccion> ordenarDatosPorFechaFinalizacion(List<OrdenDeInspeccion> ordenes) {
        // Crear una copia de la lista para no modificar la original
    List<OrdenDeInspeccion> ordenesOrdenadas = new ArrayList<>(ordenes);

        ordenesOrdenadas.sort((o1, o2) -> 
            o2.getFechaHoraFinalizacion().compareTo(o1.getFechaHoraFinalizacion()) // más recientes primero
        );
    
        return ordenesOrdenadas;
    }

    //metodo 20 secuencia
    public void tomarSelOI(Long idOrden) {

        // Buscar la orden por id
        OrdenDeInspeccion orden = ordenDeInspeccionRepository.findById(idOrden)
            .orElseThrow(() -> new RuntimeException("Orden de Inspección no encontrada"));

        // Guardar la orden seleccionada
        this.ordenSeleccionada = orden;
    }

    //metodo 23 secuencia
    public void tomarIngresoObservaciones(String observacion) {
        this.observacionCierre = observacion;
    }

    //metodo 24 secuencia
    public List<MotivoTipo> habilitarActualizacionSituacionSismografo() {
        return buscarMotivoTipo();
    }

    //metodo 25 secuencia
    public List<MotivoTipo> buscarMotivoTipo() {
        return motivoTipoRepository.findAll();
    }

    //metodo 29 secuencia
    public void tomarSelMotivoTipo(List<Long> idsSeleccionados) {
        motivosSeleccionados.clear();
        comentariosPorMotivo.clear();

        for (Long id : idsSeleccionados) {
            MotivoTipo motivo = motivoTipoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Motivo no encontrado con id " + id));
            motivosSeleccionados.add(motivo);
        }
    }

    //metodo 31 secuencia
    public void tomarIngresoComentario(Long motivoId, String comentario) {
        comentariosPorMotivo.put(motivoId, comentario);
    }

    // método 35 secuencia actualizado
    public void tomarConfirmacionCierre(OrdenDeInspeccion orden, Empleado empleado) {
        // Validar motivos y comentarios
        if (motivosSeleccionados == null || motivosSeleccionados.isEmpty()) {
            throw new IllegalStateException("Debe seleccionar al menos un motivo para cerrar la OI.");
        }

        boolean hayComentario = comentariosPorMotivo.values().stream()
                .anyMatch(comentario -> comentario != null && !comentario.isBlank());

        if (!hayComentario) {
            throw new IllegalStateException("Debe ingresar al menos un comentario para cerrar la OI.");
        }

        // Actualizar estados y fecha
        buscarEstadoCerradaOI();
        getFechaHoraActual();

        // Cerrar la OI
        cerrarOI(orden);

        // Preparar sismógrafo para reparación
        buscarEstadoFueraDeServicioSismografo();
        this.empleadoLogueado = empleado; // importante para ponerFueraDeServicioSismografo
        ponerFueraDeServicioSismografo();
    }

    // // metodo 36 secuencia
    // public void validarDatosMinimos(OrdenDeInspeccion orden) {
    //     if (motivosSeleccionados == null || motivosSeleccionados.isEmpty()) {
    //         throw new IllegalStateException("Debe seleccionar al menos un motivo para cerrar la OI.");
    //     }

    //     boolean hayComentario = comentariosPorMotivo.values().stream()
    //                         .anyMatch(comentario -> comentario != null && !comentario.isBlank());

    //     if (!hayComentario) {
    //         throw new IllegalStateException("Debe ingresar al menos un comentario para cerrar la OI.");
    //     }

    //     this.buscarEstadoCerradaOI();
    //     this.getFechaHoraActual();
    //     this.cerrarOI(orden);  // ahora usamos la orden pasada como parámetro
    //     this.buscarEstadoFueraDeServicioSismografo();
    //     this.ponerFueraDeServicioSismografo();
    // }

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
    // public List<String> buscarEmailRR() {
    //     List<String> emailsRR = new ArrayList<>();
    //     empleadoRepository.findAll().forEach(empleado -> {
    //         if (empleado.esResponsableReparacion()) {
    //             emailsRR.add(empleado.getMail());
    //         }
    //     });
    //     return emailsRR;
    // }
    public List<String> buscarEmailRR() {
        // Obtener todos los empleados que sean responsables de reparación
        List<Empleado> responsablesReparacion = empleadoRepository.findAll()
            .stream()
            .filter(Empleado::esResponsableReparacion) // método que retorna true si es responsable
            .toList();

        // Mostrar en consola los emails encontrados
        responsablesReparacion.forEach(e -> System.out.println("Email RR: " + e.getMail()));
    
        // Mapear a lista de emails
        return responsablesReparacion.stream()
            .map(Empleado::getMail)
            .toList();
    }
}