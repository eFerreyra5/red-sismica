package com.redsismica.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redsismica.model.Empleado;
import com.redsismica.model.MotivoTipo;
import com.redsismica.model.OrdenDeInspeccion;
import com.redsismica.service.GestorCierreOIService;
import com.redsismica.service.OrdenDeInspeccionService;

@Controller
public class PantCierreOI {

    @Autowired
    private OrdenDeInspeccionService ordenesService;

    @Autowired
    private final GestorCierreOIService gestor;

    public PantCierreOI(GestorCierreOIService gestor) {
        this.gestor = gestor;
    }

    @GetMapping("/cerrar-orden")
    public String abrirVentana(Model model) {
        return "index";
    }

    @GetMapping("/")
    public String home() {
        return "index";
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
    public String tomarSelOI(@RequestParam Long idOrden, HttpSession session) {
        OrdenDeInspeccion orden = ordenesService.getById(idOrden);
        session.setAttribute("ordenSeleccionada", orden);

        Empleado empleado = gestor.buscarEmpleadoLogueado();
        session.setAttribute("empleadoLogueado", empleado);

        gestor.tomarSelOI(idOrden);
        return "redirect:/observaciones"; 
    }

    @GetMapping("/observaciones")
    public String mostrarOpIngresoObservaciones(Model model) {
        return "observaciones"; 
    }

    @PostMapping("/ingresar-observaciones")
    public String tomarIngresoObservaciones(@RequestParam String observacionCierre, HttpSession session) {
        gestor.tomarIngresoObservaciones(observacionCierre);
        session.setAttribute("observacionCierre", observacionCierre);
        return "redirect:/motivos";
    }

    @GetMapping("/motivos")
    public String mostrarMotivoTipoPSeleccion(Model model) {
        List<MotivoTipo> listaMotivosTipo = gestor.habilitarActualizacionSituacionSismografo();
        model.addAttribute("motivos", listaMotivosTipo);
        model.addAttribute("comentarios", new HashMap<String, String>());
        return "motivos";
    }

    @PostMapping("/tomar-sel-motivo")
    public String tomarSelMotivoTipo(HttpServletRequest request, HttpSession session, Model model) {
        String[] idsSeleccionados = request.getParameterValues("motivoId");

        if (idsSeleccionados == null || idsSeleccionados.length == 0) {
            model.addAttribute("error", "Debe seleccionar al menos un motivo y agregar un comentario.");
            model.addAttribute("motivos", gestor.habilitarActualizacionSituacionSismografo());
            return "motivos";
        }

        List<Long> ids = Arrays.stream(idsSeleccionados).map(Long::valueOf).toList();
        gestor.tomarSelMotivoTipo(ids);

        Map<Long, String> comentarios = new HashMap<>();
        for (Long id : ids) {
            String comentario = request.getParameter("comentario_" + id);
            if (comentario == null || comentario.trim().isEmpty()) {
                model.addAttribute("error", "Todos los motivos seleccionados deben tener un comentario.");
                model.addAttribute("motivos", gestor.habilitarActualizacionSituacionSismografo());
                return "motivos";
            }
            gestor.tomarIngresoComentario(id, comentario);
            comentarios.put(id, comentario);
        }

        session.setAttribute("comentariosPorMotivo", comentarios);
        List<String> idsStr = Arrays.stream(idsSeleccionados).map(String::valueOf).toList();
        model.addAttribute("idsSeleccionados", idsStr);
        model.addAttribute("comentarios", comentarios);

        return "confirmacion-cierre";
    }

    // @PostMapping("/tomar-confirmacion-cierre")
    // public String tomarConfirmacionCierre(HttpSession session) {
    //     OrdenDeInspeccion ordenSeleccionada = (OrdenDeInspeccion) session.getAttribute("ordenSeleccionada");
    //     Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");
    //     Map<Long, String> comentarios = (Map<Long, String>) session.getAttribute("comentariosPorMotivo");

    //     if (ordenSeleccionada == null || empleado == null || comentarios == null) {
    //         return "redirect:/listar-ordenes";
    //     }

    //     List<Long> idsMotivos = comentarios.keySet().stream().toList();
    //     gestor.tomarSelMotivoTipo(idsMotivos);
    //     comentarios.forEach((id, comentario) -> gestor.tomarIngresoComentario(id, comentario));

    //     gestor.tomarConfirmacionCierre(ordenSeleccionada, empleado);

    //     // Guardar en sesión
    //     session.setAttribute("nroOrden", ordenSeleccionada.getNumeroOrden());
    //     session.setAttribute("responsables", gestor.buscarEmailRR());

    //     return "redirect:/cierre-exitoso";
    // }

    @PostMapping("/tomar-confirmacion-cierre")
    public String tomarConfirmacionCierre(HttpSession session) {
        OrdenDeInspeccion ordenSeleccionada = (OrdenDeInspeccion) session.getAttribute("ordenSeleccionada");
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");
        Map<Long, String> comentarios = (Map<Long, String>) session.getAttribute("comentariosPorMotivo");

        if (ordenSeleccionada == null || empleado == null || comentarios == null) {
            return "redirect:/listar-ordenes";
        }

        List<Long> idsMotivos = comentarios.keySet().stream().toList();
        gestor.tomarSelMotivoTipo(idsMotivos);
        comentarios.forEach((id, comentario) -> gestor.tomarIngresoComentario(id, comentario));

        gestor.tomarConfirmacionCierre(ordenSeleccionada, empleado);

        // Guardar en sesión
        session.setAttribute("nroOrden", ordenSeleccionada.getNumeroOrden());
        session.setAttribute("responsables", gestor.buscarEmailRR());

        return "redirect:/cierre-exitoso";
    }

    @GetMapping("/cierre-exitoso")
    public String mostrarCierreExitoso(Model model, HttpSession session) {
        Long nroOrdenLong = (Long) session.getAttribute("nroOrden");
        List<String> responsables = (List<String>) session.getAttribute("responsables");
    
        if (nroOrdenLong == null || responsables == null) {
            return "redirect:/listar-ordenes";
        }
    
        String nroOrden = nroOrdenLong.toString(); // conversión segura
    
        model.addAttribute("nroOrden", nroOrden);
        model.addAttribute("responsables", responsables);
    
        return "cierre-exitoso";
    }
}