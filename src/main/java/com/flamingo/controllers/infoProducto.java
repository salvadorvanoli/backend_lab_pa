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

	public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ProductoNoExisteException, CategoriaNoExisteException {
		ISistema sis = SistemaFactory.getInstancia().getISistema();
		sis.crearCasos();
		
		String productoSeleccionado = request.getParameter("productoSeleccionado");
		
		try {
			//sis.elegirProveedor("elIsma");
			sis.elegirCliente("Salva");
			sis.elegirCategoria("Intrumentos Electricos");
			sis.elegirProducto("Guitarra"); // Acá entre paréntesis tendría que ir "productoSeleccionado".
		} catch(UsuarioNoExisteException e) {
		
		}
		request.setAttribute("usuarioActual", sis.getUsuarioActual());
		request.setAttribute("productoActual", sis.getProductoActual());
		Object usuario = request.getAttribute("usuarioActual");
		Object producto = request.getAttribute("productoActual");	
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			
			request.getRequestDispatcher("/WEB-INF/products/infoProducto.jsp").
					forward(request, response);
		} else {
			Usuario usr = (Usuario) usuario;
			request.setAttribute("usuarioActual", usr);
			
			Producto prd = (Producto) producto;
			request.setAttribute("productoActual", prd);
			

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
