package com.flamingo.controllers;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

/**
 * Servlet implementation class Usuarios
 */
@WebServlet ("/carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String usuario = request.getParameter("usuarioActual");
		ISistema sis = SistemaFactory.getInstancia().getISistema();
		sis.crearCasos();
		try {
			sis.elegirCliente("Salva");
		} catch(UsuarioNoExisteException e) {
			
		}
		request.setAttribute("usuarioActual", sis.getUsuarioActual());
		Object usuario = request.getAttribute("usuarioActual");	
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
					forward(request, response);
				
		} else {
			// Usuario usr;
			Usuario usr = (Usuario) usuario;
			// try {
				// ISistema sistema = SistemaFactory.getInstancia().getISistema();
				// sistema.elegirCliente(usuario);
				// usr = sistema.getUsuarioActual();
				// usr = (Cliente) usuario;
			/*	
			} catch(Exception ex){
				response.sendError(404); // el usuario no es un cliente
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
						include(request, response);
				return;
			}
			*/
			
			// setea el usuario
			request.setAttribute("usuarioActual", usr);

			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
					forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
