package com.flamingo.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.models.EstadoSesion;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

@WebServlet ("/infoUsuario")
public class infoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public infoUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ISistema sis = SistemaFactory.getInstancia().getISistema();
		
		HttpSession session = request.getSession();
		
		if (getServletContext().getAttribute("sistema") == null) {
			System.out.println("CREO EL SISTEMA");
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}

		
		Object usuario = session.getAttribute("usuarioActual");	
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp").
					forward(request, response);
		} else {
			Usuario usr = (Usuario) usuario;
			request.setAttribute("usuarioActual", usr);

			request.getRequestDispatcher("/WEB-INF/user/infoUsuario.jsp").
					forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
