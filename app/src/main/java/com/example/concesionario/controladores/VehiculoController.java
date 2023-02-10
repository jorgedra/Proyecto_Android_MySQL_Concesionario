package com.example.concesionario.controladores;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.tareas.TareaActualizarVehiculo;
import com.example.concesionario.tareas.TareaBorrarVehiculo;
import com.example.concesionario.tareas.TareaGuardarVehiculo;
import com.example.concesionario.tareas.TareaObtenerTodosLosTipos;
import com.example.concesionario.tareas.TareaObtenerTodosLosVehiculos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class VehiculoController {

    public static boolean guardarVehiculo(Vehiculo v)
    {
        FutureTask t = new FutureTask(new TareaGuardarVehiculo(v));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static ArrayList<Vehiculo> obtenerTodosLosVehiculos()
    {
        FutureTask t = new FutureTask(new TareaObtenerTodosLosVehiculos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        boolean insercionOK = false;
        try {
            vehiculos = (ArrayList<Vehiculo>) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            return vehiculos;
        }
    }

    public static ArrayList<Tipo> obtenerTodosLosTipos()
    {
        FutureTask t = new FutureTask(new TareaObtenerTodosLosTipos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();
        boolean insercionOK = false;
        try {
            tipos = (ArrayList<Tipo>) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            return tipos;
        }
    }

    public static boolean borrarVehiculo(String matricula)
    {
        FutureTask t = new FutureTask(new TareaBorrarVehiculo(matricula));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static boolean actualizaVehiculo(Vehiculo v,String matricula)
    {
        FutureTask t = new FutureTask(new TareaActualizarVehiculo(v,matricula));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

//    public static boolean buscarVehiculo(String dato,String donde)
//    {
//        FutureTask t = new FutureTask(new TareaBuscarVehiculo(dato,donde));
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        es.submit(t);
//        boolean insercionOK = false;
//        try {
//            insercionOK = (boolean) t.get();
//            es.shutdown();
//            try {
//                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
//                    es.shutdownNow();
//                }
//            } catch (InterruptedException e) {
//                es.shutdownNow();
//            }
//        } catch (
//                ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        finally {
//            return insercionOK;
//        }
//    }
}
