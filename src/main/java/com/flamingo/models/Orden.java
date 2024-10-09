package com.flamingo.models;

import java.util.List;

public class Orden {
    private String id; 
    private String fecha; 
    private List<Producto> productos; 
    private DetallesEnvio detallesEnvio; 
    private FormaPago formaPago; 

    // Constructor
    public Orden(String id, String fecha, List<Producto> productos,
                 DetallesEnvio detallesEnvio, FormaPago formaPago) {
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
        this.detallesEnvio = detallesEnvio;
        this.formaPago = formaPago;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public DetallesEnvio getDetallesEnvio() {
        return detallesEnvio;
    }

    public void setDetallesEnvio(DetallesEnvio detallesEnvio) {
        this.detallesEnvio = detallesEnvio;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }
}
