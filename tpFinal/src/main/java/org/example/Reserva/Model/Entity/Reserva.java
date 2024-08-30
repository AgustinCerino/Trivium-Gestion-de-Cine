package org.example.Reserva.Model.Entity;

import org.example.Cliente.Model.Entity.Cliente;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Sala.Model.Entity.Sala;

import java.util.Map;
import java.util.Objects;

public class Reserva {

    private Integer id;
    private Cliente cliente;
    private Pelicula pelicula;

    public Reserva(Integer id, Cliente cliente, Pelicula pelicula) {
        this.id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Reserva reserva = (Reserva) object;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
