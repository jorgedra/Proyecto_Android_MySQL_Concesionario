package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.controladores.VehiculoController;

import java.io.IOException;
import java.util.ArrayList;

public class InsertarVehiculoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edt_nuevo_matricula = null;
    private EditText edt_nuevo_modelo = null;
    private EditText edt_nuevo_marca = null;
    private EditText edt_nuevo_caballos = null;
    private EditText edt_nuevo_precio = null;
    private TextView txt_nuevo_tipo = null;
    private Button bt_insertar = null;
    private Spinner sp_tipo = null;
    private String tipoVehiculo = null;
    private int idTipo;

    ImageView img;

    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;

    Vehiculo v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_vehiculo);
        //----------------------------------------------------
        edt_nuevo_matricula = (EditText) findViewById(R.id.edt_nuevo_matricula);
        edt_nuevo_modelo = (EditText) findViewById(R.id.edt_nuevo_modelo);
        edt_nuevo_marca = (EditText) findViewById(R.id.edt_nuevo_marca);
        edt_nuevo_caballos = (EditText) findViewById(R.id.edt_nuevo_caballos);
        edt_nuevo_precio = (EditText) findViewById(R.id.edt_nuevo_precio);
        txt_nuevo_tipo = (TextView) findViewById(R.id.txt_nuevo_tipo);
        bt_insertar = (Button) findViewById(R.id.bt_insertar);
        sp_tipo = (Spinner) findViewById(R.id.sp_tipo);
        img = (ImageView) findViewById(R.id.img_coche_add);
        //----------------------------------------------------------


        ArrayList<Tipo> tiposA = VehiculoController.obtenerTodosLosTipos();
        ArrayList tipos = new ArrayList();
        for (Tipo a: tiposA) {
            tipos.add(a.getTipo());
        }
        sp_tipo.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipo.setAdapter(adapter);
    }

    public void insertar(View view) {
        String matricula = String.valueOf(edt_nuevo_matricula.getText());
        String modelo = String.valueOf(edt_nuevo_modelo.getText());
        String marca = String.valueOf(edt_nuevo_marca.getText());
        String textocaballos = String.valueOf(edt_nuevo_caballos.getText());
        String textoPrecio = String.valueOf(edt_nuevo_precio.getText());
        if (matricula.isEmpty()) {
            edt_nuevo_matricula.setError("escribe algo");
        }
        if (modelo.isEmpty()) {
            edt_nuevo_modelo.setError("escribe algo");
        }
        if (marca.isEmpty()) {
            edt_nuevo_marca.setError("escribe algo");
        }
        if (textocaballos.isEmpty()) {
            edt_nuevo_caballos.setError("escribe algo");
        }
        if (textoPrecio.isEmpty()) {
            edt_nuevo_precio.setError("escribe algo");
        }
        double precio = Double.valueOf(textoPrecio);
        int caballos = Integer.valueOf(textocaballos);
        //--------------------------------------------------------
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI|NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(imagen_seleccionada != null)
                {
                    img.buildDrawingCache();
                    Bitmap bm_foto = img.getDrawingCache();
                    v = new Vehiculo(matricula, modelo, marca, caballos,precio,idTipo,bm_foto);
                }
                else{
                    v = new Vehiculo(matricula, modelo, marca, caballos,precio,idTipo);
                }
                boolean guardadoOK = VehiculoController.guardarVehiculo(v);
                if(guardadoOK)
                {
                    mostrarToast("guardado realizado correctamente");
                    finish();
                }
                else
                {
                    mostrarToast("no se puedo guardar el dato");
                }
                Log.i("sql", v.toString());
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mostrarToast("vale, bien cancelado");
            }
        });
        alerta1.show();


    }

    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tipoVehiculo = adapterView.getItemAtPosition(i).toString();
        ArrayList<Tipo> tipos = VehiculoController.obtenerTodosLosTipos();
        for (Tipo t : tipos)
        {
            if(t.getTipo().equalsIgnoreCase(tipoVehiculo))
            {
                txt_nuevo_tipo.setText("Tipo de vehiculo: " + t.getIdTipo());
                idTipo = t.getIdTipo();
                Log.i("sql", String.valueOf(idTipo));
            }
        }
    }

    public void cambiar_imagen(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK) {
            imagen_seleccionada = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                img.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


}