package com.flamingo.controllers;
 
import java.io.IOException; 
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.models.Producto;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Cantidad;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.ISistema;
import com.flamingo.models.Usuario;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTFecha;
import com.flamingo.models.DTProducto;
import com.flamingo.models.Estado;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;

@WebServlet("/cambiarEstadoOrden")
public class cambiarEstadoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrdenDeCompraNoExisteException {
    	ISistema sis;
		
		if (getServletContext().getAttribute("sistema") == null) {
		    System.out.println("CREO EL SISTEMA");
		    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		    sis.crearCasos();
		} else {
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		}
        	
        Usuario user = sis.getUsuarioActual();
        
        String estadoOrden = request.getParameter("estadoOrden");
        String numeroOrden = request.getParameter("numeroOrden");
        
        System.out.println(estadoOrden);
        System.out.println(numeroOrden);
        
        if(estadoOrden == null || estadoOrden == "" || numeroOrden == null || numeroOrden == "") {
        	response.sendRedirect(request.getContextPath() + "/infoUsuario");
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(numeroOrden);

        Usuario usuario = (Usuario) sis.getUsuarioActual();
        
        
        List<OrdenDeCompra> ordenes = usuario.getOrdenesDeCompras();
        OrdenDeCompra ordenSeleccionada = null;
        
        sis.elegirOrdenDeCompra(id);
        
        for(OrdenDeCompra orden1 : ordenes) {
        	if(orden1.getNumero() == id) {
        		ordenSeleccionada = orden1;
        		break;
        	}
        }

        if (ordenSeleccionada == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra no existe");
        }
        
        if(estadoOrden.equals("entregada")) {
        	
        	System.out.println("Entré al if 1");
        	
        	if(ordenSeleccionada.getEstado() != Estado.enCamino) {
        		
        		System.out.println("Entré al if 2");
        		
        		response.sendRedirect(request.getContextPath() + "/infoUsuario");
        		return;
        	}
        }
        
        System.out.println("Llegué a pasar el if");
        
        Estado est = Estado.valueOf(estadoOrden);
        
        ordenSeleccionada.setEstado(est);
        
        request.setAttribute("usuarioActual", sis.getUsuarioActual());
        request.setAttribute("orden", ordenSeleccionada);

        response.sendRedirect(request.getContextPath() + "/infoUsuario");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | OrdenDeCompraNoExisteException e) {
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException | OrdenDeCompraNoExisteException e) {
			 e.printStackTrace();
		}
    }
}