package org.example.Swing;
import org.example.Pelicula.Model.Entity.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionPeliculasView extends JFrame {

    private JPanel panelFondo;
    private ImageIcon imagenFondo;
public GestionPeliculasView() {
    setTitle("Gestión de Clientes");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
}

    public void verPelicula(JFrame parent, Pelicula pelicula) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><body style='padding: 10px;'>")
                .append("<h2>Detalles de la Película</h2>")
                .append("<p><strong>Título:</strong> ").append(pelicula.getTitulo()).append("</p>")
                .append("<p><strong>Género:</strong> ").append(pelicula.getGenero()).append("</p>")
                .append("<p><strong>Duración:</strong> ").append(pelicula.getDuracion()).append(" minutos</p>")
                .append("<p><strong>Fecha:</strong> ").append(pelicula.getFechasYhoras()).append("</p>")
                .append("</body></html>");

        JOptionPane.showMessageDialog(parent, mensaje.toString(), "Detalles de la Película", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarListaPeliculas(JFrame parent, ArrayList<Pelicula> peliculas) {
        // Crear un nuevo JFrame para mostrar la lista de películas
        JFrame frame = new JFrame("Lista de Películas");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(parent);
        frame.setLayout(new BorderLayout());

        // Crear la tabla y su modelo
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Título", "Género", "Duración", "Fecha"}, 0);
        JTable tablePeliculas = new JTable(tableModel);
        tablePeliculas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablePeliculas.setRowHeight(25);
        tablePeliculas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tablePeliculas.getTableHeader().setBackground(Color.DARK_GRAY);
        tablePeliculas.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablePeliculas);

        // Llenar la tabla con los datos de las películas
        for (Pelicula pelicula : peliculas) {
            tableModel.addRow(new Object[]{pelicula.getTitulo(), pelicula.getGenero(), pelicula.getDuracion(), pelicula.getFechasYhoras()});
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

