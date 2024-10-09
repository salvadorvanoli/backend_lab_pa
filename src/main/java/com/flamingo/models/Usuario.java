package com.flamingo.models;

import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String nickname;
    private String tipo; 
    private String email;
    private String fecha; 
    private String foto; 
    private String web; 
    private String empresa; 
    private String id;
    private String contraseña; 
    private List<Orden> ordenes; 
    private List<Producto> productos; 

    // Constructor
    public Usuario(String nombre, String apellido, String nickname, String tipo, String email,
                   String fecha, String foto, String web, String empresa, String id,
                   String contraseña, List<Orden> ordenes, List<Producto> productos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.tipo = tipo;
        this.email = email;
        this.fecha = fecha;
        this.foto = foto;
        this.web = web;
        this.empresa = empresa;
        this.id = id;
        this.contraseña = contraseña;
        this.ordenes = ordenes;
        this.productos = productos;
    }

    // Getters y Setters (omitir por brevedad)
    
    // Ejemplo de Getter
    public String getNombre() {
        return nombre;
    }

    // Ejemplo de Setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Agregar otros getters y setters aquí...
}
