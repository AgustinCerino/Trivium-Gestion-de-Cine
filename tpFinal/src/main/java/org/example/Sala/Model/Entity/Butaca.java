package org.example.Sala.Model.Entity;

import org.example.Cliente.Model.Entity.Cliente;

public class Butaca {
    private int numero;
    private String disponibilidad;
    private Cliente cliente;

    public Butaca(int numero, String disponibilidad, Cliente cliente) {
        this.numero = numero;
        this.disponibilidad = disponibilidad;
        this.cliente = cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
