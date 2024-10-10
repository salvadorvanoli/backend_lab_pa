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

    @Override
    public void init() throws ServletException {
        // Inicializar listas de productos y categorías al iniciar el servlet
        listaDeProductos = obtenerListaDeProductos();
        listaDeCategorias = obtenerListaDeCategorias();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar atributos en la solicitud
        request.setAttribute("productos", listaDeProductos);
        request.setAttribute("categorias", listaDeCategorias);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    private List<Producto> obtenerListaDeProductos() {
        List<Categoria> cereales = crearCategoriaCereales();
        
        Producto a = new Producto("Cereal Saludable", 4.9, "todo muy muy saludable", "1", null, cereales, "DD Market", 5, 10); 
        Producto b = new Producto("Cereal", 2.5, "todo nada nada saludable", "2", null, cereales, "BCT Market", 3, 130); 
        
        List<Producto> lista = new ArrayList<>();
        lista.add(a);
        lista.add(b);
        
        return lista;
    }

    private List<Categoria> obtenerListaDeCategorias() {
        List<Categoria> todas = new ArrayList<>();
        todas.add(new Categoria("Dulces", null));
        todas.add(new Categoria("Saludables", crearCategoriaCereales()));
        todas.add(new Categoria("Bebidas", null));
        
        return todas;
    }

    private List<Categoria> crearCategoriaCereales() {
        List<Categoria> cereales = new ArrayList<>();
        cereales.add(new Categoria("Cereales", null));
        return cereales;
    }
}
