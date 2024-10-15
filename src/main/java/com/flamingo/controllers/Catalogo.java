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
import jakarta.servlet.http.HttpSession;

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
        	
        	ISistema sis;
    		
    		HttpSession session = request.getSession();
    		
    		if (getServletContext().getAttribute("sistema") == null) {
    		    System.out.println("CREO EL SISTEMA");
    		    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
    		    sis = (ISistema) getServletContext().getAttribute("sistema");
    		    sis.crearCasos();
    		} else {
    		    sis = (ISistema) getServletContext().getAttribute("sistema");
    		}
        	
            List<Producto> listaDeProductos = sis.getProductos();
            HashMap<String, Categoria> listaDeCategorias = sis.getCategorias();
            
            String textoBusqueda = request.getParameter("textoBusqueda");
            
            if(textoBusqueda != null) {
            	request.setAttribute("textoBusqueda", textoBusqueda);
            }

            // Asegurarse de que las listas no sean nulas
            if (listaDeProductos == null) {
                listaDeProductos = new ArrayList<>();
            }
            if (listaDeCategorias == null) {
                listaDeCategorias = new HashMap<>();
            }

            request.setAttribute("productos", listaDeProductos);
            request.setAttribute("categorias", listaDeCategorias);

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



}