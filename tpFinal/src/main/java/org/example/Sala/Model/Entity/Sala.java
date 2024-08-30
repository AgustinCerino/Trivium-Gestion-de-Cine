package org.example.Sala.Model.Entity;

import org.example.Cliente.Model.Entity.Cliente;

import java.util.ArrayList;

public class Sala {
    private int numeroSala;
    private String disponibilidad;
    private ArrayList<Butaca> butacas;

    public Sala(int numeroSala, String disponibilidad) {
        this.numeroSala = numeroSala;
        this.disponibilidad = disponibilidad;
        this.butacas = new ArrayList<>();
        addButacas();
    }

    public Sala(){
        this.butacas = new ArrayList<>();
        addButacas();
    }
    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public ArrayList<Butaca> getButacas() {
        return butacas;
    }

    public void setButacas(ArrayList<Butaca> butacas) {
        this.butacas = butacas;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void addButacas(){
        for (int i=0; i<5; i++){
            Butaca butaca = new Butaca(i, "DISPONIBLE", new Cliente());
            butacas.add(butaca);
        }
    }
}

