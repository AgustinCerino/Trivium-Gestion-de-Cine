package org.example.Swing;

import org.example.Cliente.Model.Entity.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;

public class GestionClientesView extends JFrame {

    public GestionClientesView() {
        setTitle("Gestión de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

    }

    public void mostrarCliente(JFrame parent, Cliente unCliente) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><body style='padding: 10px;'>")
                .append("<h2>Información del Cliente</h2>")
                .append("<p><strong>DNI Cliente:</strong> ").append(unCliente.getDni()).append("</p>")
                .append("<p><strong>Nombre y Apellido:</strong> ").append(unCliente.getNombreYapellido()).append("</p>")
                .append("<p><strong>Edad:</strong> ").append(unCliente.getEdad()).append("</p>")
                .append("</body></html>");

        JOptionPane.showMessageDialog(parent, mensaje.toString(), "Información del Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarListaClientes(JFrame parent, HashSet<Cliente> clientes) {
        // Crear un nuevo JFrame para mostrar la lista de clientes
        JFrame frame = new JFrame("Lista de Clientes");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(parent);
        frame.setLayout(new BorderLayout());

        // Crear la tabla y su modelo
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Nombre", "DNI", "Edad"}, 0);
        JTable tableClientes = new JTable(tableModel);
        tableClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tableClientes.setRowHeight(25);
        tableClientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tableClientes.getTableHeader().setBackground(Color.DARK_GRAY);
        tableClientes.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tableClientes);

        // Llenar la tabla con los datos de los clientes
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{cliente.getNombreYapellido(), cliente.getDni(), cliente.getEdad()});
        }

        // Añadir la tabla al JFrame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Añadir un botón de cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(Color.DARK_GRAY);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> frame.dispose());
        JPanel panelSur = new JPanel();
        panelSur.add(btnCerrar);
        frame.add(panelSur, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }


}