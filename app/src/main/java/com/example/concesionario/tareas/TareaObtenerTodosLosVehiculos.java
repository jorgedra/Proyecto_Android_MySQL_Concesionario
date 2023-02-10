package com.example.concesionario.tareas;

import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.ArrayList;

public class TareaObtenerTodosLosVehiculos implements java.util.concurrent.Callable {
    @Override
    public ArrayList<Vehiculo> call() throws Exception {
        ArrayList<Vehiculo> vehiculos = VehiculoDB.obtenerVehiculos();
        return vehiculos;
    }
}
