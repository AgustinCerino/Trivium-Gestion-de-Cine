package org.example.Sala.Controller;

import org.example.Cliente.Model.Entity.Cliente;

import org.example.Excepciones.Excepciones;
import org.example.Sala.Model.Entity.Butaca;
import org.example.Sala.Model.Entity.Sala;
import org.example.Sala.Model.Repository.SalaRepository;
import org.example.Sala.View.SalaView;


import javax.swing.*;


public class SalaController {
    private SalaView salaView;
    private SalaRepository salaRepository;

    public SalaController(SalaView salaView, SalaRepository salaRepository) {
        this.salaView = salaView;
        this.salaRepository = salaRepository;
    }

    public SalaView getSalaView() {
        return salaView;
    }

    public void setSalaView(SalaView salaView) {
        this.salaView = salaView;
    }

    public SalaRepository getSalaRepository() {
        return salaRepository;
    }

    public void setSalaRepository(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }


    public void verButacaDisponible(Sala sala) {
        StringBuilder mensaje = new StringBuilder();
        boolean hayButacasDisponibles = false;  // Variable de control para verificar disponibilidad

        mensaje.append("Disponibilidad de butacas en la sala " + sala.getNumeroSala() + ":\n");

        for (Butaca butaca : sala.getButacas()) {
            if (butaca.getDisponibilidad().equals("DISPONIBLE")) {
                hayButacasDisponibles = true;  // Encontramos al menos una butaca disponible
                mensaje.append("[").append(butaca.getNumero()).append("] ");
            }
        }

        if (hayButacasDisponibles) {
            // Mostrar mensaje con las butacas disponibles
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Sala " + sala.getNumeroSala(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar mensaje de que no hay butacas disponibles
            JOptionPane.showMessageDialog(null, "No hay butacas disponibles.", "Sala " + sala.getNumeroSala(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void elegirButacas(Cliente cliente, Sala sala) {
        int idButaca;
        int option;
        do {
            verButacaDisponible(sala);
            if ((idButaca = validarButacas(sala)) != -1) { // me fijo si hay butacas disponibles
                sala.getButacas().get(idButaca).setDisponibilidad("OCUPADA");
                sala.getButacas().get(idButaca).setCliente(cliente);
                option = JOptionPane.showConfirmDialog(null, "¿Desea reservar otra butaca?", "Continuar", JOptionPane.YES_NO_OPTION);
            } else {
                JOptionPane.showMessageDialog(null, "No hay más butacas disponibles...", "Sala " + sala.getNumeroSala(), JOptionPane.INFORMATION_MESSAGE);
                option = JOptionPane.NO_OPTION;
            }
        } while (option == JOptionPane.YES_OPTION);
    }


   public int validarButacas(Sala sala) {
       boolean ok = false;
       int flag = 0;
       int num = 0;

       // Determinar si hay butacas disponibles
       for (Butaca butaca : sala.getButacas()) {
           if (butaca.getDisponibilidad().equals("DISPONIBLE")) {
               flag = 1;
               break; // Si hay al menos una butaca disponible, salir del bucle
           }
       }

       if (flag == 1) {
           while (!ok) {
               try {
                   // Solicitar al usuario que elija una butaca
                   String input = JOptionPane.showInputDialog(null, "Elija butaca a reservar:", "Reservar Butaca", JOptionPane.QUESTION_MESSAGE);

                   // Si el usuario cancela la operación
                   if (input == null) {
                       throw new Excepciones("La operación fue cancelada...");
                   }

                   num = Integer.parseInt(input.trim());

                   // Validar si el número ingresado es válido y la butaca está disponible
                   if (num >= 0 && num < sala.getButacas().size()) {
                       if (sala.getButacas().get(num).getDisponibilidad().equals("DISPONIBLE")) {
                           ok = true;
                       } else {
                           JOptionPane.showMessageDialog(null, "Esa butaca está ocupada...", "Error", JOptionPane.ERROR_MESSAGE);
                           ok = false;
                       }
                   } else {
                       JOptionPane.showMessageDialog(null, "Esa butaca no corresponde a una existente...", "Error", JOptionPane.ERROR_MESSAGE);
                       ok = false;
                   }
               } catch (NumberFormatException e) {
                   JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
               } catch (Excepciones e) {
                   JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
       } else {
           // Si no hay butacas disponibles, retornar -1
           return -1;
       }

       return num;
   }

    public void crearSala() {
        Sala sala1 = new Sala(1, "DISPONIBLE");
        salaRepository.addSala(sala1);
        Sala sala2 = new Sala(2, "DISPONIBLE");
        salaRepository.addSala(sala2);
        Sala sala3 = new Sala(3, "DISPONIBLE");
        salaRepository.addSala(sala3);
    }
}
