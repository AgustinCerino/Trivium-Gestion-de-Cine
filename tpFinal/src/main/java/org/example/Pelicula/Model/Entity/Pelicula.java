package org.example.Pelicula.Model.Entity;
import org.example.Sala.Model.Entity.Sala;

import java.util.Date;


public class Pelicula {

    private String titulo;
    private String genero;
    private Integer duracion;
    private Date fechasYhoras;
    private Sala sala;

    public Pelicula(String titulo, String genero, int duracion, Date fechasYhoras, Sala sala) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.fechasYhoras = fechasYhoras;
        this.sala = sala;
    }
    public Pelicula(){}

    public String getTitulo() {
        return titulo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFechasYhoras() {
        return fechasYhoras;
    }

    public void setFechasYhoras(Date fechasYhoras) {
        this.fechasYhoras = fechasYhoras;
    }


}
