package com.example.concesionario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concesionario.clases.Vehiculo;

import java.util.ArrayList;

public class ListaVehiculoAdapter extends RecyclerView.Adapter<VehiculoViewHolder> {

    // atributos
    private Context contexto = null;
    private ArrayList<Vehiculo> vehiculos = null;
    private LayoutInflater inflate = null;

    public ListaVehiculoAdapter(Context contexto, ArrayList<Vehiculo> vehiculos) {
        this.contexto = contexto;
        this.vehiculos = vehiculos;
        inflate =  LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @NonNull
    @Override
    public VehiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflate.inflate(R.layout.item_mostrar_vehiculo,parent,false);
        VehiculoViewHolder evh = new VehiculoViewHolder(mItemView,this);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoViewHolder holder, int position) {
        Vehiculo v = this.vehiculos.get(position);
        holder.getTxt_item_matricula().setText("Matricula: " + v.getMatricula());
        holder.getTxt_item_modelo().setText("Modelo: " + v.getModelo());
        holder.getTxt_item_marca().setText("Marca: " + v.getMarca());
        holder.getTxt_item_caballos().setText(String.valueOf("Caballos: " + v.getCaballos()));
        holder.getTxt_item_precio().setText(String.valueOf("Precio: " + v.getPrecio()));
        holder.getTxt_item_tipo().setText("Tipo: " + v.getTipos_id());
        if(v.getFoto() != null)
        {
            holder.img_item_vehiculo.setImageBitmap(v.getFoto());
        }
    }

    @Override
    public int getItemCount() {
        return this.vehiculos.size();
    }
    public void setListaVehiculos(ArrayList<Vehiculo> vehiculos)
    {
        this.vehiculos = vehiculos;
    }
}
