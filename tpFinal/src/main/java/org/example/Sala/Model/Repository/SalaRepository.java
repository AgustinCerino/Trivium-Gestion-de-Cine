package org.example.Sala.Model.Repository;

import org.example.Sala.Model.Entity.Sala;

import java.util.ArrayList;
import java.util.Scanner;

public class SalaRepository {


    private ArrayList<Sala> listaSalas;

    Scanner scanner = new Scanner(System.in);

    public SalaRepository() {
        this.listaSalas = new ArrayList<>();
    }

    public ArrayList<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(ArrayList<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public void addSala(Sala sala){
        listaSalas.add(sala);
    }



    public Sala consultar(Integer id){
        if(id>=1 && id<=3) {
            for (Sala sala : listaSalas) {
                if (sala.getNumeroSala() == id) {
                    return sala;
                }
            }
        }
            return null;
    }
}
