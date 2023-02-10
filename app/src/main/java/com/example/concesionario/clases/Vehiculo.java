package com.example.concesionario.clases;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Objects;

public class Vehiculo implements Serializable {

    private String matricula;
    private String modelo;
    private String marca;
    private int caballos;
    private double precio;
    private int Tipos_id;
    private Bitmap foto;

    public Vehiculo(String matricula, String modelo, String marca, int caballos, double precio, int Tipos_id) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.caballos = caballos;
        this.precio = precio;
        this.Tipos_id = Tipos_id;
    }

    public Vehiculo(String matricula, String modelo, String marca, int caballos, double precio, int Tipos_id, Bitmap foto) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.caballos = caballos;
        this.precio = precio;
        this.Tipos_id = Tipos_id;
        this.foto = foto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipos_id() {
        return Tipos_id;
    }

    public void setTipos_id(int Tipos_id) {
        this.Tipos_id = Tipos_id;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", caballos=" + caballos +
                ", precio=" + precio +
                ", Tipos_id=" + Tipos_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return matricula.equals(vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
