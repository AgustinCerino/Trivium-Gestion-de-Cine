package org.example.Cliente.Model.Entity;

import java.util.Objects;

public class Cliente {

    private String dni;
    private String nombreYapellido;
    private Integer edad;


    public Cliente(String dni, String nombreYapellido, int edad) {
        this.dni = dni;
        this.nombreYapellido = nombreYapellido;
        this.edad = edad;
    }

    public Cliente(){}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

}

