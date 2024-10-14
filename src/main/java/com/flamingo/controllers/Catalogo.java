package com.flamingo.controllers;
 
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.Producto;
import com.flamingo.models.SistemaFactory;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.models.Categoria;
import com.flamingo.models.Comentario;
import com.flamingo.models.ISistema;


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	ISistema sis = SistemaFactory.getInstancia().getISistema();
        	
            List<Producto> listaDeProductos = null;
            List<Categoria> listaDeCategorias = null;

            // Asegurarse de que las listas no sean nulas
            if (listaDeProductos == null) {
                listaDeProductos = new ArrayList<>();
            }
            if (listaDeCategorias == null) {
                listaDeCategorias = new ArrayList<>();
            }

            request.setAttribute("productos", listaDeProductos);
            request.setAttribute("categorias", listaDeCategorias);
            
            // Serializar las listas a JSON
            String categoriasJSON = ListaCategoriasToJson(listaDeCategorias);
            String productosJSON = ListaPproductosToJson(listaDeProductos);

            request.setAttribute("productosJSON", productosJSON);
            request.setAttribute("categoriasJSON", categoriasJSON);

            // Redirigir a la página JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }
    
    
    private String ListaCategoriasToJson(List<Categoria> categorias) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (int i = 0; i < categorias.size(); i++) {
            Categoria cat = categorias.get(i);
            jsonBuilder.append(categoriaToJson(cat)); // Convertir cada categoría a JSON
            
            if (i < categorias.size() - 1) {
                jsonBuilder.append(","); // Agregar coma entre categorías
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String categoriaToJson(Categoria cat) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format("{\"nombreCat\":\"%s\",\"tieneProductos\":%b,\"hijos\":[",
                cat.getNombreCat(), cat.isTieneProductos()));

        // Serializar hijos
        StringBuilder hijosJsonBuilder = new StringBuilder();
        HashMap<String, Categoria> hijosHash = cat.getHijos();
        if (hijosHash != null && !hijosHash.isEmpty()) {
            int j = 0;
            for (Categoria subcat : hijosHash.values()) {
                if (j > 0) hijosJsonBuilder.append(",");
                hijosJsonBuilder.append(categoriaToJson(subcat)); // Llamada recursiva
                j++;
            }
        }
        jsonBuilder.append(hijosJsonBuilder).append("]}");
        return jsonBuilder.toString();
    }
    
    private String ListaPproductosToJson(List<Producto> productos) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (int i = 0; i < productos.size(); i++) {
            Producto prod = productos.get(i);
            jsonBuilder.append(productoToJson(prod)); // Convertir cada producto a JSON
            
            if (i < productos.size() - 1) {
                jsonBuilder.append(","); // Agregar coma entre productos
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String productoToJson(Producto prod) {
        String precioFormateado = String.format(Locale.US, "%.2f", prod.getPrecio()); 
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format(
            "{\"nombreProducto\":\"%s\",\"descripcion\":\"%s\",\"especificacion\":\"%s\"," +
            "\"numReferencia\":%d,\"precio\":%s,\"imagenes\":[",
            prod.getNombreProducto(), prod.getDescripcion(), prod.getEspecificacion(),
            prod.getNumReferencia(), precioFormateado)); // Usar el precio formateado

        // Serializar imágenes
        StringBuilder imagenesJsonBuilder = new StringBuilder();
        List<String> imagenes = prod.getImagenes();
        for (int j = 0; j < imagenes.size(); j++) {
            if (j > 0) imagenesJsonBuilder.append(",");
            imagenesJsonBuilder.append("\"").append(imagenes.get(j)).append("\"");
        }
        jsonBuilder.append(imagenesJsonBuilder)
            .append("],\"categorias\":[],\"proveedor\":\"")
            .append(prod.getProveedor() != null ? prod.getProveedor().toString() : "")
            .append("\",\"estrellas\":").append(prod.getEstrellas())
            .append(",\"nombreTienda\":\"").append(prod.getNombreTienda()).append("\"}");
        
        return jsonBuilder.toString();
    }



}