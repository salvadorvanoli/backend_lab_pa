package com.flamingo.controllers;
 
import java.io.IOException; 
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.SistemaFactory;
import com.flamingo.models.ISistema;
import com.flamingo.models.Usuario;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Estado;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;

@WebServlet("/cambiarEstadoOrden")
public class cambiarEstadoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrdenDeCompraNoExisteException {
    	ISistema sis;
		
		if (getServletContext().getAttribute("sistema") == null) {
		    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		    sis.crearCasos();
		} else {
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		}
        
        String estadoOrden = request.getParameter("estadoOrden");
        String numeroOrden = request.getParameter("numeroOrden");
        
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
        	
        	if(ordenSeleccionada.getEstado() != Estado.enCamino) {
        		
        		response.sendRedirect(request.getContextPath() + "/infoUsuario");
        		return;
        	}
        }
        
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