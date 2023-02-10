package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;
import com.example.concesionario.modelo.ConfiguracionDB;
import com.example.concesionario.modelo.VehiculoDB;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connection c = ConfiguracionDB.conectarConBaseDeDatos();
        if(c == null)
        {
            Log.i("sql", "error al conectar");
        }
        else{
            Log.i("sql", "conectado correctamente");
        }


    }
    public void insertarVehiculo(View view) {
        Intent intent = new Intent(this,InsertarVehiculoActivity.class);
        startActivity(intent);
    }

    public void mostrarVehiculo(View view) {
        Intent intent = new Intent(this,MostrarVehiculoActivity.class);
        startActivity(intent);
    }
}