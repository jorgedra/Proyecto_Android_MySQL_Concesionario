package com.example.concesionario.modelo;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import com.example.concesionario.clases.Tipo;
import com.example.concesionario.clases.Vehiculo;
import com.example.concesionario.modelo.ConfiguracionDB;
import com.example.concesionario.utilidades.ImagenesBlobBitmap;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehiculoDB {

    public static ArrayList<Vehiculo> obtenerVehiculos() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM vehiculos";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                String matricula = resultado.getString("matricula");
                String modelo = resultado.getString("modelo");
                String marca = resultado.getString("marca");
                Integer caballos = resultado.getInt("caballos");
                Double precio = resultado.getDouble("precio");
                Integer Tipos_id = resultado.getInt("Tipos_id");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Vehiculo v = new Vehiculo(matricula, modelo,marca, caballos,precio, Tipos_id);
                if(foto != null)
                {

                    byte[] bfoto = ImagenesBlobBitmap.blob_to_bytes(foto);
                    bm_foto = ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray(bfoto,ConfiguracionDB.ANCHO_IMAGENES_BITMAP,ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                    v.setFoto(bm_foto);
                }
                vehiculos.add(v);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return vehiculos;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return vehiculos;
        }
    }

    public static ArrayList<Tipo> obtenerTipos() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM tipos ORDER BY idTipo";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                String tipo = resultado.getString("Tipo");
                Integer idTipo = resultado.getInt("idTipo");
                Tipo elTipo = new Tipo(idTipo,tipo);
                tipos.add(elTipo);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return tipos;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return tipos;
        }
    }

    public static boolean borrarVehiculo(String matricula) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "DELETE FROM vehiculos WHERE (matricula = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1, matricula);
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean guardarVehiculo(Vehiculo v) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO vehiculos (matricula, modelo,marca, caballos,precio, Tipos_id,foto) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1, v.getMatricula());
            sentencia.setString(2, v.getModelo());
            sentencia.setString(3, v.getMarca());
            sentencia.setInt(4, v.getCaballos());
            sentencia.setDouble(5, v.getPrecio());
            sentencia.setInt(6, v.getTipos_id());
            if(v.getFoto() != null)
            {
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(v.getFoto());
                sentencia.setBytes(7,bl1);
            }
            else{
                sentencia.setBlob(7, (InputStream) null);
            }
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean actualizarVehiculos(Vehiculo v, String matriculaAntigua) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "UPDATE vehiculos SET matricula = ?, modelo = ?, marca = ?, caballos = ?, precio = ? , Tipos_id = ? , foto = ? WHERE matricula = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, v.getMatricula());
            pst.setString(2, v.getModelo());
            pst.setString(3, v.getMarca());
            pst.setInt(4, v.getCaballos());
            pst.setDouble(5, v.getPrecio());
            pst.setInt(6,v.getTipos_id());
            if(v.getFoto() != null)
            {
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(v.getFoto());
                pst.setBytes(7,bl1);
            }
            else
            {
                pst.setBlob(7,(InputStream) null);
            }
            pst.setString(8, matriculaAntigua);
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }


}
