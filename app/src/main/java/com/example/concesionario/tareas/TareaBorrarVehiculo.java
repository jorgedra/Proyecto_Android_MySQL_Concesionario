package com.example.concesionario.tareas;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBorrarVehiculo implements Callable<Boolean> {

    private String matricula = null;

    public TareaBorrarVehiculo(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean borradoOK = VehiculoDB.borrarVehiculo(matricula);
            return borradoOK;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
