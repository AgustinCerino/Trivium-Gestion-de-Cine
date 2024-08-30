package org.example.Pelicula.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Excepciones.Excepciones;
import org.example.Interfaces.Repository;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Validaciones.Validar;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PeliculaRepository implements Repository<Pelicula> {

    private ArrayList<Pelicula> listaPeliculas;


    private static final String FILE_PATH = "src/main/resources/peliculas.json";


    private Gson gson = new Gson();

    Validar validar = new Validar();

    //---------------------------------------- CONSTRUCTOR GETTER SETTER -----------------------------------------------//

    public ArrayList<Pelicula> getListaPeliculas() {
        return this.listaPeliculas;
    }

    public void setListaPeliculas(ArrayList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public PeliculaRepository() {
        this.loadPeliculas();
    }

    //-------------------------------------------------- JSON ---------------------------------------------------------//

    public void loadPeliculas() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type arrayType = new TypeToken<ArrayList<Pelicula>>() {
            }.getType();
            listaPeliculas = gson.fromJson(reader, arrayType);
            if (listaPeliculas == null) {
                listaPeliculas = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            listaPeliculas = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePeliculas() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaPeliculas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------- METODOS --------------------------------------------------------//

    @Override
    public void registrar(Pelicula obj) {
        listaPeliculas.add(obj);
        savePeliculas();
    }

    @Override
    public Pelicula consultar(String id) {
        for (Pelicula pelicula : this.listaPeliculas) {
            if (pelicula.getTitulo().equals(id)) {
                return pelicula;
            }
        }
        return null;
    }


    public boolean consultarSiExistePeliculaEnList(String id) {
        for (Pelicula pelicula : this.listaPeliculas) {
            if (pelicula.getTitulo().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean actualizar(String id, Pelicula obj) {
        Pelicula peliculaAactualizar = this.consultar(id);
        boolean exito = false;
        if (peliculaAactualizar != null) {
            this.listaPeliculas.remove(peliculaAactualizar);
            this.listaPeliculas.add(obj);
            exito = true;
        }
        return exito;
    }

    @Override
    public synchronized  boolean eliminar(String id) {
        Pelicula peliculaAeliminar = this.consultar(id);
        boolean elimino = false;
        if(peliculaAeliminar != null) {
            elimino = this.listaPeliculas.remove(peliculaAeliminar);
        }
        savePeliculas();
        return elimino;
    }

    public String validarPelicula() {
        String nombre = "";
        boolean ok = false;
        while (!ok) {
            try {
                nombre = validar.validarPelicula("Nombre de la pelicula: ", this.listaPeliculas);
                ok = true;
            } catch (Excepciones e) {
                System.out.println(e.getMessage());
            }
        }
        return nombre;
    }

}
