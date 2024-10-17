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

import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

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

     
        if (emailOrNickname == null || emailOrNickname.isEmpty() || password == null || password.isEmpty()) {
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
            return;
        }

        ISistema sis = SistemaFactory.getInstancia().getISistema();
        sis.crearCasos();
        List<Usuario> usuariosRegistrados = sis.getUsuarios();

        Usuario usuarioEncontrado = null;
 
        for (Usuario usr : usuariosRegistrados) {
            if (usr.getEmail().equals(emailOrNickname) || usr.getNickname().equals(emailOrNickname)) {
                usuarioEncontrado = usr;
                break;
            }
        }

        // Validación del usuario y contraseña
        if (usuarioEncontrado == null || !usuarioEncontrado.getContrasenia().equals(password)) {
            request.setAttribute("error", "Correo electrónico/nickname o contraseña incorrectos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
        } else { //inicio de sesion exitoso
        	
            try {
				sis.iniciarSesion(emailOrNickname, password);
			} catch (ContraseniaIncorrectaException e) {
				e.printStackTrace();
			} catch (UsuarioNoEncontrado e) {
				e.printStackTrace();
			}
            
            objSesion.setAttribute("usuarioActual", usuarioEncontrado);
            
            response.sendRedirect("home");
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
