package org.example.Validaciones;

import org.example.Excepciones.Excepciones;
import org.example.Pelicula.Model.Entity.Pelicula;


import javax.swing.*;
import java.util.ArrayList;

public class Validar {
    private String cadena;



    public String validarPelicula(String mensaje, ArrayList<Pelicula> lista) throws Excepciones {
        String input = JOptionPane.showInputDialog(null, mensaje, "Ingresar Título de Película", JOptionPane.PLAIN_MESSAGE);
        this.cadena = input != null ? input.trim() : "";
        boolean ok = false;
        for (Pelicula pelicula : lista) {
            if (pelicula.getTitulo().equals(this.cadena)) {
                ok = true;
            }
        }
        if (!ok) {
            mostrarError("La película ingresada no corresponde a una registrada...");
            throw new Excepciones("La película ingresada no corresponde a una registrada...");
        }
        return this.cadena;
    }


    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

