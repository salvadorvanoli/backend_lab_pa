package com.flamingo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/catalogo")
public class catalogo extends HttpServlet {
    private static final long serialVersionUID = 1L; // Mejor práctica para el serialVersionUID

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Supongamos que tienes un método que obtiene la lista de productos
        List<Producto> listaDeProductos = obtenerListaDeProductos(); // Método ficticio; debes implementarlo
        List<Categoria> listaDeCategorias = obtenerListaDeCategorias(); // Método ficticio; debes implementarlo
        
        // Configurar atributos en la solicitud
        request.setAttribute("productos", listaDeProductos);
        request.setAttribute("categorias", listaDeCategorias);
        
        // Ruta del JSP: asegúrate de que la ruta sea correcta
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../../../webapp/WEB-INF/catalogo/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    // Métodos ficticios para obtener listas de productos y categorías
    private List<Producto> obtenerListaDeProductos() {
        // Implementa tu lógica para obtener la lista de productos
        return null; // Reemplaza con la lógica real
    }

    private List<Categoria> obtenerListaDeCategorias() {
        // Implementa tu lógica para obtener la lista de categorías
        return null; // Reemplaza con la lógica real
    }
}
