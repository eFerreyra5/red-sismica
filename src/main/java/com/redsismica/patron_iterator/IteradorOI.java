package com.redsismica.patron_iterator;

import com.redsismica.model.Empleado;
import com.redsismica.model.OrdenDeInspeccion;

public class IteradorOI implements IIterador{
    private Object[] filtro;
    private OrdenDeInspeccion[] ordenes;
    private int posicionActual;

    public IteradorOI(Object[] elementos, Object[] filtro) {
        this.ordenes = new OrdenDeInspeccion[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            this.ordenes[i] = (OrdenDeInspeccion) elementos[i];
        }
        this.filtro = filtro;
    }

    @Override
    public Object actual() {
        if (posicionActual >= 0 && posicionActual < ordenes.length) {
            return ordenes[posicionActual];
        }
        return null;
    }

    @Override
    public boolean cumpleFiltro(Object[] filtros) {
        OrdenDeInspeccion oiActual = (OrdenDeInspeccion) actual();
        Empleado filtroEmpleado = (Empleado) filtros[0];
        if (oiActual.esDeEmpleado(filtroEmpleado)) {
            if (oiActual.esCompRealizada()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean haFinalizado() {
        return posicionActual >= ordenes.length;
    }

    @Override
    public void primero() {
        posicionActual = 0;
    }

    @Override
    public void siguiente() {
        posicionActual++;
    }
}
