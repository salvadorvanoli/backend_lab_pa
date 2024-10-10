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
    private static final long serialVersionUID = 1L; // Mejor práctica para el serialVersionUID

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        List<Producto> listaDeProductos = obtenerListaDeProductos(); // Método ficticio; debes implementarlo
        List<Categoria> listaDeCategorias = obtenerListaDeCategorias(); // Método ficticio; debes implementarlo
        
        // Configurar atributos en la solicitud
        request.setAttribute("productos", listaDeProductos);
        request.setAttribute("categorias", listaDeCategorias);
        
        request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp").forward(request, response);
    }

    @SuppressWarnings("null")
	private List<Producto> obtenerListaDeProductos(){
    	Categoria Cerealescategoria = new Categoria("Cereales",  null);
    
    	List<Categoria> Cereales = new ArrayList<>();
    	Cereales.add(Cerealescategoria);
    	
    	Producto a = new Producto("Cereal Saludable", 4.9 , "todo muy muy saludable" , "1", null, Cereales,  "DD Market", 5, 10); 
    	Producto b = new Producto("Cereal ", 2.5 , "todo nada nada saludable" , "2", null, Cereales, "BCT Market", 3, 130); 
    	
    	List<Producto> lista = new ArrayList<>();
    	lista.add(a);
    	lista.add(b);
    	
    	return lista;
    }

    private List<Categoria> obtenerListaDeCategorias() {
    	Categoria Cerealescategoria = new Categoria("Cereales",  null);
    	
    	List<Categoria> Cereales = new ArrayList<>();
    	Cereales.add(Cerealescategoria);
    	
    	Categoria Dulces = new Categoria("Dulces", null);
    	Categoria Saludables = new Categoria("Saludables",  Cereales);
    	Categoria Bebidas = new Categoria("Bebidas",  null);
    	
    	List<Categoria> todas = new ArrayList<>();
    	todas.add(Dulces);
    	todas.add(Saludables); 
    	todas.add(Bebidas);
    	
    	return todas;
    }
}
