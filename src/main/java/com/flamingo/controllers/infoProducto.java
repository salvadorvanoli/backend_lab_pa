package com.flamingo.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.flamingo.exceptions.CategoriaNoExisteException;
import com.flamingo.exceptions.ProductoNoExisteException;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.models.Cliente;
import com.flamingo.models.EstadoSesion;
import com.flamingo.models.ISistema;
import com.flamingo.models.Producto;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

@WebServlet ("/infoProducto")
public class infoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public infoProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ProductoNoExisteException, CategoriaNoExisteException {
		ISistema sis;
		
		HttpSession session = request.getSession();
		
		if (getServletContext().getAttribute("sistema") == null) {
		    System.out.println("CREO EL SISTEMA");
		    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		    sis.crearCasos();
		} else {
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		
		String numReferenciaStr = request.getParameter("productoSeleccionado");
		
		if(numReferenciaStr.isBlank() || numReferenciaStr.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/home/home.jsp").
			forward(request, response);
			return;
		}
		
		int numReferencia = Integer.parseInt(numReferenciaStr);
		Producto productoSeleccionado = null;
		
		for(Producto prod : sis.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				productoSeleccionado = prod;
				break;
			}
		}
		
		if(productoSeleccionado == null) {
			session.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/user/ERROR.jsp").
					forward(request, response);
		}

		request.setAttribute("productoActual", productoSeleccionado);
		Object usuario = session.getAttribute("usuarioActual");
		Object producto = request.getAttribute("productoActual");	
		
		if(usuario == null) {
			
			session.setAttribute("usuarioActual", null);
			
			request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp").
					forward(request, response);
		} else {
			Usuario usr = (Usuario) usuario;
			session.setAttribute("usuarioActual", usr);
			
			System.out.println(usr.toString());
			
			Producto prd = (Producto) producto;
			request.setAttribute("productoActual", prd);
			sis.setProductoActual(prd);
			

			request.getRequestDispatcher("/WEB-INF/products/infoProducto.jsp").
					forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				processRequest(request, response);
			} catch (CategoriaNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				processRequest(request, response);
			} catch (CategoriaNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
