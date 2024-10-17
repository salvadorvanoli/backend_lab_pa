package com.flamingo.controllers;

import java.io.IOException;

import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;

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
        
        /*
        System.out.println("registro Email: " + email);
        System.out.println("registro Nickname: " + nickname);
        */
        
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        try {
			sis.registro(nickname, email);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
        
        // Guardar estos valores en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nickname", nickname);
        
        response.sendRedirect(request.getContextPath() + "/ingresardatos"); 
    }
}
