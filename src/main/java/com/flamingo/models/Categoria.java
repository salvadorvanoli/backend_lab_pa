package com.flamingo.models;

import java.util.List;



import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Categoria {
    private String nombreCat;
    private List<Producto> productos;
    private Categoria padre;
    private List<Categoria> hijos;
    private boolean tieneProductos;

    // Constructor
    public Categoria(String nombreCat, List<Categoria> hijos) {
        this.nombreCat = nombreCat;

        this.hijos = hijos;
    }

    public List<Categoria> getHijos(){
    	return this.hijos;
    }
    // Getter para nombreCat
    public String getNombreCat() {
        return nombreCat;
    }
    
    public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}

    public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Categoria getPadre() {
		return padre;
	}

	public void setPadre(Categoria padre) {
		this.padre = padre;
	}





	public boolean isTieneProductos() {
		return tieneProductos;
	}

	public void setTieneProductos(boolean tieneProductos) {
		this.tieneProductos = tieneProductos;
	}

	
    // Método para quitar un producto de la categoría
    public boolean quitarProducto(Producto p) {
        return productos.remove(p);
    }

    // Método para agregar un producto a la categoría
    public void agregarProducto(Producto p) {
        productos.add(p);
    }
    
  

    // Eliminar un hijo
    public void eliminarHijo(String clave) {
        hijos.remove(clave);
    }
    
    // Método para listar los productos de la categoría
    public List<DTProducto> listarProductos() {
        List<DTProducto> listaProductos = new ArrayList<>();
        for (Producto producto : productos) {
            listaProductos.add(producto.getDTProducto());
        }
        return listaProductos;
    }


    
    // Método para seleccionar un producto por su nombre
    public Producto seleccionarProducto(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombreProducto().equalsIgnoreCase(nombreProducto)) { // equalsIgnoreCase compara dos strings ignorando la diferencia entre mayúsculas y minúsculas
                return producto;
            }
        }
        return null; // Devuelve null si no se encuentra el producto
    }


	@Override
	public String toString() {
	    return nombreCat; 
	}
	
	 public boolean tieneSubcategorias() {
	        return hijos != null && !hijos.isEmpty();
	    }
}

