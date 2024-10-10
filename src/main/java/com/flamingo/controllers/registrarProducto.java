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
import com.flamingo.models.Usuario;
import com.flamingo.models.Categoria; // Importa la clase Categoria
import com.flamingo.models.GestorTemp; // Asegúrate de tener este import

@WebServlet("/registrarProducto")
public class registrarProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Lista de usuarios
    private static List<Usuario> usuariosRegistrados = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtén las categorías desde GestorTemp
        GestorTemp gestorTemp = new GestorTemp();
        List<Categoria> categorias = gestorTemp.obtenerCategorias();

        // Agrega las categorías al request
        request.setAttribute("categorias", categorias);

        // Redirigir al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registrarProducto/registrarProducto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
