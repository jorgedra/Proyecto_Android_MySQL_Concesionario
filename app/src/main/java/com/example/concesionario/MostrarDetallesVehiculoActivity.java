package com.example.concesionario;

import static com.example.concesionario.utilidades.ImagenesBlobBitmap.bytes_to_bitmap;

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
import com.example.concesionario.modelo.ConfiguracionDB;

import java.io.IOException;
import java.util.ArrayList;

public class MostrarDetallesVehiculoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edt_nuevo_matricula = null;
    private EditText edt_nuevo_modelo = null;
    private EditText edt_nuevo_marca = null;
    private EditText edt_nuevo_caballos = null;
    private EditText edt_nuevo_precio = null;
    private TextView txt_nuevo_tipo = null;
    private Button bt_actualizar = null;
    private Button bt_borrar = null;
    private Spinner sp_tipo = null;
    private String tipoVehiculo = null;
    private String matriculaAntigua = null;
    private int idTipo;
    private int tipoIntent;
    private Vehiculo v;

    ImageView img_detalle_vehiculo;
    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_vehiculo);
        edt_nuevo_matricula = (EditText) findViewById(R.id.edt_nuevo_matricula);
        edt_nuevo_modelo = (EditText) findViewById(R.id.edt_nuevo_modelo);
        edt_nuevo_marca = (EditText) findViewById(R.id.edt_nuevo_marca);
        edt_nuevo_caballos = (EditText) findViewById(R.id.edt_nuevo_caballos);
        edt_nuevo_precio = (EditText) findViewById(R.id.edt_nuevo_precio);
        txt_nuevo_tipo = (TextView) findViewById(R.id.txt_nuevo_tipo);
        bt_actualizar = (Button) findViewById(R.id.bt_insertar);
        sp_tipo = (Spinner) findViewById(R.id.sp_tipo);
        img_detalle_vehiculo = (ImageView) findViewById(R.id.img_coche_detalles);

        Intent intent = getIntent();

        if(intent != null)
        {
            v = (Vehiculo) intent.getSerializableExtra(VehiculoViewHolder.EXTRA_VEHICULO);
            edt_nuevo_matricula.setText(v.getMatricula());
            edt_nuevo_modelo.setText(v.getModelo());
            edt_nuevo_marca.setText(v.getMarca());
            edt_nuevo_caballos.setText(String.valueOf(v.getCaballos()));
            edt_nuevo_precio.setText(String.valueOf(v.getPrecio()));
            txt_nuevo_tipo.setText(String.valueOf(v.getTipos_id()));
            tipoIntent = v.getTipos_id();
            byte foto[] = intent.getByteArrayExtra(VehiculoViewHolder.EXTRA_IMG_VEHICULO);
            if(foto != null)
            {
                Bitmap fotov = bytes_to_bitmap(foto, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                img_detalle_vehiculo.setImageBitmap(fotov);
            }

            matriculaAntigua = v.getMatricula();
        }



        ArrayList<Tipo> tiposA = VehiculoController.obtenerTodosLosTipos();
        ArrayList tipos = new ArrayList();
        for (Tipo a: tiposA) {
            tipos.add(a.getTipo());
        }
        sp_tipo.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipo.setAdapter(adapter);

        if(v.getTipos_id() != 0){
            for (int i = 1; i < sp_tipo.getCount(); i++)
            {
                if(i+1 == v.getTipos_id())
                {
                    sp_tipo.setSelection(i);
                }
            }
        }
    }

    public void actualizar(View view) {
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
                Vehiculo v = new Vehiculo(matricula, modelo, marca, caballos, precio,idTipo);
                img_detalle_vehiculo.buildDrawingCache();
                Bitmap bm_foto = img_detalle_vehiculo.getDrawingCache();
                v.setFoto(bm_foto);
                boolean guardadoOK = VehiculoController.actualizaVehiculo(v,matriculaAntigua);
                MostrarVehiculoActivity.adaptadorVehiculos.notifyDataSetChanged();
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


    public void borrar(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("son correctos los datos? (SI|NO)");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String matricula = String.valueOf(edt_nuevo_matricula.getText());
                boolean guardadoOK = VehiculoController.borrarVehiculo(matricula);
                if (guardadoOK) {
                    mostrarToast("vehiculo borrado correctamente");
                    finish();
                } else {
                    mostrarToast("no se puedo borrar el vehiculo");
                }
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
            }
        }

    }


    public void cambiar_imagen_detalle(View view) {
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
                img_detalle_vehiculo.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}