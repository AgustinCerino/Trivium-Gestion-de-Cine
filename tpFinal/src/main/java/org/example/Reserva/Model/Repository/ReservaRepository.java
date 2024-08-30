package org.example.Reserva.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Cliente.Model.Entity.Cliente;
import org.example.Pelicula.Model.Entity.Pelicula;
import org.example.Reserva.Model.Entity.Reserva;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReservaRepository {


    private LinkedHashMap<Integer, Reserva> reservaMap;


    private static final String FILE_PATH = "src/main/resources/reservas.json";


    private Gson gson = new Gson();


    //-------------------------------------------------- JSON ----------------------------------------------------------//
    public void loadReservas() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type mapType = new TypeToken<LinkedHashMap<Integer, Reserva>>() {
            }.getType();
            reservaMap = gson.fromJson(reader, mapType);
            if (reservaMap == null) {
                reservaMap = new LinkedHashMap<>();
            }
        } catch (FileNotFoundException e) {
            reservaMap = new LinkedHashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveReservas() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(reservaMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //------------------------------------------- CONSTRUCTOR GETTER SETTER --------------------------------------------//

    public ReservaRepository() {
        this.reservaMap = new LinkedHashMap<>();
    }

    public LinkedHashMap<Integer, Reserva> getReservaMap() {
        return (LinkedHashMap<Integer, Reserva>) reservaMap;
    }

    public void setReservaMap(Map<Integer, Reserva> reservaMap) {
        this.reservaMap = (LinkedHashMap<Integer, Reserva>) reservaMap;
    }

    public void agregarReserva(Integer key, Reserva reserva) {
        reservaMap.put(key, reserva);
        saveReservas();
        JOptionPane.showMessageDialog(null, "Reserva realizada con exito", "Reservas", JOptionPane.INFORMATION_MESSAGE);
    }
    public int gestLastIdReserva (){
        Integer lastId = 0 ;
        if(!this.reservaMap.isEmpty()){
            for (Map.Entry<Integer, Reserva> entry : this.reservaMap.entrySet()){
                Reserva reserva = entry.getValue();
                if (reserva.getId()>lastId){
                    lastId = reserva.getId();
                }
            }
        }
        lastId = lastId +1;
        return lastId;
    }

    public Reserva crearReserva(Cliente cliente, Pelicula pelicula) {
        System.out.print("ID de la reserva: ");
        int id = gestLastIdReserva();
        System.out.println(id);
        return new Reserva(id, cliente, pelicula);
    }


}
