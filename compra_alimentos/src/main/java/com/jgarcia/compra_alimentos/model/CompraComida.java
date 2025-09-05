package com.jgarcia.compra_alimentos.model;

import java.util.UUID;

public class CompraComida {
    private String nombre;
    private String genero;
    private String ciudad;
    private String frecuencia;
    private String articulo;
    private double gasto;
    private String id;

    public CompraComida(String nombre, String genero, String ciudad, String frecuencia,
                        String articulo, double gasto) {
        this.nombre = nombre;
        this.genero = genero;
        this.ciudad = ciudad;
        this.frecuencia = frecuencia;
        this.articulo = articulo;
        this.gasto = gasto;
        this.id = UUID.randomUUID().toString();
    }

    public CompraComida() {
        this.id = UUID.randomUUID().toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
