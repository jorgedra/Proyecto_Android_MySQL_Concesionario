package com.example.concesionario.clases;

public class Tipo {

    private int idTipo;
    private String tipo;

    public Tipo(int idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }

    public Tipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTIpo) {
        this.idTipo = idTIpo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "idTIpo=" + idTipo +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
