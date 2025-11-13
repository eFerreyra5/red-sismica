package com.redsismica.patron_iterator;

public interface IAgregado {
    public IIterador crearIterador(Object[] elementos, Object[] filtros);
}
