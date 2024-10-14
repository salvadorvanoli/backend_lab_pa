package com.flamingo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;
import com.flamingo.models.ISistema;
import com.flamingo.models.Producto;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Cliente;
import com.flamingo.models.Comentario;
import com.flamingo.models.DTFecha;
import com.google.gson.Gson;

@WebServlet("/agregarAlCarrito")
public class agregarAlCarrito extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public agregarAlCarrito() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ISistema sis;
    	if (getServletContext().getAttribute("sistema") == null) {
    	    System.out.println("CREO EL SISTEMA");
    	    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
    	    sis = (ISistema) getServletContext().getAttribute("sistema");
    	    sis.crearCasos();
    	} else {
    	    sis = (ISistema) getServletContext().getAttribute("sistema");
    	}
    	
    	int numReferencia = Integer.parseInt(request.getParameter("numReferencia"));
    	
        // Obtén el parámetro como una cadena
        String cantidadStr = request.getParameter("cantidad");

        int cantidad = 0;
        
        if (cantidadStr != null) {
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if(cantidad <= 0) {
                	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La cantidad debe ser mayor que cero.");
                    return;
                }
            } catch (NumberFormatException e) {
            	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cantidad no válida.");
                return;
            }
        }
        
        Cliente cl = (Cliente) sis.getUsuarioActual();
        
        //if(cl.getCarrito().containsKey(numReferencia)) {
        //	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El usuario ya tiene ese producto en el carrito");
        //    return;
        //}
        
        Cantidad cant = new Cantidad(sis.getProductoActual(), cantidad);
        
        cl.getCarrito().put(sis.getProductoActual().getNumReferencia(), cant);
        
        response.sendRedirect("infoProducto?productoSeleccionado=" + numReferencia);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        processRequest(request, response);
    }
}