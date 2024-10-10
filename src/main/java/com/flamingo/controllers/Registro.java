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
import jakarta.servlet.http.HttpSession;
import com.flamingo.models.Usuario;

@WebServlet("/registrar")
public class Registro extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros enviados desde el JSP
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");

        // Guardar estos valores en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nickname", nickname);

        // Redirigir a ingresar datos
        response.sendRedirect(request.getContextPath() + "/ingresardatos"); // Cambia /proximaPagina a la ruta que desees
    }
}

