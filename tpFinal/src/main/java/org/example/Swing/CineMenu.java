package org.example.Swing;

import org.example.Cliente.Controller.ClienteController;
import org.example.Pelicula.Controller.PeliculaController;
import org.example.Reserva.Controller.ReservaController;

import javax.swing.*;
import java.awt.*;

public class CineMenu extends JFrame {
        private ClienteController clienteController;
        private PeliculaController peliculaController;
        private ReservaController reservaController;

        private JPanel mainPanel;

        public CineMenu(ClienteController clienteController, PeliculaController peliculaController, ReservaController reservaController) {
            this.clienteController = clienteController;
            this.peliculaController = peliculaController;
            this.reservaController = reservaController;

            setTitle("Cine Menu");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            mainPanel = new JPanel(new CardLayout());
            mainPanel.add(createMainMenu(), "MainMenu");
            mainPanel.add(createClienteMenu(), "ClienteMenu");
            mainPanel.add(createPeliculaMenu(), "PeliculaMenu");
            mainPanel.add(createReservaMenu(), "ReservaMenu");

            add(mainPanel);
            showCard("MainMenu");
        }

        private JPanel createMainMenu() {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Menu Principal", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            panel.add(titleLabel);

            JButton clienteButton = new JButton("Menu Cliente");
            clienteButton.addActionListener(e -> showCard("ClienteMenu"));
            panel.add(clienteButton);

            JButton peliculaButton = new JButton("Menu Pelicula");
            peliculaButton.addActionListener(e -> showCard("PeliculaMenu"));
            panel.add(peliculaButton);

            JButton reservaButton = new JButton("Menu Reserva");
            reservaButton.addActionListener(e -> showCard("ReservaMenu"));
            panel.add(reservaButton);

            JButton signOutButton = new JButton("Sign out");
            signOutButton.addActionListener(e -> {
                System.out.println("Programa finalizado");
                System.exit(0);
            });
            panel.add(signOutButton);

            return panel;
        }

        private JPanel createClienteMenu() {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Submenu Clientes", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            panel.add(titleLabel);

            JButton mostrarClientesButton = new JButton("Mostrar Clientes");
            mostrarClientesButton.addActionListener(e -> clienteController.mostrarListaClientes());
            panel.add(mostrarClientesButton);

            JButton cargarClienteButton = new JButton("Cargar Cliente Manual");
            cargarClienteButton.addActionListener(e -> clienteController.cargarClienteManual());
            panel.add(cargarClienteButton);

            JButton buscarClienteButton = new JButton("Buscar un Cliente");
            buscarClienteButton.addActionListener(e -> clienteController.buscarCliente());
            panel.add(buscarClienteButton);

            JButton actualizarClienteButton = new JButton("Actualizar un Cliente");
            actualizarClienteButton.addActionListener(e -> clienteController.actualizarCliente());
            panel.add(actualizarClienteButton);

            JButton eliminarClienteButton = new JButton("Eliminar un Cliente");
            eliminarClienteButton.addActionListener(e -> clienteController.eliminarCliente());
            panel.add(eliminarClienteButton);

            JButton backButton = new JButton("Volver atras");
            backButton.addActionListener(e -> showCard("MainMenu"));
            panel.add(backButton);

            return panel;
        }

        private JPanel createPeliculaMenu() {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Submenu Peliculas", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            panel.add(titleLabel);

            JButton cargarPeliculaButton = new JButton("Cargar Pelicula Manual");
            cargarPeliculaButton.addActionListener(e -> peliculaController.cargarPeliculaManual());
            panel.add(cargarPeliculaButton);

            JButton mostrarPeliculasButton = new JButton("Mostrar Peliculas");
            mostrarPeliculasButton.addActionListener(e -> peliculaController.mostrarListPeliculas());
            panel.add(mostrarPeliculasButton);

            JButton buscarPeliculaButton = new JButton("Buscar una Pelicula");
            buscarPeliculaButton.addActionListener(e -> peliculaController.buscarPelicula());
            panel.add(buscarPeliculaButton);

            JButton actualizarPeliculaButton = new JButton("Actualizar una Pelicula");
            actualizarPeliculaButton.addActionListener(e -> peliculaController.actualizarPelicula());
            panel.add(actualizarPeliculaButton);

            JButton eliminarPeliculaButton = new JButton("Eliminar una Pelicula");
            eliminarPeliculaButton.addActionListener(e -> peliculaController.eliminarPelicula());
            panel.add(eliminarPeliculaButton);

            JButton backButton = new JButton("Volver atras");
            backButton.addActionListener(e -> showCard("MainMenu"));
            panel.add(backButton);

            return panel;
        }

        private JPanel createReservaMenu() {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Submenu Reservas", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            panel.add(titleLabel);

            JButton crearReservaButton = new JButton("Crear Nueva Reserva");
            crearReservaButton.addActionListener(e -> {
                try {
                    reservaController.generarReserva();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(crearReservaButton);

            JButton mostrarReservasButton = new JButton("Mostrar Reservas Activas");
            mostrarReservasButton.addActionListener(e -> reservaController.mostrarReservas());
            panel.add(mostrarReservasButton);

            JButton buscarReservaButton = new JButton("Buscar una Reserva");
            buscarReservaButton.addActionListener(e -> reservaController.buscarPorCliente());
            panel.add(buscarReservaButton);

            JButton backButton = new JButton("Volver atras");
            backButton.addActionListener(e -> showCard("MainMenu"));
            panel.add(backButton);

            return panel;
        }

        private void showCard(String card) {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, card);
        }
}
