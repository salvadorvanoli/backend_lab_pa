package com.flamingo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.flamingo.models.Usuario;
import com.flamingo.models.Orden;
import com.flamingo.models.Producto;

@WebServlet("/RegistrarUsuario")
public class Registro extends HttpServlet {
    
    // Simulamos una base de datos temporal en memoria (lista de usuarios)
    private static List<Usuario> usuariosRegistrados = new ArrayList<>();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nickname = request.getParameter("nickname");
        String tipo = request.getParameter("tipo"); 
        String email = request.getParameter("email");
        String fecha = request.getParameter("fecha"); 
        String foto = request.getParameter("foto"); 
        String web = request.getParameter("web"); 
        String empresa = request.getParameter("empresa");
        String id = request.getParameter("id"); 
        String contrasena = request.getParameter("contrasena");

        // Validar si los campos están vacíos
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || 
            nickname == null || nickname.isEmpty() || email == null || email.isEmpty() || 
            contrasena == null || contrasena.isEmpty()) {
            request.setAttribute("errorMessage", "Por favor, completa todos los campos.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Validar si el correo electrónico es válido (se puede hacer también en el JSP)
        if (!esCorreoValido(email)) {
            request.setAttribute("errorMessage", "Correo electrónico inválido.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Validar si el usuario o el email ya están registrados
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getEmail().equals(email) || usuario.getNickname().equals(nickname)) {
                request.setAttribute("errorMessage", "Ya existe un usuario con ese correo electrónico o nickname.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }
        }

        // Si todo es válido, crear un nuevo usuario y agregarlo a la lista
        Usuario nuevoUsuario = new Usuario(nombre, apellido, nickname, tipo, email, fecha, foto, web, empresa, id, contrasena, new ArrayList<>(), new ArrayList<>());
        usuariosRegistrados.add(nuevoUsuario);

        // Guardar las credenciales temporales en sesión (o cualquier otra acción)
        request.getSession().setAttribute("credencialesTemp", nuevoUsuario);

        // Redirigir al siguiente paso
        response.sendRedirect("ingresarDatos.jsp");
    }

    // validar el formato del correo
    private boolean esCorreoValido(String email) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(emailRegex);
    }
}
