package com.flamingo.controllers;
 
import java.io.IOException; 
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.SistemaFactory;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.ISistema;
import com.flamingo.models.Usuario;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.DTProducto;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;

@WebServlet("/VerOrdenDeCompra")
public class infoOrden extends HttpServlet {
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
        	
        Usuario user = (Usuario) sis.getUsuarioActual();
        
        String num = request.getParameter("ordenId");
        
        if(num == null || num == "") {
        	response.sendRedirect(request.getContextPath() + "/infoUsuario");
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(num);
        
        List<OrdenDeCompra> ordenes = user.getOrdenesDeCompras();
        OrdenDeCompra ordenSeleccionada = null;
        
        for(OrdenDeCompra orden1 : ordenes) {
        	if(orden1.getNumero() == id) {
        		ordenSeleccionada = orden1;
        		break;
        	}
        }

        if (ordenSeleccionada == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra no existe");
        }
        
        List<DTProducto> listaProd = new ArrayList<>();
        
        for(DTCantidad ca : ordenSeleccionada.getCantidad()) {
        	listaProd.add(ca.getProducto());
        }
        
        request.setAttribute("usuarioActual", sis.getUsuarioActual());
        request.setAttribute("productos", listaProd);
        request.setAttribute("orden", ordenSeleccionada);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orden/infoOrden.jsp");
        dispatcher.forward(request, response);
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