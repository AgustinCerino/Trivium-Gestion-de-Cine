package org.example.Swing;
import org.example.Sala.Model.Entity.Butaca;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

import org.example.Reserva.Model.Entity.Reserva;
public class GestionReservasView extends JFrame {

    public GestionReservasView() {
        setTitle("Gestión de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

    }


    public void verReservas(Map<Integer, Reserva> reservasMap, JFrame parent) {
        JFrame frame = new JFrame("Lista de Reservas");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(parent);
        frame.setLayout(new BorderLayout());

        // Crear un panel para contener la información de las reservas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear un StringBuilder para construir el mensaje HTML de todas las reservas
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><body style='padding: 10px;'>");

        // Iterar sobre las reservas del mapa
        for (Map.Entry<Integer, Reserva> entry : reservasMap.entrySet()) {
            Reserva reserva = entry.getValue();

            mensaje.append("<h2>Detalles de la Reserva</h2>")
                    .append("<p><strong>Cliente:</strong> ").append(reserva.getCliente().getNombreYapellido()).append("</p>")
                    .append("<p><strong>DNI Cliente:</strong> ").append(reserva.getCliente().getDni()).append("</p>")
                    .append("<p><strong>Edad Cliente:</strong> ").append(reserva.getCliente().getEdad()).append("</p>")
                    .append("<br>")
                    .append("<h3>Película</h3>")
                    .append("<p><strong>Título:</strong> ").append(reserva.getPelicula().getTitulo()).append("</p>")
                    .append("<p><strong>Género:</strong> ").append(reserva.getPelicula().getGenero()).append("</p>")
                    .append("<p><strong>Duración:</strong> ").append(reserva.getPelicula().getDuracion()).append(" minutos</p>")
                    .append("<p><strong>Fecha:</strong> ").append(reserva.getPelicula().getFechasYhoras()).append("</p>")
                    .append("<br>")
                    .append("<h3>Sala</h3>")
                    .append("<p><strong>Número de Sala:</strong> ").append(reserva.getPelicula().getSala().getNumeroSala()).append("</p>");

            // Contar las butacas reservadas por el cliente en la película
            int numButacasReservadas = 0;
            for (Butaca butaca : reserva.getPelicula().getSala().getButacas()) {
                if (butaca.getCliente() != null && butaca.getCliente().equals(reserva.getCliente())) {
                    numButacasReservadas++;
                }
            }
            mensaje.append("<p><strong>Cantidad de butacas reservadas:</strong> ").append(numButacasReservadas).append("</p>")
                    .append("<hr>"); // Línea separadora entre cada reserva
        }

        mensaje.append("</body></html>");

        JLabel label = new JLabel(mensaje.toString());
        label.setVerticalAlignment(SwingConstants.TOP);
        panel.add(label);

        // Crear un JScrollPane para hacer scroll en el contenido
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

        frame.setVisible(true);
    }

     public void verReserva(Reserva reserva, JFrame parent) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><body style='padding: 10px;'>")
                .append("<h2>Detalles de la Reserva</h2>")
                .append("<p><strong>Cliente:</strong> ").append(reserva.getCliente().getNombreYapellido()).append("</p>")
                .append("<p><strong>DNI Cliente:</strong> ").append(reserva.getCliente().getDni()).append("</p>")
                .append("<p><strong>Edad Cliente:</strong> ").append(reserva.getCliente().getEdad()).append("</p>")
                .append("<br>")
                .append("<h3>Película</h3>")
                .append("<p><strong>Título:</strong> ").append(reserva.getPelicula().getTitulo()).append("</p>")
                .append("<p><strong>Género:</strong> ").append(reserva.getPelicula().getGenero()).append("</p>")
                .append("<p><strong>Duración:</strong> ").append(reserva.getPelicula().getDuracion()).append(" minutos</p>")
                .append("<p><strong>Fecha:</strong> ").append(reserva.getPelicula().getFechasYhoras()).append("</p>")
                .append("<br>")
                .append("<h3>Sala</h3>")
                .append("<p><strong>Número de Sala:</strong> ").append(reserva.getPelicula().getSala().getNumeroSala()).append("</p>");

        // Contar las butacas reservadas por el cliente en la película
        int numButacasReservadas = 0;
        for (Butaca butaca : reserva.getPelicula().getSala().getButacas()) {
            if (butaca.getCliente() != null && butaca.getCliente().equals(reserva.getCliente())) {
                numButacasReservadas++;
            }
        }
        mensaje.append("<p><strong>Cantidad de butacas reservadas:</strong> ").append(numButacasReservadas).append("</p>");

        mensaje.append("</body></html>");

        JOptionPane.showMessageDialog(parent, mensaje.toString(), "Detalles de la Reserva", JOptionPane.INFORMATION_MESSAGE);
    }


}