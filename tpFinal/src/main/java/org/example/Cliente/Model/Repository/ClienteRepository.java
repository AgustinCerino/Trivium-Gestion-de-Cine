package org.example.Cliente.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Cliente.Model.Entity.Cliente;
import org.example.Cliente.View.ClienteView;
import org.example.Interfaces.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class ClienteRepository implements Repository <Cliente> {

    private static final String FILE_PATH = "src/main/resources/clientes.json";


    private Gson gson = new Gson();

    ClienteView clienteView;

    private Set<Cliente> listaClientes;

    public HashSet<Cliente> getListaClientes() {
        return (HashSet<Cliente>) listaClientes;
    }

    public void setListaClientes(Set<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ClienteRepository() {
        this.loadClientes();
    }

    @Override
    public void registrar(Cliente obj) {
        this.listaClientes.add(obj);
        this.saveClientes();
    }

    @Override
    public Cliente consultar(String id) {
        for (Cliente unCliente : this.listaClientes) {
            if (unCliente.getDni().equals(id)) {
                return unCliente;
            }
        }
        return null;
    }


    public boolean  consultarSiExisteClienteEnLista (String id) {
        for (Cliente unCliente : this.listaClientes) {
            if (unCliente.getDni().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized  boolean actualizar(String id, Cliente obj) {
        Cliente clienteAactualizar = this.consultar(id);
        boolean exito = false;
        if (clienteAactualizar != null) {
            this.listaClientes.remove(clienteAactualizar);
            this.listaClientes.add(obj);
            exito = true;
        }
        return exito;
    }

    @Override
    public synchronized  boolean  eliminar(String id) {
        Cliente clienteAeliminar = this.consultar(id);
        boolean elimino = false;
        if (clienteAeliminar != null) {
            this.listaClientes.remove(clienteAeliminar);
            elimino = true;
        }
        return elimino;
    }

    public void loadClientes() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<HashSet<Cliente>>() {
            }.getType();
            listaClientes = gson.fromJson(reader, listType);

            if (listaClientes == null) {
                listaClientes = new HashSet<>();
            }

        } catch (FileNotFoundException e) {
            listaClientes = new HashSet<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveClientes() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaClientes, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}