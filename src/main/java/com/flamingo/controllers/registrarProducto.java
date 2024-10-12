package com.flamingo.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import com.flamingo.models.Categoria;
import com.flamingo.models.Proveedor;

@WebServlet ("/registrarProducto")
public class registrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public registrarProducto() {
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
		try {
			sis.elegirProveedor("elIsma");
					
		} catch(UsuarioNoExisteException e) {
			
		}
		request.setAttribute("usuarioActual", sis.getUsuarioActual());
		request.setAttribute("categorias", sis.getCategorias());
		Object usuario = request.getAttribute("usuarioActual");
		Object categorias = request.getAttribute("categorias");	
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			
			request.getRequestDispatcher("/WEB-INF/registrarProducto/registrarProducto.jsp").
					forward(request, response);
		} else {
			Usuario usr = (Usuario) usuario;
			request.setAttribute("usuarioActual", usr);
			
			HashMap<String, Categoria> ctg = (HashMap<String, Categoria>) categorias;
			request.setAttribute("categorias", ctg);
			

			request.getRequestDispatcher("/WEB-INF/registrarProducto/registrarProducto.jsp").
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
		ISistema sis = SistemaFactory.getInstancia().getISistema();
		// Configurar el encoding para manejar caracteres especiales (por si es necesario)
	    request.setCharacterEncoding("UTF-8");
	    sis.crearCasos();
	    
	    try {
			sis.elegirProveedor("elIsma");
					
		} catch(UsuarioNoExisteException e) {
			
		}
	 // Obtener los parámetros del formulario que enviaste
	    String nombre = request.getParameter("nombre");
	    String id = request.getParameter("id");
	    String precio = request.getParameter("precio");
	    String[] categorias = request.getParameterValues("categorias");
	    String[] especificaciones = request.getParameterValues("especificaciones");

	    // Imprimir en consola los datos obtenidos para testeo
	    System.out.println("Nombre del producto: " + nombre);
	    System.out.println("ID del producto: " + id);
	    System.out.println("Precio del producto: " + precio);

	    // Imprimir las categorías (si existen)
	    if (categorias != null) {
	        System.out.println("Categorías del producto:");
	        for (String categoria : categorias) {
	            System.out.println("- " + categoria);
	        }
	    } else {
	        System.out.println("No se seleccionaron categorías.");
	    }

	    // Imprimir especificaciones (si existen)
	    if (especificaciones != null) {
	        System.out.println("Especificaciones del producto:");
	        for (String especificacion : especificaciones) {
	            System.out.println("- " + especificacion);
	        }
	    } else {
	        System.out.println("No se especificaron especificaciones.");
	    }

	    
	    // Convertir precio a un número float
	    float precioFloat = 0f;
	    if (precio != null && !precio.isEmpty()) {
	        try {
	            precioFloat = Float.parseFloat(precio);
	        } catch (NumberFormatException e) {
	            // Manejar el error si el formato no es correcto
	            e.printStackTrace();
	        }
	    }
	    
	   
	    // Crear una instancia del nuevo producto (asumiendo que tienes una clase Producto)
	    Producto nuevoProducto = new Producto(null, null, null, 0, 0, null, null, null);
	    nuevoProducto.setNombreProducto(nombre);
	    nuevoProducto.setPrecio(precioFloat);  
	    
	    Proveedor proveedor = (Proveedor) sis.getUsuarioActual();
	    proveedor.agregarProducto(nuevoProducto);

	}

	

}
