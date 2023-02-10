package com.example.concesionario.tareas;

import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.concurrent.Callable;

public class TareaActualizarVehiculo implements Callable<Boolean> {

    private Vehiculo v;
    private String matricula = null;
    public TareaActualizarVehiculo(Vehiculo v, String matricula) {
        this.matricula = matricula;
        this.v = v;
    }


    @Override
    public Boolean call() throws Exception {
        boolean guaradoOK = VehiculoDB.actualizarVehiculos(v,matricula);
        return guaradoOK;
    }
}
