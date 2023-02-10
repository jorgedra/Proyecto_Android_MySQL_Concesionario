package com.example.concesionario.tareas;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.ArrayList;

public class TareaObtenerTodosLosTipos implements java.util.concurrent.Callable {

    @Override
    public ArrayList<Tipo> call() throws Exception {
        ArrayList<Tipo> tipos = VehiculoDB.obtenerTipos();
        return tipos;
    }
}
