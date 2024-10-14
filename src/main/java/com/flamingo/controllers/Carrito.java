package com.flamingo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Cliente;
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Carrito
 */
@WebServlet ("/carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ISistema sis;
		if (getServletContext().getAttribute("sistema") == null) {
			System.out.println("CREO EL SISTEMA");
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}

		HttpSession session = request.getSession();

		Object usuario = session.getAttribute("usuarioActual");	
		
		if(usuario == null) {
			// session.setAttribute("usuarioActual", null);
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp"). // Se debería enviar a una pagina de error?
					forward(request, response);
				
		} else {
			Usuario usr = (Usuario) usuario;
			Cliente cli = (Cliente) sis.getUsuarioActual();
			
			session.setAttribute("usuarioActual", usr);

			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
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
