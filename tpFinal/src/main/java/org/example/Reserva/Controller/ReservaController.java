package org.example.Reserva.Controller;

import org.example.Cliente.Controller.ClienteController;
import org.example.Cliente.Model.Entity.Cliente;
import org.example.Cliente.Model.Repository.ClienteRepository;
import org.example.Cliente.View.ClienteView;
import org.example.Pelicula.Controller.PeliculaController;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Reserva.Model.Entity.Reserva;
import org.example.Reserva.Model.Repository.ReservaRepository;
import org.example.Reserva.View.ReservaView;
import org.example.Sala.Controller.SalaController;
import org.example.Sala.Model.Entity.Butaca;
import org.example.Sala.Model.Entity.Sala;
import org.example.Swing.GestionClientesView;
import org.example.Swing.GestionPeliculasView;
import org.example.Swing.GestionReservasView;


import javax.swing.*;
import java.util.*;

public class ReservaController {
    private ReservaView reservaView;
    private ClienteView clienteView;
    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private ClienteController clienteController;
    private PeliculaController peliculaController;
    private SalaController salaController;
    GestionReservasView gestionReservasView;
    GestionPeliculasView gestionPeliculasView;
    GestionClientesView gestionClientesView;


    public ReservaController(ReservaView reservaView, ReservaRepository reservaRepository, ClienteController clienteController,
                             PeliculaController peliculaController, SalaController salaController,
                             GestionClientesView gestionClientesView, GestionPeliculasView gestionPeliculasView,
                             GestionReservasView gestionReservasView, ClienteView clienteView, ClienteRepository clienteRepository) {
        this.reservaView = reservaView;
        this.reservaRepository = reservaRepository;
        this.clienteController = clienteController;
        this.peliculaController = peliculaController;
        this.salaController = salaController;
        this.gestionPeliculasView = gestionPeliculasView;
        this.gestionClientesView = gestionClientesView;
        this.gestionReservasView = gestionReservasView;
        this.clienteView = clienteView;
        this.clienteRepository = clienteRepository;

    }

    public ReservaView getReservaView() {
        return reservaView;
    }

    public void setReservaView(ReservaView reservaView) {
        this.reservaView = reservaView;
    }

