package com.ico.envioCalificaciones.controllers;

public class Calificacion {
    private String nombre;
    private String asignatura;
    private double puntuacion;

    public Calificacion(String nombre, String asignatura, double puntuacion) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
}
