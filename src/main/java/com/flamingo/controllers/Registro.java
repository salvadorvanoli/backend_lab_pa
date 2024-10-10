package com.flamingo.controllers;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registrar")
public class Registro extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Mostrar el formulario de registro
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/registro.jsp");
        dispatcher.forward(request, response); // Cambia la ruta según tu estructura de proyecto
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros enviados desde el formulario de registro
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");

        // Aquí puedes agregar la lógica para validar y guardar el usuario
        // Por ejemplo, puedes llamar a tu sistema para registrar el usuario

        // Guardar estos valores en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nickname", nickname);

        // Redirigir a ingresar datos después de un registro exitoso
        response.sendRedirect(request.getContextPath() + "/ingresardatos"); // Cambia /ingresardatos a la ruta que desees
    }
}