    public ReservaRepository getReservaRepository() {
        return reservaRepository;
    }

    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public void setClienteController(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public PeliculaController getPeliculaController() {
        return peliculaController;
    }

    public void setPeliculaController(PeliculaController peliculaController) {
        this.peliculaController = peliculaController;
    }

    public void generarReserva() {
        String dni = clienteController.getClienteView().validarDni(gestionClientesView);
        Cliente clienteEncontrado = this.clienteRepository.consultar(dni);

        if (clienteEncontrado != null) {
            JOptionPane.showMessageDialog(null, "Cliente valido", "Reservas", JOptionPane.INFORMATION_MESSAGE);
            Date fechaHoy = new Date();
            JOptionPane.showMessageDialog(null, "Mostrando películas en cartelera", "Reservas", JOptionPane.INFORMATION_MESSAGE);
            peliculaController.mostrarPelisFuturo(fechaHoy);

            Pelicula peliEncontrada = buscarPelicula();

            if (peliEncontrada == null) {
                return; // Si no se encontró la película, salimos del método
            }

            boolean listo = false;

            for (Map.Entry<Integer, Reserva> entry : reservaRepository.getReservaMap().entrySet()) {
                if (entry.getValue().getPelicula().getTitulo().equals(peliEncontrada.getTitulo())) {
                    if (hayButacasDisponibles(peliEncontrada)) {
                        generarReserva(peliEncontrada, clienteEncontrado);
                        listo = true;
                        break;
                    }
                }
            }

            if (!listo) {
                seleccionarSalaYGenerarReserva(peliEncontrada, clienteEncontrado);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no existe en la lista", "Reservas", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    public void loadSalas() {
        for (Map.Entry<Integer, Reserva> entryMap : reservaRepository.getReservaMap().entrySet()) {
            for (Object pelicula : peliculaController.getPeliculaRepository().getListaPeliculas()) {
                Pelicula pelicula1 = (Pelicula) pelicula;
                if (Objects.equals(pelicula1.getTitulo(), entryMap.getValue().getPelicula().getTitulo())) {
                    pelicula1.setSala(entryMap.getValue().getPelicula().getSala());
                }
            }
        }
    }

    private Pelicula buscarPelicula() {
        Pelicula peliEncontrada = null;
        String tituloPelicula = peliculaController.getPeliculaRepository().validarPelicula();

        if (tituloPelicula != null) {
            peliEncontrada = peliculaController.getPeliculaRepository().consultar(tituloPelicula);
            if(peliEncontrada != null) {
                JOptionPane.showMessageDialog(null, "Pelicula existente en cartelera", "Reservas", JOptionPane.INFORMATION_MESSAGE);
                gestionPeliculasView.verPelicula(gestionPeliculasView, peliEncontrada);
            } else {
                JOptionPane.showMessageDialog(null, "La pelicula no existe en cartelera", "Reservas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return peliEncontrada;
    }

    private boolean hayButacasDisponibles(Pelicula peliEncontrada) {
        for (Butaca butaca : peliEncontrada.getSala().getButacas()) {
            if (butaca.getDisponibilidad().equals("DISPONIBLE")) {
                return true;
            }
        }
        return false;
    }

    private void seleccionarSalaYGenerarReserva(Pelicula peliEncontrada, Cliente clienteEncontrado) {
        int flag = 0;
        Sala salaDisponible = new Sala();

        do {
            String numeroSalaString = JOptionPane.showInputDialog("Ingrese el numero de sala");

            try {
                // Convertir la entrada de usuario a entero
                int numeroSala = Integer.parseInt(numeroSalaString);
                salaDisponible = salaController.getSalaRepository().consultar(numeroSala);
            } catch (NumberFormatException e) {
                // Manejar el caso donde el usuario no ingresa un número válido
                JOptionPane.showMessageDialog(null, "Error: Por favor ingrese un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (salaDisponible != null) {
                flag = 1;
                peliEncontrada.setSala(salaDisponible);
            } else {
                JOptionPane.showMessageDialog(null, "Sala No Disponible", "Reservas", JOptionPane.ERROR_MESSAGE);
            }
        } while (flag == 0);

        generarReserva(peliEncontrada, clienteEncontrado);
    }

    public void generarReserva(Pelicula peliEncontrada, Cliente clienteEncontrado) {
        int numeroSala = peliEncontrada.getSala().getNumeroSala();
        JOptionPane.showMessageDialog(null, "Numero de sala " + numeroSala, "Reservas ", JOptionPane.INFORMATION_MESSAGE);
        salaController.elegirButacas(clienteEncontrado, peliEncontrada.getSala());

        Reserva reservaNueva = reservaRepository.crearReserva(clienteEncontrado, peliEncontrada);
        reservaRepository.agregarReserva(reservaNueva.getId(), reservaNueva);
        peliculaController.getPeliculaRepository().savePeliculas();
    }

    public void mostrarReservas() {
        if (reservaRepository.getReservaMap().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas", "Reservas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            gestionReservasView.verReservas(this.reservaRepository.getReservaMap(), this.gestionReservasView);
        }
    }

    public void buscarPorCliente() {
        String id = this.clienteView.pedirDniCliente(gestionClientesView);
        Cliente cliente = this.clienteRepository.consultar(id);
        boolean exito = false;
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente valido", "Reservas", JOptionPane.INFORMATION_MESSAGE);
            if (!this.reservaRepository.getReservaMap().isEmpty()) {
                for (Map.Entry<Integer, Reserva> entry : reservaRepository.getReservaMap().entrySet()) {
                    Reserva reserva = entry.getValue();
                    if (reserva.getCliente().getDni().equals(cliente.getDni())) {
                        gestionReservasView.verReserva(entry.getValue(), gestionReservasView);
                        exito = true;
                    }
                }
                if(!exito){
                    JOptionPane.showMessageDialog(null, "El cliente no registra reservas", "Reservas", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay reservas registradas", "Reservas", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no existe en la lista", "Clientes", JOptionPane.ERROR_MESSAGE);
        }
    }
}
