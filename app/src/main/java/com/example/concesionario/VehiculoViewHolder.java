package com.example.concesionario;

import static com.example.concesionario.utilidades.ImagenesBlobBitmap.bitmap_to_bytes_png;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.VehiculoDB;

import java.util.ArrayList;

public class VehiculoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_IMG_VEHICULO = "com.example.vehiculoviewholder.vehiculo_img";
    public static final String EXTRA_VEHICULO = "com.example.vehiculodetalle.vehiculo";
    // atributos
    private TextView txt_item_matricula;
    private TextView txt_item_modelo;
    private TextView txt_item_marca;
    private TextView txt_item_caballos;
    private TextView txt_item_precio;
    public ImageView img_item_vehiculo;
    private TextView txt_item_tipo;
    //-------------------------------------
    public static int vPosition;
    private ListaVehiculoAdapter lpv;


    public VehiculoViewHolder(@NonNull View itemView, ListaVehiculoAdapter listaProductosAdapter) {
        super(itemView);
        txt_item_matricula = (TextView) itemView.findViewById(R.id.txt_item_matricula);
        txt_item_precio = (TextView) itemView.findViewById(R.id.txt_item_precio);
        txt_item_tipo = (TextView) itemView.findViewById(R.id.txt_item_tipo);
        txt_item_caballos = (TextView) itemView.findViewById((R.id.txt_caballos));
        txt_item_marca = (TextView) itemView.findViewById((R.id.txt_marca));
        txt_item_modelo = (TextView) itemView.findViewById((R.id.txt_item_modelo));
        img_item_vehiculo = (ImageView) itemView.findViewById(R.id.img_item_vehiculo);
        //-----------------------------------------------------------------------------
        lpv = listaProductosAdapter;
        itemView.setOnClickListener(this);
    }

    public TextView getTxt_item_matricula() {
        return txt_item_matricula;
    }

    public void setTxt_item_matricula(TextView txt_item_codropa) {
        this.txt_item_matricula = txt_item_matricula;
    }

    public TextView getTxt_item_precio() {
        return txt_item_precio;
    }

    public void setTxt_item_precio(TextView txt_item_precio) {
        this.txt_item_precio = txt_item_precio;
    }

    public TextView getTxt_item_tipo() {
        return txt_item_tipo;
    }

    public void setTxt_item_tipo(TextView txt_item_tipo) {
        this.txt_item_tipo = txt_item_tipo;
    }

    public TextView getTxt_item_modelo() {return txt_item_modelo;}

    public void setTxt_item_modelo(TextView txt_item_modelo) {
        this.txt_item_modelo = txt_item_modelo;
    }

    public TextView getTxt_item_marca() {return txt_item_marca;}

    public void setTxt_item_marca(TextView txt_item_color) {
        this.txt_item_marca = txt_item_marca;
    }

    public TextView getTxt_item_caballos() {
        return txt_item_caballos;
    }

    public void setTxt_item_caballos(TextView txt_item_caballos) {
        this.txt_item_caballos = txt_item_caballos;
    }

    public ImageView getImg_item_vehiculo() {
        return img_item_vehiculo;
    }

    public void setImg_item_vehiculo(ImageView img_item_vehiculo) {
        this.img_item_vehiculo = img_item_vehiculo;
    }

    @Override
    public void onClick(View view) {
        vPosition = getAdapterPosition();
        ArrayList<Vehiculo> vehiculos = this.lpv.getVehiculos();
        Vehiculo vehiculo = vehiculos.get(vPosition);
        Intent intent = new Intent(lpv.getContexto(), MostrarDetallesVehiculoActivity.class);
        Vehiculo v = new Vehiculo(vehiculo.getMatricula(),vehiculo.getModelo(),vehiculo.getMarca(),vehiculo.getCaballos(),vehiculo.getPrecio(),vehiculo.getTipos_id());
        if(vehiculo.getFoto() != null)
        {
            byte foto[] = bitmap_to_bytes_png(vehiculo.getFoto());
            intent.putExtra(EXTRA_IMG_VEHICULO,foto);
        }
        intent.putExtra(EXTRA_VEHICULO, v);
        lpv.getContexto().startActivity(intent);
    }
}
