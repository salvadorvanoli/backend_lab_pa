package com.flamingo.models;
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
    private HashMap<String, Categoria> hijos;
    private boolean tieneProductos;

    
    public boolean tieneSubcategorias() {
        return !hijos.isEmpty(); // Devuelve true si tiene subcategorías (si hijos no está vacío)
    }
    
    // Constructor
    public Categoria(String nombreCat, boolean tieneProductos, Categoria padre) {
        this.nombreCat = nombreCat;
        this.productos = new ArrayList<>();
        this.tieneProductos = tieneProductos;
        this.padre = padre;
        this.hijos = new HashMap<>();
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

	public HashMap<String, Categoria> getHijos() {
		return hijos;
	}

	public void setHijos(HashMap<String, Categoria> hijos) {
		this.hijos = hijos;
	}

	public boolean isTieneProductos() {
		return tieneProductos;
	}

	public void setTieneProductos(boolean tieneProductos) {
		this.tieneProductos = tieneProductos;
	}

	// Método para obtener los datos básicos de la categoría
    public DTCategoria getDTCategoria() {
    	HashMap<String, DTCategoria> lista = new HashMap<>();
    	for (Categoria cat : this.hijos.values()) {
    		DTCategoria dt = cat.getDTCategoria();
    		lista.put(dt.getNombreCat(), dt);
    	}
        return new DTCategoria(nombreCat, lista);
    }

    // Método para quitar un producto de la categoría
    public boolean quitarProducto(Producto p) {
        return productos.remove(p);
    }

    // Método para agregar un producto a la categoría
    public void agregarProducto(Producto p) {
        productos.add(p);
    }
    
    // Agregar un hijo
    public void agregarHijo(String clave, Categoria categoriaHija) {
        hijos.put(clave, categoriaHija);
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

    // Método para listar los hijos de una categoría
    public List<Categoria> listarHijos() {
        List<Categoria> listaHijos = new ArrayList<>();
        for (Map.Entry<String, Categoria> entry : hijos.entrySet()) { // Iterar sobre un HashMap || Opcionalmente, si se quiere la clave: String clave = entry.getKey();
            Categoria cat = entry.getValue();
            listaHijos.add(cat);
        }
        return listaHijos;
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
	

}