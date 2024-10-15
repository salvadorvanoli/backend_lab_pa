package com.flamingo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Categoria;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.DTProducto;
import com.flamingo.models.DTProductoDetallado;
import com.flamingo.models.DTProveedor;
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Producto;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
			System.out.println("CREO EL SISTEMA");
			getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
			sis = (ISistema) getServletContext().getAttribute("sistema");
			sis.crearCasos();
		} else {
			sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		
		HttpSession session = request.getSession();
		Object usuario = session.getAttribute("usuarioActual");

		System.out.println("NOOOOOO Entre a user = null");
		try {
			
			String tipoGET = request.getHeader("tipo");
			
			Gson gson = new Gson();
			
			String result = "";
			
			if (tipoGET.equals("getCategorias")) {
				result = gson.toJson(sis.listarCategorias());
				System.out.println(result);
			} else if (tipoGET.equals("getProductos")) {
				
				HashMap<Integer, DTProductoDetallado> lista = new HashMap<>();
				result = gson.toJson(sis.listarAllProductos(null, lista));
				System.out.println(result);
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
			System.out.println("Entre a user = null");
			// session.setAttribute("usuarioActual", null);
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp"). // Se debería enviar a una pagina de error?
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
			        
			        System.out.println(numReferencia);
			        sis.eliminarItemCarrito(numReferencia);
			        
				} else if (tipoPOST.equals("manejarCantidad")) {
					
					int[] elementos = gson.fromJson(jsonBuilder.toString(), int[].class);
			        sis.modificarCantidadItemCarrito(elementos[0], elementos[1]);
					
				} else if (tipoPOST.equals("realizarCompra")) {
					
					OrdenDeCompra orden = gson.fromJson(jsonBuilder.toString(), OrdenDeCompra.class);
					sis.realizarCompra(orden);
					
				}
				// Puedes enviar una respuesta si lo deseas
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("{\"message\":\"Producto recibido correctamente\"}");
			} catch (Exception e) {
				// Manejar Excepcion
			}
		}
	}
	
	
	
    private String ListaCategoriasToJson(HashMap<String, Categoria> categorias) {
        StringBuilder jsonBuilder = new StringBuilder("[");

        int size = 0;
        
        for(Categoria cat : categorias.values()) {
        	jsonBuilder.append(categoriaToJson(cat));
        	
        	if(size < categorias.size() - 1) {
        		jsonBuilder.append(",");
        	}
        	
        	size++;
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String categoriaToJson(Categoria cat) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format("{\"nombreCat\":\"%s\",\"tieneProductos\":%b,\"hijos\":[",
                cat.getNombreCat(), cat.isTieneProductos()));

        // Serializar hijos
        StringBuilder hijosJsonBuilder = new StringBuilder();
        HashMap<String, Categoria> hijosHash = cat.getHijos();
        if (hijosHash != null && !hijosHash.isEmpty()) {
            int j = 0;
            for (Categoria subcat : hijosHash.values()) {
                if (j > 0) hijosJsonBuilder.append(",");
                hijosJsonBuilder.append(categoriaToJson(subcat)); // Llamada recursiva
                j++;
            }
        }
        jsonBuilder.append(hijosJsonBuilder).append("]}");
        return jsonBuilder.toString();
    }
    
    private String ListaProductosToJson(List<Producto> productos) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (int i = 0; i < productos.size(); i++) {
            Producto prod = productos.get(i);
            jsonBuilder.append(productoToJson(prod)); // Convertir cada producto a JSON
            
            if (i < productos.size() - 1) {
                jsonBuilder.append(","); // Agregar coma entre productos
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String productoToJson(Producto prod) {
        String precioFormateado = String.format(Locale.US, "%.2f", prod.getPrecio()); 
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format(
            "{\"nombreProducto\":\"%s\",\"descripcion\":\"%s\",\"especificacion\":\"%s\"," +
            "\"numReferencia\":%d,\"precio\":%s,\"imagenes\":[",
            prod.getNombreProducto(), prod.getDescripcion(), prod.getEspecificacion(),
            prod.getNumReferencia(), precioFormateado)); // Usar el precio formateado

        // Serializar imágenes
        StringBuilder imagenesJsonBuilder = new StringBuilder();
        List<String> imagenes = prod.getImagenes();
        for (int j = 0; j < imagenes.size(); j++) {
            if (j > 0) imagenesJsonBuilder.append(",");
            imagenesJsonBuilder.append("\"").append(imagenes.get(j)).append("\"");
        }
        jsonBuilder.append(imagenesJsonBuilder)
            .append("],\"categorias\":[],\"proveedor\":\"")
            .append(prod.getProveedor() != null ? prod.getProveedor().toString() : "")
            .append("\",\"estrellas\":").append(prod.getEstrellas())
            .append(",\"nombreTienda\":\"").append(prod.getNombreTienda()).append("\"}");
        
        return jsonBuilder.toString();
    }
	

}
