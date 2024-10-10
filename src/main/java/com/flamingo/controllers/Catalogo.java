package com.flamingo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.Producto;
import com.flamingo.models.Categoria;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<Producto> listaDeProductos;
    private List<Categoria> listaDeCategorias;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.listaDeProductos = obtenerListaDeProductos();
    	this.listaDeCategorias = obtenerListaDeCategorias(); 
    	
        request.setAttribute("productos", listaDeProductos);
        request.setAttribute("categorias", listaDeCategorias);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    private List<Producto> obtenerListaDeProductos() {
    	return null;
    }

    private List<Categoria> obtenerListaDeCategorias() {
        return null;
    }


}
