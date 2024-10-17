package com.flamingo.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.ISistema;
import com.flamingo.models.Proveedor;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Cliente;

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
        
        if(sis.getUsuarioActual() instanceof Proveedor) {
        	response.sendRedirect("infoProducto?productoSeleccionado=" + numReferencia);
            return;
        }
        
        Cliente cl = (Cliente) sis.getUsuarioActual();
        
        if(cl.getCarrito().containsKey(numReferencia)) {
        	response.sendRedirect("infoProducto?productoSeleccionado=" + numReferencia);
            return;
        }
        
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