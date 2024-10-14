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
	private int estrellas;
	private int cantCompras;
    private String nombreTienda;
    private List<Comentario> comentarios;


    // Constructor


    public Producto(String nombreProducto, String descripcion, List<String> especificacion, int numReferencia, float precio, List<String> imagenes, List<Categoria> categorias, Proveedor proveedor, String nombreTienda) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.especificacion = especificacion;
        this.numReferencia = numReferencia;
        this.precio = precio;
        this.estrellas = 0;
        this.nombreTienda = nombreTienda;
        this.cantCompras = 0;
        
        
        this.comentarios = new ArrayList<>();
        
        this.imagenes = imagenes;
        this.categorias = categorias;
        this.proveedor = proveedor;
        
    }
    
    public List<Comentario> getComentarios() {
    	return this.comentarios;
    }
    
    public void setComentarios(List<Comentario> comentarios) {
    	this.comentarios = comentarios;
    }
    
    public void agregarComentario(Comentario nuevo) {
    	this.comentarios.add(nuevo);
    }
    
    public int getEstrellas() {
    	return this.estrellas;
    }
    
    public void setEstrellas(int estrellas) {
    	this.estrellas = estrellas;
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
    
    public void setNombreTienda(String nombreTienda) {
    	this.nombreTienda = nombreTienda;
    }
    
    public void setCantCompras(int cantCompras) {
    	this.cantCompras = cantCompras;
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
        return new DTProducto(nombreProducto, numReferencia, descripcion, precio, imagenes);
    }
    
    // Función para buscar un comentario por ID y agregar respuesta en el comentario correcto.
    public Comentario getComentario(int id) {
        // Recorremos los comentarios principales
        for (Comentario comentario : comentarios) {
            // Si el comentario tiene el ID que estamos buscando
            if (comentario.getId() == id) {
                return comentario; // Comentario encontrado
            }
            
            // Si no es el comentario principal, revisamos las respuestas
            Comentario respuestaEncontrada = buscarEnRespuestas(comentario.getComentarios(), id);
            if (respuestaEncontrada != null) {
                return respuestaEncontrada;
            }
        }
        // Si no se encontró, retornamos null
        return null;
    }

    // Función auxiliar para buscar el comentario en las respuestas (subcomentarios)
    private Comentario buscarEnRespuestas(List<Comentario> respuestas, int id) {
        // Recorremos las respuestas (subcomentarios)
        for (Comentario respuesta : respuestas) {
            // Si la respuesta tiene el ID que estamos buscando
            if (respuesta.getId() == id) {
                return respuesta; // Respuesta encontrada
            }

            // Revisamos si esta respuesta tiene más respuestas anidadas
            Comentario respuestaAnidada = buscarEnRespuestas(respuesta.getComentarios(), id);
            if (respuestaAnidada != null) {
                return respuestaAnidada; // Respuesta anidada encontrada
            }
        }
        // Si no se encontró, retornamos null
        return null;
    }
    
    public String getNombreTienda() {
    	return this.nombreTienda;
    }
    
    public int getCantCompras() {
    	return this.cantCompras;
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
    
    public int getIdComentarioMayor() {
        return buscarIdMayorComentarios(comentarios, 0); // Iniciamos la búsqueda desde 0
    }

    // Método auxiliar para buscar el ID mayor en la lista de comentarios
    private int buscarIdMayorComentarios(List<Comentario> comentarios, int maxId) {
        for (Comentario comentario : comentarios) {
            // Convertimos el ID del comentario actual a un número entero para compararlo
            int idActual = comentario.getId();

            // Actualizamos el máximo si el ID actual es mayor
            if (idActual > maxId) {
                maxId = idActual;
            }

            // Llamamos recursivamente a la función para buscar en las respuestas del comentario
            maxId = buscarIdMayorComentarios(comentario.getComentarios(), maxId);
        }
        return maxId; // Retornamos el ID mayor encontrado
    }

}