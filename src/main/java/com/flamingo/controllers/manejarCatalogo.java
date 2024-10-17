package com.flamingo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.SistemaFactory;
import com.google.gson.Gson;

/**
 * Servlet implementation class Carrito
 */
@WebServlet ("/manejarcatalogo")
public class manejarCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manejarCatalogo() {
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
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ISistema sis;
		if (getServletContext().getAttribute("sistema") == null) {
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		
		try {
			
			String tipoGET = request.getHeader("tipo");
			
			Gson gson = new Gson();
			
			String result = "";
			
			if (tipoGET.equals("getCategorias")) {
				result = gson.toJson(sis.listarCategorias());
			} else if (tipoGET.equals("getProductos")) {
				
				result = gson.toJson(sis.listarAllProductos());
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			
		} catch (Exception e) {
			// Manejar Excepcion
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ISistema sis;
		if (getServletContext().getAttribute("sistema") == null) {
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		
		HttpSession session = request.getSession();
		Object usuario = session.getAttribute("usuarioActual");
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
					forward(request, response);
				
		} else {
			try {
				// Leer el cuerpo de la solicitud (donde está el JSON)
				String tipoPOST = request.getHeader("tipo");
				
				BufferedReader reader = request.getReader();
		        StringBuilder jsonBuilder = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            jsonBuilder.append(line);
		        }
		        
		        Gson gson = new Gson();
				
				if (tipoPOST.equals("eliminarItem")) {
					
			        int numReferencia = gson.fromJson(jsonBuilder.toString(), int.class);

			        sis.eliminarItemCarrito(numReferencia);
			        
				} else if (tipoPOST.equals("manejarCantidad")) {
					
					int[] elementos = gson.fromJson(jsonBuilder.toString(), int[].class);
			        sis.modificarCantidadItemCarrito(elementos[0], elementos[1]);
					
				} else if (tipoPOST.equals("realizarCompra")) {
					
					OrdenDeCompra orden = gson.fromJson(jsonBuilder.toString(), OrdenDeCompra.class);
					sis.realizarCompra(orden);
					
				}
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("{\"message\":\"Producto recibido correctamente\"}");
			} catch (Exception e) {
				// Manejar Excepcion
			}
		}
	}
	

}
