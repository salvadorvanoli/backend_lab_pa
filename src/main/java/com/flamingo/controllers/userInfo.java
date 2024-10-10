package com.flamingo.controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet ("/userInfo")
public class userInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		session.setAttribute("usuarioActual", "1");
		
		// Verifica si el usuarioActual es null
		if (session.getAttribute("usuarioActual") == null) {
			resp.sendRedirect(req.getContextPath() + "/iniciarSesion");
			return;
		}
		
		// Establece el tipo de contenido a JSON
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		

		// Crea el JSON de respuesta
		String jsonResponse = "{\r\n"
			+ "    \"nombre\": \"Salvador\",\r\n"
			+ "    \"apellido\": \"Vanoli\",\r\n"
			+ "    \"nickname\": \"salvaelpro777\",\r\n"
			+ "    \"tipo\": \"Cliente\",\r\n"
			+ "    \"email\": \"salva@salva.com\",\r\n"
			+ "    \"fecha\": \"2004-05-01\",\r\n"
			+ "    \"foto\": \"media/images/test.jpg\",\r\n"
			+ "    \"web\": \"salva.com\",\r\n"
			+ "    \"empresa\": \"salvaEnterprise\",\r\n"
			+ "    \"id\": \"0\",\r\n"
			+ "    \"ordenes\": [\r\n"
			+ "        {\r\n"
			+ "            \"id\": 0,\r\n"
			+ "            \"fecha\": \"2024-09-24\",\r\n"
			+ "            \"productos\": [\r\n"
			+ "                {\r\n"
			+ "                    \"nombre\": \"Zucaritas\",\r\n"
			+ "                    \"precio\": 300,\r\n"
			+ "                    \"descripcion\": \"Muy ricas, sisi muy muy ricas\",\r\n"
			+ "                    \"imagenes\": [\r\n"
			+ "                      \"media/images/test.jpg\",\r\n"
			+ "                      \"media/images/test.jpg\"\r\n"
			+ "                    ],\r\n"
			+ "                    \"id\": \"777\",\r\n"
			+ "                    \"cantidad\": 1\r\n"
			+ "                },\r\n"
			+ "                {\r\n"
			+ "                    \"nombre\": \"WATAFAK\",\r\n"
			+ "                    \"precio\": 200,\r\n"
			+ "                    \"descripcion\": \"sii\",\r\n"
			+ "                    \"imagenes\": [\r\n"
			+ "                      \"media/images/test.jpg\",\r\n"
			+ "                      \"media/images/test.jpg\"\r\n"
			+ "                    ],\r\n"
			+ "                    \"id\": \"778\",\r\n"
			+ "                    \"cantidad\": 5\r\n"
			+ "                }\r\n"
			+ "            ]\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 1,\r\n"
			+ "            \"fecha\": \"2024-09-27\",\r\n"
			+ "            \"productos\": [\r\n"
			+ "                {\r\n"
			+ "                    \"nombre\": \"Zucaritas\",\r\n"
			+ "                    \"precio\": 400,\r\n"
			+ "                    \"descripcion\": \"Muy ricas, sisi muy muy ricas\",\r\n"
			+ "                    \"imagenes\": [\r\n"
			+ "                      \"media/images/test.jpg\",\r\n"
			+ "                      \"media/images/test.jpg\"\r\n"
			+ "                    ],\r\n"
			+ "                    \"id\": \"777\",\r\n"
			+ "                    \"cantidad\": 6\r\n"
			+ "                },\r\n"
			+ "                {\r\n"
			+ "                    \"nombre\": \"SSSSSSSSSSSESx\",\r\n"
			+ "                    \"precio\": 200,\r\n"
			+ "                    \"descripcion\": \"sii\",\r\n"
			+ "                    \"imagenes\": [\r\n"
			+ "                      \"media/images/test.jpg\",\r\n"
			+ "                      \"media/images/test.jpg\"\r\n"
			+ "                    ],\r\n"
			+ "                    \"id\": \"778\",\r\n"
			+ "                    \"cantidad\": 8\r\n"
			+ "                }\r\n"
			+ "            ]\r\n"
			+ "        }\r\n"
			+ "    ],\r\n"
			+ "    \"productos\": [\r\n"
			+ "        {\r\n"
			+ "            \"nombre\": \"Zucaritas\",\r\n"
			+ "            \"estrellas\": 3,\r\n"
			+ "            \"precio\": 300,\r\n"
			+ "            \"descripcion\": \"Muy ricas, sisi muy muy ricas\",\r\n"
			+ "            \"id\": \"777\",\r\n"
			+ "            \"categorias\": [\r\n"
			+ "                {\r\n"
			+ "                    \"nombre\": \"Comida\",\r\n"
			+ "                    \"hijas\": [\r\n"
			+ "                        {\r\n"
			+ "                            \"nombre\": \"Dulce\",\r\n"
			+ "                            \"hijas\": [\r\n"
			+ "                                {\r\n"
			+ "                                    \"nombre\": \"Cereales\",\r\n"
			+ "                                    \"hijas\": []\r\n"
			+ "                                }\r\n"
			+ "                            ]\r\n"
			+ "                        }\r\n"
			+ "                    ]\r\n"
			+ "                }\r\n"
			+ "            ],\r\n"
			+ "            \"especificacion\": [\r\n"
			+ "                \"Cereal dulce de maíz\",\r\n"
			+ "                \"0 proteína 100% lípidos\",\r\n"
			+ "                \"Totalmente mortal para el cuerpo\"\r\n"
			+ "            ],\r\n"
			+ "            \"imagenes\": [\r\n"
			+ "                \"media/images/test.jpg\",\r\n"
			+ "                \"media/images/test.jpg\"\r\n"
			+ "            ]\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}";
		
		

		// Envía el JSON como respuesta
		out.print(jsonResponse);
		out.flush();
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
