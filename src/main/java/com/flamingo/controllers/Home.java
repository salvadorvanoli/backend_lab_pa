package com.flamingo.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.models.EstadoSesion;

/**
 * Servlet implementation class Home
 */
@WebServlet ("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * inicializa la sesión si no estaba creada 
	 * @param request 
	 */
	public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("paginas_navegadas") == null) {
			session.setAttribute("paginas_navegadas", 0);
		}
		if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
		}
	}
	
	/**
	 * Devuelve el estado de la sesión
	 * @param request
	 * @return 
	 */
	public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		initSession(req);
		
		switch(getEstado(req)){
			case NO_LOGIN:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
						forward(req, resp);
				break;
			case LOGIN_INCORRECTO:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
						forward(req, resp);
				break;
				
				// hace que se ejecute el jsp sin cambiar la url
				//req.getRequestDispatcher("/WEB-INF/home/inicioErroneo.jsp").
				//		forward(req, resp);
				//break;
			case LOGIN_CORRECTO:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
						forward(req, resp);
				break;
				
				// manda una redirección a otra URL (cambia la URL)
				//resp.sendRedirect("/flamingo/perfil");
				//break;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
