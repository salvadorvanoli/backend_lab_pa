package com.flamingo.models;

public class DetallesEnvio {
    private String nombre;
    private String apellido;
    private String direccion1;
    private String direccion2;
    private String departamento;
    private String ciudad;
    private String codPostal;
    private String numTelefono;
    private String tipoEnvio; 
    private double precioEnvio;

    // Constructor
    public DetallesEnvio(String nombre, String apellido, String direccion1, String direccion2,
                         String departamento, String ciudad, String codPostal,
                         String numTelefono, String tipoEnvio, double precioEnvio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.numTelefono = numTelefono;
        this.tipoEnvio = tipoEnvio;
        this.precioEnvio = precioEnvio;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public double getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(double precioEnvio) {
        this.precioEnvio = precioEnvio;
    }
}
