package com.flamingo.models;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String nombreProducto;
    private String descripcion;
    private List<String> especificacion;
    private int numReferencia;
    private float precio;
    private List<String> imagenes;
    private List<Categoria> categorias;
    private Proveedor proveedor;
    private int cantEstrellas;

    // Constructor
    public Producto(String nombreProducto, String descripcion, List<String> especificacion, int numReferencia, float precio, List<String> imagenes, List<Categoria> categorias, Proveedor proveedor) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.especificacion = especificacion;
        this.numReferencia = numReferencia;
        this.precio = precio;
        
        this.imagenes = new ArrayList<>();
        this.categorias = new ArrayList<>();
        
        this.imagenes = imagenes;
        this.categorias = categorias;
        this.proveedor = proveedor;
        
        this.cantEstrellas = 0;
    }

    // Setters
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEspecificacion(List<String> especificacion) {
        this.especificacion = especificacion;
    }

    public void setNumReferencia(int numReferencia) {
        this.numReferencia = numReferencia;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    // Getters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getEspecificacion() {
        return especificacion;
    }

    public int getNumReferencia() {
        return numReferencia;
    }

    public float getPrecio() {
        return precio;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    // Método para obtener los datos básicos del producto
    public DTProducto getDTProducto() {
        return new DTProducto(nombreProducto, numReferencia, descripcion, precio);
    }

    // Método para obtener los datos detallados del producto
    public DTProductoDetallado getDTProductoDetallado() {
        // Convertir las categorías de Categoria a String
        List<String> nombresCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            nombresCategorias.add(categoria.getNombreCat());
        }
        
        List<String> nombresEspecificaciones = new ArrayList<>();
        for(String esp : especificacion) {
        	nombresCategorias.add(esp);
        }

        // Crear el objeto DTProveedor a partir del Proveedor
        DTProveedor dtProveedor = proveedor.getDTProveedor();

        // Crear y retornar el objeto DTProductoDetallado
        return new DTProductoDetallado(
            nombreProducto,
            descripcion,
            precio,
            numReferencia,
            nombresEspecificaciones,
            nombresCategorias,
            dtProveedor,
            imagenes
        );
    }

    // Método para modificar los datos del producto
    public void modificarDatosProducto(String tituloProducto, int numReferencia, String descripcion, float precio, List<String> especificacion) {
        this.nombreProducto = tituloProducto;
        this.numReferencia = numReferencia;
        this.descripcion = descripcion;
        this.precio = precio;
        this.especificacion = especificacion;
    }
    
    @Override
    public String toString() {
        return nombreProducto;  
    }

}

