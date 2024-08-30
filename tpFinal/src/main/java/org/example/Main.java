package org.example;


import org.example.Cliente.Controller.ClienteController;
import org.example.Cliente.Model.Repository.ClienteRepository;
import org.example.Cliente.View.ClienteView;
import org.example.Pelicula.Controller.PeliculaController;
import org.example.Pelicula.Model.Repository.PeliculaRepository;
import org.example.Pelicula.View.PeliculaView;
import org.example.Reserva.Controller.ReservaController;
import org.example.Reserva.Model.Repository.ReservaRepository;
import org.example.Reserva.View.ReservaView;
import org.example.Sala.Controller.SalaController;
import org.example.Sala.Model.Repository.SalaRepository;
import org.example.Sala.View.SalaView;
import org.example.Swing.*;

import javax.swing.*;



public class Main {
    public static void main(String[] args) {


        GestionClientesView gestionClientesView = new GestionClientesView();
        GestionPeliculasView gestionPeliculasView = new GestionPeliculasView();
        GestionReservasView gestionReservasView = new GestionReservasView();

        ClienteRepository clienteRepository = new ClienteRepository();
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteRepository, clienteView, gestionClientesView);

        SalaView salaView = new SalaView();
        SalaRepository salaRepository = new SalaRepository();
        SalaController salaController = new SalaController(salaView, salaRepository);

        PeliculaView peliculaView = new PeliculaView();
        PeliculaRepository peliculaRepository = new PeliculaRepository();
        PeliculaController peliculaController = new PeliculaController(peliculaView, peliculaRepository, gestionPeliculasView,
                salaController);

        ReservaView reservaView = new ReservaView();
        ReservaRepository reservaRepository = new ReservaRepository();
        ReservaController reservaController = new ReservaController(reservaView, reservaRepository, clienteController,
                peliculaController, salaController, gestionClientesView, gestionPeliculasView, gestionReservasView,
                clienteView, clienteRepository);

        salaController.crearSala();

        clienteRepository.loadClientes();
        peliculaRepository.loadPeliculas();
        reservaController.loadSalas();
        reservaRepository.loadReservas();

// Menu principal 
        SwingUtilities.invokeLater(() -> {
            CineMenu cineMenu = new CineMenu(clienteController, peliculaController, reservaController);
            cineMenu.setVisible(true);
        });
    }
}



