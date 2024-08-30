package org.example.Interfaces;

public interface Repository<T> {

    void registrar(T obj);
    T consultar(String id);
     boolean  actualizar(String id, T obj);
     boolean  eliminar(String id);
}

