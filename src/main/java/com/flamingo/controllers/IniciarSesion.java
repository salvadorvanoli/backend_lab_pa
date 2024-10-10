package com.flamingo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.models.EstadoSesion;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IniciarSesion() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String emailOrNickname = request.getParameter("emailOrNickname");
        String password = request.getParameter("password");
        EstadoSesion nuevoEstado;

        ISistema sis = SistemaFactory.getInstancia().getISistema();
        List<Usuario> usuariosRegistrados = sis.getUsuarios();
        

        Usuario usuarioEncontrado = null;

        for (Usuario usr : usuariosRegistrados) {
            if (usr.getEmail().equals(emailOrNickname) || usr.getNickname().equals(emailOrNickname)) {
                usuarioEncontrado = usr;
                break;
            }
        }

        
        if (usuarioEncontrado == null || !usuarioEncontrado.getContrasenia().equals(password)) {
            nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
            request.setAttribute("error", "Correo electrónico/nickname o contraseña incorrectos.");
        } else {
            // El inicio de sesión fue exitoso
            nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
            sis.iniciarSesion(usuarioEncontrado); // Establece el usuario actual en ISistema 
            //HAY QUE IMPLEMENTARLO EN SISTEMAA
            
        }

     

        // Redirige a la página principal o a una página de error
        if (nuevoEstado == EstadoSesion.LOGIN_CORRECTO) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        } else {
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
        }

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
