package org.example.Pelicula.Controller;

import org.example.Cliente.Model.Entity.Cliente;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Pelicula.Model.Repository.PeliculaRepository;
import org.example.Pelicula.View.PeliculaView;
import org.example.Sala.Controller.SalaController;
import org.example.Sala.Model.Entity.Butaca;
import org.example.Swing.GestionPeliculasView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class PeliculaController {
    private PeliculaView peliculaView;
    private PeliculaRepository peliculaRepository;
    private SalaController salaController;
    private GestionPeliculasView gestionPeliculasView;


    //---------------------------------------- CONSTRUCTOR GETTER SETTER -----------------------------------------------//
    public PeliculaController(PeliculaView peliculaView, PeliculaRepository peliculaRepository,
                              GestionPeliculasView gestionPeliculasView, SalaController salaController) {
        this.peliculaView = peliculaView;
        this.peliculaRepository = peliculaRepository;
        this.salaController = salaController;
        this.gestionPeliculasView = gestionPeliculasView;
    }

    public PeliculaView getPeliculaView() {
        return peliculaView;
    }

    public SalaController getSalaController() {
        return salaController;
    }

    public void setSalaController(SalaController salaController) {
        this.salaController = salaController;
    }

    public void setPeliculaView(PeliculaView peliculaView) {
        this.peliculaView = peliculaView;
    }

    public PeliculaRepository getPeliculaRepository() {
        return peliculaRepository;
    }

    public void setPeliculaRepository(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    //-------------------------------------------------METODOS----------------------------------------------------------//


    public void cargarPeliculaManual() {
        boolean existe = true;
        Pelicula pelicula = peliculaView.crearPelicula();
        if(pelicula != null){
            existe = peliculaRepository.consultarSiExistePeliculaEnList(pelicula.getTitulo());
            if (!existe){

                this.peliculaRepository.registrar(pelicula);
                JOptionPane.showMessageDialog(null, "Pelicula cargada a la cartelera con exito", "Peliculas", JOptionPane.INFORMATION_MESSAGE);
                this.gestionPeliculasView.verPelicula(this.gestionPeliculasView, pelicula);
            } else {
                JOptionPane.showMessageDialog(null, "La pelicula ya existe en cartelera", "Peliculas", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void mostrarListPeliculas() {

        ArrayList<Pelicula> listaPeliculas = this.peliculaRepository.getListaPeliculas();
        if (!listaPeliculas.isEmpty()) {
            gestionPeliculasView.mostrarListaPeliculas(this.gestionPeliculasView, listaPeliculas);
        } else {
            JOptionPane.showMessageDialog(null, "Lista de peliculas vacia", "Peliculas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarPelicula() {
        Pelicula unaPelicula = peliculaRepository.consultar(peliculaView.pedirTitulo());
        if (unaPelicula != null) {
            JOptionPane.showMessageDialog(null, "Pelicula Valida", "Peliculas", JOptionPane.INFORMATION_MESSAGE);
            gestionPeliculasView.verPelicula(this.gestionPeliculasView, unaPelicula);
        } else {
            JOptionPane.showMessageDialog(null, "La pelicula no existe en cartelera", "Peliculas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarPelicula() {

        Pelicula peliculaAactualizar = peliculaRepository.consultar(peliculaView.pedirTitulo());
        boolean exito;
        if (peliculaAactualizar != null) {
            exito =  peliculaRepository.actualizar(peliculaAactualizar.getTitulo(), peliculaView.crearPelicula());
            if(exito) {
                JOptionPane.showMessageDialog(null, "Actualizacion Exitosa", "Peliculas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar pelicula", "Peliculas", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La pelicula que desea actualizar no existe", "Peliculas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarPelicula() {
        boolean elimino = peliculaRepository.eliminar(peliculaView.pedirTitulo());
        if(elimino) {
            JOptionPane.showMessageDialog(null, "Eliminacion de pelicula exitosa", "Peliculas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La pelicula no existe en cartelera", "Peliculas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarPelisFuturo(Date fecha) {

        for (Object pelicula : getPeliculaRepository().getListaPeliculas()) {
            Pelicula pelicula1 = (Pelicula) pelicula;
            boolean ok = false;
            if (pelicula1.getFechasYhoras().getTime() > fecha.getTime()) {
                for (Butaca butaca : pelicula1.getSala().getButacas()) {
                    if (butaca.getDisponibilidad().equals("DISPONIBLE")) {
                        ok = true;
                    }
                }
                if(ok){
                    gestionPeliculasView.verPelicula(this.gestionPeliculasView, pelicula1);
                }
            }
        }
    }
}
