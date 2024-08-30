package org.example.Cliente.View;

import org.example.Cliente.Model.Entity.Cliente;
import org.example.Excepciones.Excepciones;


import javax.swing.*;

public class ClienteView {
public void mensaje1() {
    JOptionPane.showMessageDialog(null, "El Cliente buscado no existe!", "Error", JOptionPane.ERROR_MESSAGE);
}

public Cliente cargarClienteManual(JFrame parent) {


    String dni = validarDni(parent);
    String nombreYapellido = validarNombreYapellido(parent);
    int edad = validarEdad(parent);

    return new Cliente(dni, nombreYapellido, edad);
}

public String validarNombreYapellido(JFrame parent) {
    String nombre = "";
    boolean ok = false;
    while (!ok) {
        try {
            nombre = JOptionPane.showInputDialog(parent, "Ingrese el Nombre y Apellido del Cliente:", "Nombre y Apellido", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Excepciones("El campo no puede estar vacío...");
            } else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                throw new Excepciones("Ingrese solo letras y espacios...");
            }
            ok = true;
        } catch (Excepciones e) {
            mostrarError(parent, e.getMessage());
        }
    }
    return nombre.trim();
}

public int validarEdad(JFrame parent) {
    int edad = 0;
    boolean ok = false;
    while (!ok) {
        try {
            String input = JOptionPane.showInputDialog(parent, "Ingrese la Edad del Cliente:", "Edad", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                throw new Excepciones("La operación fue cancelada...");
            }
            edad = Integer.parseInt(input.trim());
            if (edad <= 0) {
                throw new Excepciones("La edad debe ser mayor que cero...");
            }
            ok = true;
        } catch (NumberFormatException | Excepciones e) {
            mostrarError(parent, e.getMessage());
        }
    }
    return edad;
}

public String validarDni(JFrame parent) {
    String dni = "";
    boolean ok = false;
    while (!ok) {
        try {
            dni = JOptionPane.showInputDialog(parent, "Ingrese el DNI del Cliente:", "DNI", JOptionPane.QUESTION_MESSAGE);
            if (dni == null) {
                throw new Excepciones("La operación fue cancelada...");
            }
            if (!dni.matches("\\d+")) {
                throw new Excepciones("Ingrese solo números para el DNI...");
            }
            ok = true;
        } catch (Excepciones e) {
            mostrarError(parent, e.getMessage());
        }
    }
    return dni.trim();
}

public String pedirDniCliente(JFrame parent) {
    String dni = "";
    boolean ok = false;
    while (!ok) {
        try {
            dni = JOptionPane.showInputDialog(parent, "Ingrese el DNI del Cliente buscado:", "Buscar Cliente", JOptionPane.QUESTION_MESSAGE);
            if (dni == null) {
                throw new Excepciones("La operación fue cancelada...");
            }
            if (!dni.matches("\\d+")) {
                throw new Excepciones("Ingrese solo números para el DNI...");
            }
            ok = true;
        } catch (Excepciones e) {
            mostrarError(parent, e.getMessage());
        }
    }
    return dni.trim();
}

private void mostrarError(JFrame parent, String mensaje) {
    JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}
}





