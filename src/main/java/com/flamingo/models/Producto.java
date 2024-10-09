package com.flamingo.models;

import java.util.List;

public class Producto {
    private String nombre;
    private double precio;
    private String descripcion;
    private String id; 
    private List<String> imagenes; 

    // Constructor
    public Producto(String nombre, double precio, String descripcion, String id, List<String> imagenes) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id = id;
        this.imagenes = imagenes;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
}
