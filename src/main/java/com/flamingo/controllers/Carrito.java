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
import com.flamingo.models.ISistema;
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
		ISistema sis;
		if (getServletContext().getAttribute("sistema") == null) {
			System.out.println("CREO EL SISTEMA");
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		try {
			sis.elegirCliente("Salva");
		} catch(UsuarioNoExisteException e) {
			// Manejar error
		}
		HttpSession session = request.getSession();
		// Object usuario = session.getAttribute("usuarioActual");
		request.setAttribute("usuarioActual", sis.getUsuarioActual());
		session.setAttribute("usuarioActual", sis.getUsuarioActual());
		Object usuario = session.getAttribute("usuarioActual");	
		
		if(usuario == null) {
			// session.setAttribute("usuarioActual", null);
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp"). // Se debería enviar a una pagina de error?
					forward(request, response);
				
		} else {
			Usuario usr = (Usuario) usuario;
			/*
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print(cartJson);
			out.flush();
			*/
			
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
