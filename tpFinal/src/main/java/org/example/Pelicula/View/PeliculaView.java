package org.example.Pelicula.View;

import org.example.Excepciones.Excepciones;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Sala.Model.Entity.Sala;
import org.example.Validaciones.Validar;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Scanner;

public class PeliculaView {
    public Pelicula crearPelicula() {
        JTextField tituloField = new JTextField(20);
        JTextField generoField = new JTextField(20);
        JTextField duracionField = new JTextField(5);
        JComboBox<String> mesCombo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        JComboBox<Integer> diaCombo = new JComboBox<>();
        JTextField horaField = new JTextField(2);
        JTextField minutoField = new JTextField(2);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Género:"));
        panel.add(generoField);
        panel.add(new JLabel("Duración (minutos):"));
        panel.add(duracionField);
        panel.add(new JLabel("Mes:"));
        panel.add(mesCombo);

        panel.add(new JLabel("Día:"));
        panel.add(diaCombo);
        panel.add(new JLabel("Hora:"));
        panel.add(horaField);
        panel.add(new JLabel("Minuto:"));
        panel.add(minutoField);

        // Llenar el combo de días basado en el mes seleccionado
        mesCombo.addActionListener(e -> {
            int mes = mesCombo.getSelectedIndex() + 1;
            int diasEnMes = obtenerDiasEnMes(mes);
            diaCombo.removeAllItems();
            for (int i = 1; i <= diasEnMes; i++) {
                diaCombo.addItem(i);
            }
        });

        // Mostrar el diálogo para crear la película
        int option = JOptionPane.showConfirmDialog(null, panel, "Crear Película",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String titulo = tituloField.getText().trim();
                String genero = generoField.getText().trim();
                int duracion = Integer.parseInt(duracionField.getText().trim());
                int mes = Integer.parseInt((String) mesCombo.getSelectedItem());
                int dia = (int) diaCombo.getSelectedItem();
                int horas = Integer.parseInt(horaField.getText().trim());
                int minutos = Integer.parseInt(minutoField.getText().trim());

                Date fecha = new Date(124, (mes - 1), dia, horas, minutos);
                Pelicula pelicula = new Pelicula(titulo, genero, duracion, fecha, new Sala());
                if (pelicula != null) {
                    return pelicula;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Verifica que todos los campos numéricos sean válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null; // Si se cancela o cierra el diálogo
    }

    // Métodos de Validación adaptados a Swing

    private int obtenerDiasEnMes(int mes) {
        switch (mes) {
            case 2:
                return 28; // Simplificado para el ejemplo, deberías manejar años bisiestos
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }


    public String pedirTitulo() {
        return JOptionPane.showInputDialog(null, "Ingrese el Título de la Película buscada");
    }
}
