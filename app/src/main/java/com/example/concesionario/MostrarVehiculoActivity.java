package com.example.concesionario;

import static com.example.concesionario.VehiculoViewHolder.EXTRA_IMG_VEHICULO;
import static com.example.concesionario.utilidades.ImagenesBlobBitmap.bytes_to_bitmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.controladores.VehiculoController;
import com.example.concesionario.modelo.ConfiguracionDB;

import java.util.ArrayList;
import java.util.Collections;

public class MostrarVehiculoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView rv_mostrarVehiculo;
    public static ListaVehiculoAdapter adaptadorVehiculos;
    private ArrayList<Vehiculo> vehiculos;
    private EditText edt_buscar_matricula;
    private EditText edt_buscar_precio;
    ImageView img_mostrar;
    private Context contexto;
    private LayoutInflater mInflater;

    public void ListaVehiculoAdapter(Context contexto)
    {
        this.contexto = contexto;
        mInflater = LayoutInflater.from(contexto);
    }

    public ListaVehiculoAdapter getAdaptadorVehiculos() {
        return adaptadorVehiculos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_vehiculo);
        Intent intent = getIntent();
        rv_mostrarVehiculo = (RecyclerView) findViewById(R.id.rv_mostrarVehiculo);
        vehiculos = VehiculoController.obtenerTodosLosVehiculos();
        edt_buscar_matricula = (EditText) findViewById(R.id.edt_buscar_matricula);
        edt_buscar_precio = (EditText) findViewById(R.id.edt_buscar_precio);
        img_mostrar = (ImageView) findViewById(R.id.img_item_vehiculo);

        //-----------------------------------------------------------------
        adaptadorVehiculos = new ListaVehiculoAdapter(this, vehiculos);
        rv_mostrarVehiculo.setAdapter(adaptadorVehiculos);
        int orientation = getResources().getConfiguration().orientation;
        byte[] fotob = intent.getByteArrayExtra(EXTRA_IMG_VEHICULO);
        if(fotob != null)
        {
            Bitmap fotov = bytes_to_bitmap(fotob, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
            img_mostrar.setImageBitmap(fotov);
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv_mostrarVehiculo.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            // In portrait
            rv_mostrarVehiculo.setLayoutManager(new LinearLayoutManager(this));
        }





//----------------------------------------------------------------------------------------------


    }
    public void refrescarVehiculos(View view) {
        vehiculos = VehiculoController.obtenerTodosLosVehiculos();
        if(vehiculos != null) {
            adaptadorVehiculos.setListaVehiculos(vehiculos);
            adaptadorVehiculos.notifyDataSetChanged();
        }
    }

    public void buscar(View view) {
        if(edt_buscar_matricula.getText() == null  ) {
            edt_buscar_matricula.setError("Introduce el criterio busqueda");
        }
        else if(edt_buscar_precio.getText() == null)
        {
            edt_buscar_precio.setError("Introduce el criterio busqueda");
        }
        else{
            CharSequence buscarM = edt_buscar_matricula.getText();
            CharSequence buscarP = edt_buscar_precio.getText();
            double buscarPint = Double.valueOf(String.valueOf(buscarP));
            ArrayList<Vehiculo> v1 = new ArrayList<Vehiculo>();


            for (Vehiculo v : vehiculos) {
                if (!buscarM.toString().isEmpty()) {
                    if(v.getMatricula().contains(buscarM))
                    {
                        v1.add(v);
                    }
                }
                if (!buscarP.toString().isEmpty()) {
                    if(v.getPrecio() < buscarPint)
                    {
                            v1.add(v);
                    }
                }
            }
            adaptadorVehiculos.setVehiculos(v1);
            adaptadorVehiculos.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}