package com.redsismica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redsismica.model.Empleado;
import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.service.GestorCierreOIService;
import com.redsismica.service.OrdenDeInspeccionService;

import org.springframework.ui.Model;

@Controller
public class PantCierreOI {

    @Autowired
    private OrdenDeInspeccionService ordenesService;

    private final GestorCierreOIService gestor;

    public PantCierreOI(GestorCierreOIService gestor) {
        this.gestor = gestor;
    }
    
    @GetMapping("/cerrar-orden")
    public String abrirVentana(Model model) {
        return "pant-cierre-oi"; // Nombre del archivo HTML
    }

    @GetMapping("/")
    public String home() {
        return "pant-cierre-oi";
    }

    @GetMapping("/listar-ordenes")
    public String opcionCerrarOI(Model model) {
        Empleado empleadoLogueado = gestor.buscarEmpleadoLogueado();

        List<OrdenDeInspeccion> ordenesCompRealizadas = gestor.buscarOICompRealizadas(empleadoLogueado);

        List<OrdenDeInspeccion> ordenesOrdenadas = gestor.ordenarDatosPorFechaFinalizacion(ordenesCompRealizadas);

        model.addAttribute("ordenesCompRealizadas", ordenesOrdenadas);
        return "listar-ordenes";
    }

    @PostMapping("/seleccionar-orden")
    public String tomarSelOI(@RequestParam Long idOrden) {
        // Guardar la orden seleccionada en el servicio
        gestor.tomarSelOI(idOrden);
    
        // Redirigir a la siguiente página después de seleccionar
        return "redirect:/observaciones"; 
    }
    
    @GetMapping("/observaciones")
    public String mostrarOpIngresoObservaciones(Model model) {
        // Redirigir a la página de ingreso de observaciones
        return "observaciones"; // Cambialo por el HTML real de la página siguiente
    }
}
