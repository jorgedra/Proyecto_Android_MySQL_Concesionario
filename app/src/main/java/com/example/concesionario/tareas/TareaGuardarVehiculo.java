package com.example.concesionario.tareas;

import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.concurrent.Callable;

public class TareaGuardarVehiculo implements Callable<Boolean> {

    private Vehiculo v;

    public TareaGuardarVehiculo(Vehiculo v) {
        this.v = v;
    }


    @Override
    public Boolean call() throws Exception {
        boolean guaradoOK = VehiculoDB.guardarVehiculo(v);
        return guaradoOK;
    }
}
