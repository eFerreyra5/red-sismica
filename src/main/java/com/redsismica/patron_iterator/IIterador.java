package com.redsismica.patron_iterator;

public interface IIterador {
    public Object actual();
    public boolean cumpleFiltro(Object[] filtros);
    public boolean haFinalizado();
    public void primero();
    public void siguiente();
}
