package com.flamingo.controllers;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.exceptions.UsuarioNoEncontrado; 
import com.flamingo.controllers.gestorTemporal; 
import com.flamingo.models.EstadoSesion;
import com.flamingo.models.Usuario;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarsesion") // Cambiado para coincidir con tu ruta
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

        // Intenta buscar el usuario por email o nickname
        Usuario usr = null;
        try {
            usr = gestorTemporal.getInstance().buscarUsuario(emailOrNickname);
        } catch (UsuarioNoEncontrado ex) {
            // El usuario no fue encontrado, se considerará como login incorrecto
            nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
            request.setAttribute("error", "Correo electrónico/nickname o contraseña incorrectos.");
            objSesion.setAttribute("estado_sesion", nuevoEstado);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
            return; // Salir del método
        }

        // Verifica la contraseña
        if (!usr.getContrasena().equals(password)) {
            nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
            request.setAttribute("error", "Correo electrónico/nickname o contraseña incorrectos.");
        } else {
            nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
            // Setea el usuario logueado
            request.getSession().setAttribute("usuario_logueado", usr);
        }

        objSesion.setAttribute("estado_sesion", nuevoEstado);

        // Redirige a la página principal
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
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
