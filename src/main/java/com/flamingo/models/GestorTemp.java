package com.flamingo.models;

import java.util.ArrayList;
import java.util.List;

public class GestorTemp {
    private List<Categoria> categorias;     
    private List<Producto> productos;        
    private List<Usuario> usuarios;         

    // Constructor
    public GestorTemp() {
        this.categorias = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
 
        cargarCategorias();
    }
    
    private void cargarCategorias() {
        // Inicializar la lista de categorías
        categorias = new ArrayList<>();

        // Categorías principales
        Categoria electronica = new Categoria("Electrónica", new ArrayList<>());
        
        // Subcategoría de Celulares
        Categoria celulares = new Categoria("Celulares", new ArrayList<>());
        celulares.getHijos().add(new Categoria("Samsung", new ArrayList<>())); // Subcategoría de Samsung
        celulares.getHijos().add(new Categoria("iPhone", new ArrayList<>()));  // Subcategoría de iPhone
        celulares.getHijos().add(new Categoria("Xiaomi", new ArrayList<>()));   // Subcategoría de Xiaomi
        electronica.getHijos().add(celulares);
        
        // Subcategoría de Laptops
        Categoria laptops = new Categoria("Laptops", new ArrayList<>());
        laptops.getHijos().add(new Categoria("Lenovo", new ArrayList<>())); // Subcategoría de Lenovo
        laptops.getHijos().add(new Categoria("Dell", new ArrayList<>()));    // Puedes agregar más marcas si lo deseas
        electronica.getHijos().add(laptops);
        
        categorias.add(electronica);
        
        // Otras categorías
        Categoria moda = new Categoria("Moda", new ArrayList<>());
        moda.getHijos().add(new Categoria("Vestimenta Masculina", new ArrayList<>()));
        moda.getHijos().add(new Categoria("Vestimenta Femenina", new ArrayList<>()));
        categorias.add(moda);
        
        Categoria hogarYCocina = new Categoria("Hogar y Cocina", new ArrayList<>());
        categorias.add(hogarYCocina);
        
        Categoria bellezaYSalud = new Categoria("Belleza y Salud", new ArrayList<>());
        bellezaYSalud.getHijos().add(new Categoria("Suplementos", new ArrayList<>()));
        bellezaYSalud.getHijos().add(new Categoria("Maquillaje", new ArrayList<>()));
        categorias.add(bellezaYSalud);
        
        Categoria librosMusicaYPeliculas = new Categoria("Libros, Música y Películas", new ArrayList<>());
        librosMusicaYPeliculas.getHijos().add(new Categoria("Libros", new ArrayList<>()));
        librosMusicaYPeliculas.getHijos().add(new Categoria("Música", new ArrayList<>()));
        librosMusicaYPeliculas.getHijos().add(new Categoria("Películas", new ArrayList<>()));
        categorias.add(librosMusicaYPeliculas);
        
        Categoria automotriz = new Categoria("Automotriz", new ArrayList<>());
        categorias.add(automotriz);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Métodos para manejar categorías
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public Categoria obtenerCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombreCat().equals(nombre)) {
                return categoria;
            }
        }
        return null; // Retorna null si no se encuentra la categoría
    }

    public List<Categoria> obtenerCategorias() {
        return categorias;
    }

    // Métodos para manejar productos
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto obtenerProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombreProducto().equals(nombre)) {
                return producto;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    // Métodos para manejar usuarios
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario obtenerUsuario(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null; // Retorna null si no se encuentra el usuario
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}
