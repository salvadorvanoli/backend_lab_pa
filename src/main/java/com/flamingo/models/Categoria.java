package com.flamingo.models;

import java.util.List;

public class Categoria {
    private String nombre;
    private List<Categoria> hijas;


    // Constructor
    public Categoria(String nombre, List<Categoria> hijas) {
        this.nombre = nombre;
        this.hijas = hijas;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Categoria> getHijas() {
        return hijas;
    }

    public void setHijas(List<Categoria> hijas) {
        this.hijas = hijas;
    }
    
    public boolean tieneSubcategorias() {
        return hijas != null && !hijas.isEmpty();
    }
    
    
}
