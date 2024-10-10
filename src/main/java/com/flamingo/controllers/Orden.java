package com.flamingo.controllers;
 
import java.io.IOException;
import java.lang.module.ModuleDescriptor.Requires;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.Producto;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Categoria;
import com.flamingo.models.Comentario;
import com.flamingo.models.ISistema;
import com.flamingo.models.Usuario;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Cliente;

import com.flamingo.exceptions.OrdenDeCompraNoExisteException;

@WebServlet("/VerOrdenDeCompra")
public class Orden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrdenDeCompraNoExisteException {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	Usuario user = sis.getUsuarioActual();
    	
    	String num = request.getParameter("ordenId");
    	int id = -1;
    	
    	try {
    	    id = Integer.parseInt(num);
    	} catch (NumberFormatException e) {
    	    System.out.println("Error: No fue posible convertir el String a int.");
    	}
  
    	if (!(user instanceof Cliente)) {
    	    return;
    	}
    	
    	Cliente cliente = (Cliente) user;
    	List<OrdenDeCompra> ordenes = cliente.getOrdenesDeCompras();
    	
    	
    	for(OrdenDeCompra ord : ordenes){
    		if(ord.getNumero() == id){
    			request.setAttribute("orden", ord);
    			break;
    		}
    	}
    	
    	if(id == -1){
    		throw new OrdenDeCompraNoExisteException("La orden de compra no existe");
    	}
    
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orden/orden.jsp");
    	dispatcher.forward(request, response);
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrdenDeCompraNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrdenDeCompraNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}