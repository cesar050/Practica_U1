package com.practica.models;

public class Familia {
    private String id;
    private String nombre;
    private boolean tieneGenerador;
    private float presupuesto;
    private float costoGenerador;
    private float consumoPorHora;
    private float energiaGenerada;
    private String usoGenerador;

    // Constructor vacío
    public Familia() {}

    // Constructor con todos los parámetros
    public Familia(String id, String nombre, boolean tieneGenerador, float presupuesto, float costoGenerador, float consumoPorHora, float energiaGenerada, String usoGenerador) {
        this.id = id;
        this.nombre = nombre;
        this.tieneGenerador = tieneGenerador;
        this.presupuesto = presupuesto;
        this.costoGenerador = costoGenerador;
        this.consumoPorHora = consumoPorHora;
        this.energiaGenerada = energiaGenerada;
        this.usoGenerador = usoGenerador;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTieneGenerador() {
        return tieneGenerador;
    }
    public void setTieneGenerador(boolean tieneGenerador) {
        this.tieneGenerador = tieneGenerador;
    }

    public float getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public float getCostoGenerador() {
        return costoGenerador;
    }
    public void setCostoGenerador(float costoGenerador) {
        this.costoGenerador = costoGenerador;
    }

    public float getConsumoPorHora() {
        return consumoPorHora;
    }
    public void setConsumoPorHora(float consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }

    public float getEnergiaGenerada() {
        return energiaGenerada;
    }
    public void setEnergiaGenerada(float energiaGenerada) {
        this.energiaGenerada = energiaGenerada;
    }

    public String getUsoGenerador() {
        return usoGenerador;
    }
    public void setUsoGenerador(String usoGenerador) {
        this.usoGenerador = usoGenerador;
    }
}
