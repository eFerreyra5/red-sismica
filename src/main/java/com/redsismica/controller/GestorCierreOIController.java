package com.redsismica.controller;

import com.redsismica.model.Empleado;
import com.redsismica.model.Estado;
import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.service.GestorCierreOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/gestor")
public class GestorCierreOIController {

    @Autowired
    private GestorCierreOIService gestorService;

    // Paso 3: registrar cierre OI
    @PostMapping("/registrar-cierre")
    public void registrarCierreOI() {
        gestorService.registrarCierreOI();
    }

    // Paso 4: obtener empleado logueado
    @GetMapping("/empleado-logueado")
    public Empleado getEmpleadoLogueado() {
        return gestorService.buscarEmpleadoLogueado();
    }

    // Paso 7: buscar OIs comp realizadas de un empleado
    @GetMapping("/ordenes-realizadas")
    public List<OrdenDeInspeccion> getOrdenesCompRealizadas() {
        Empleado empleado = gestorService.buscarEmpleadoLogueado();
        return gestorService.buscarOICompRealizadas(empleado);
    }

    // Paso 17: ordenar por fecha
    @GetMapping("/ordenes-ordenadas")
    public List<OrdenDeInspeccion> getOrdenesOrdenadas() {
        return gestorService.ordenarDatosPorFechaFinalizacion();
    }

    // Paso 24-25: motivos tipo
    @GetMapping("/motivos")
    public List<String> getMotivosTipo() {
        return gestorService.habilitarActualizacionSismografo();
    }

    // Paso 37: estado cerrada OI
    @GetMapping("/estado-cerrada")
    public Estado getEstadoCerradaOI() {
        return gestorService.buscarEstadoCerradaOI();
    }

    // Paso 40: fecha actual
    @GetMapping("/fecha-actual")
    public LocalDateTime getFechaHoraActual() {
        return gestorService.getFechaHoraActual();
    }

    // Paso 41: cerrar OI
    @PostMapping("/cerrar-orden/{id}")
    public void cerrarOrden(@PathVariable Long id, @RequestBody String observacion) {
        gestorService.setOrdenSeleccionada(id);
        gestorService.buscarEstadoCerradaOI();
        gestorService.getFechaHoraActual();
        gestorService.cerrarOI(gestorService.buscarOICompRealizadas(
                gestorService.buscarEmpleadoLogueado()).stream()
                .filter(oi -> oi.getNumeroOrden().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("OI no encontrada"))
        );
    }

    // Paso 46: buscar estado fuera de servicio
    @GetMapping("/estado-fds")
    public Estado getEstadoFueraDeServicio() {
        return gestorService.buscarEstadoFueraDeServicioSismografo();
    }

    // Paso 49: poner fuera de servicio
    @PostMapping("/sismografo-fds/{idOrden}")
    public void ponerSismografoFueraDeServicio(@PathVariable Long idOrden) {
        gestorService.setOrdenSeleccionada(idOrden);
        gestorService.ponerFueraDeServicioSismografo();
    }

    // Paso 50: buscar emails responsables reparación
    @GetMapping("/emails-rr")
    public List<String> getEmailsRR() {
        return gestorService.buscarEmailRR();
    }
}