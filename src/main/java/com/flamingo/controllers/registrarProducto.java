package com.flamingo.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import com.flamingo.exceptions.CategoriaNoPuedeTenerProductosException;
import com.flamingo.exceptions.ProductoNoExisteException;
import com.flamingo.exceptions.ProductoRepetidoException;
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
	

		// Obtener el usuario actual desde la sesión
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuarioActual");

		// Verificar si el usuario existe antes de imprimir
		if (usuarioActual != null) {
		    // Suponiendo que tienes un método toString() en la clase Usuario o puedes acceder a sus atributos
		   
		    // O puedes imprimir atributos específicos como el nombre o el ID
		    System.out.println("Nombre del usuario actual: " + usuarioActual.getNombre()); 
 
		} else {
		    System.out.println("No hay un usuario actual en la sesión.");
		}

		request.setAttribute("categorias", sis.getCategorias());
		Object usuario = request.getAttribute("usuarioActual");
		Object categorias = request.getAttribute("categorias");	
		
		if(usuario == null) {
			
			request.getRequestDispatcher("/WEB-INF/registrarProducto/registrarProducto.jsp").
					forward(request, response);
		} else {
			Usuario usr = (Usuario) usuario;
			session.setAttribute("usuarioActual", usr);
			
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
		
		HttpSession session = request.getSession();
		
		ISistema sis;
		if (getServletContext().getAttribute("sistema") == null) {
		    System.out.println("CREO EL SISTEMA");
		    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		    sis.crearCasos();
		} else {
		    sis = (ISistema) getServletContext().getAttribute("sistema");
		}
		
		// Configurar el encoding para manejar caracteres especiales (por si es necesario)
	    request.setCharacterEncoding("UTF-8");
	    
	  
	 // Obtener los parámetros del formulario que enviaste
	    String nombre = request.getParameter("nombre");
	    String id = request.getParameter("id");
	    String precio = request.getParameter("precio");
	    String numeroRef = request.getParameter("numero");
	    String descripcion = request.getParameter("descripcion");
	    String imagenes = request.getParameter("imagenes");
	    
	 // Declarar la lista de imágenes fuera del if
	    List<String> listaImagenes = new ArrayList<>();

	 // Obtener el parámetro de imágenes
	    String img = request.getParameter("imagenes");

	    // Verificar si el parámetro no es nulo o vacío
	    if (img != null && !img.isEmpty()) {
	        // Eliminar los corchetes, comillas y espacios innecesarios alrededor
	        img = img.replace("[", "").replace("]", "").replace("\"", "").trim();

	        // Dividir la cadena por el prefijo específico de las imágenes en base64 (asegura que sea correcto)
	        String[] imagenesArray = img.split("(?=data:image/)");

	        // Crear la lista de imágenes
	        listaImagenes = new ArrayList<>(Arrays.asList(imagenesArray));

	        // Limpiar posibles comas al final de cada imagen
	        for (int i = 0; i < listaImagenes.size(); i++) {
	            listaImagenes.set(i, listaImagenes.get(i).trim()); // Elimina espacios en blanco
	            // Si hay comas al final de la imagen, las eliminamos
	            if (listaImagenes.get(i).endsWith(",")) {
	                listaImagenes.set(i, listaImagenes.get(i).substring(0, listaImagenes.get(i).length() - 1));
	            }
	        }

	        // Imprimir cada imagen en una nueva línea
	        for (String imagen : listaImagenes) {
	            System.out.println(imagen.trim()); // Imprime la imagen limpia
	        }
	    } else {
	        System.out.println("No se recibieron imágenes.");
	    }

	    // Verificar y mostrar las imágenes guardadas en la lista
	    for (int i = 0; i < listaImagenes.size(); i++) {
	        System.out.println("Imagen en lista " + (i + 1) + ": " + listaImagenes.get(i).trim()); // Imprime cada imagen
	    }

	 // Declarar la lista de especificaciones fuera del if
	    List<String> listaEspecificaciones = new ArrayList<>();

	    // Obtener el parámetro de especificaciones
	    String especificacionesString = request.getParameter("especificaciones"); // Suponiendo que obtienes la cadena como un solo parámetro

	    // Verificar si el parámetro no es nulo o vacío
	    if (especificacionesString != null && !especificacionesString.isEmpty()) {
	        // Eliminar los corchetes y las comillas
	        especificacionesString = especificacionesString.replace("[", "").replace("]", "").replace("\"", "");

	        // Dividir la cadena por comas para obtener cada especificación
	        String[] especificacionesArray = especificacionesString.split(",");

	        // Crear la lista de especificaciones
	        listaEspecificaciones = new ArrayList<>(Arrays.asList(especificacionesArray));

	        // Imprimir cada especificación en una nueva línea
	        for (String especificacion : listaEspecificaciones) {
	            System.out.println(especificacion.trim()); // Imprime la especificación
	        }
	    } else {
	        System.out.println("No se recibieron especificaciones.");
	    }

	     
		 // Obtener el parámetro de categorías
		    String categoriasString = request.getParameter("categorias"); 
		    
		    
		        // Eliminar los corchetes y las comillas
		        categoriasString = categoriasString.replace("[", "").replace("]", "").replace("\"", "");

		        // Dividir la cadena por comas para obtener cada categoría
		        String[] categoriasArray = categoriasString.split(",");

		        // Crear la lista de categorías
		        List<String> listaCategorias = new ArrayList<>(Arrays.asList(categoriasArray));

		        // Imprimir cada categoría en una nueva línea
		        for (String categoria : listaCategorias) {
		            System.out.println(categoria.trim()); // Imprime la categoría
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
		    
		
		    // Inicializar una variable para el valor convertido
		    int numeroRefInt = 0; // o double, dependiendo del tipo que necesites

		    // Verificar si el parámetro no es null y no está vacío
		    if (numeroRef != null && !numeroRef.isEmpty()) {
		        try {
		            // Convertir el parámetro a un número entero
		            numeroRefInt = Integer.parseInt(numeroRef);
		        } catch (NumberFormatException e) {
		            // Manejar el error si el formato no es correcto
		            e.printStackTrace();
		            
		        }
		    }
		    
		    
		    
		    Producto nuevoProducto = new Producto(null, null,null, 0, 0, null, null, null, null);

		    //////////////////////Link categorias/////////////////////////////////////////
			 // Crear vínculo entre categorías y producto
			    List<Categoria> categoriasGuardadas = new ArrayList<>();

			    // Obtener el HashMap de categorías desde el sistema
			    HashMap<String, Categoria> todasLasCategorias = sis.getCategorias();

			   
			    if (listaCategorias != null) {
			        for (String nombreCategoria : listaCategorias) {
			            // Buscar la categoría utilizando el método del sistema
			            Categoria categoria = sis.buscarCategoriaRecursivamente(nombreCategoria, todasLasCategorias);
			            
			            // Verificar si se encontró la categoría
			            if (categoria != null) {
			                // Agregar la categoría a la lista
			                categoriasGuardadas.add(categoria);
			                categoria.agregarProducto(sis.getProductoActual());
			                // Imprimir información para verificar
			                System.out.println("Producto '" + nuevoProducto.getNombreProducto() + "' agregado a la categoría: " + nombreCategoria);
			            } else {
			                System.out.println("Categoría no encontrada: " + nombreCategoria);
			            }
			        }
			    } else {
			        System.out.println("No se seleccionaron categorías.");
			    }
		

	/////////////////////////////////////////////////

		    // Crear una instancia del nuevo producto 
		   
		    nuevoProducto.setNombreProducto(nombre);
		    nuevoProducto.setPrecio(precioFloat);  
		    nuevoProducto.setNumReferencia(numeroRefInt);
		    nuevoProducto.setDescripcion(descripcion);
		    nuevoProducto.setCategorias(categoriasGuardadas);
		    nuevoProducto.setEspecificacion(listaEspecificaciones);

	    
		    List<String> op = new ArrayList<>();
		 // Suponiendo que ya tienes la lista de imágenes llena
		    if (!listaImagenes.isEmpty()) {
		        System.out.println("Lista de imágenes:");
		        for (String imagen : listaImagenes) {
		            System.out.println(imagen.trim()); // Imprime cada imagen
		        }
		    } else {
		        System.out.println("No hay imágenes para mostrar.");
		    }

		 // Agregar los strings "a" y "b" a la lista, imagenes
		 op.add("p");
		 op.add("q");
		    
		    try {
				sis.registrarProducto(nuevoProducto.getNombreProducto(), nuevoProducto.getNumReferencia(), nuevoProducto.getDescripcion(), nuevoProducto.getEspecificacion(), nuevoProducto.getPrecio(), nuevoProducto.getCategorias(),  listaImagenes, ((Proveedor) sis.getUsuarioActual()).getnomCompania());         
			} catch (ProductoRepetidoException | CategoriaNoPuedeTenerProductosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		 // Suponiendo que `categoriasguardadas` es una lista de objetos de tipo Categoria
		    for (Categoria categoria : categoriasGuardadas) {
		        // Agregar el producto actual a la categoría utilizando el método agregarProducto
		        categoria.agregarProducto(sis.getProductoActual());

		        // Verificación opcional
		        System.out.println("Producto agregado a la categoría: " + categoria.getNombreCat());
		    }

		    
	 // Obtener el HashMap de categorías desde el sistema
	    HashMap<String, Categoria> categoriasMap = sis.getCategorias();

	    // Imprimir los productos de cada categoría
	    System.out.println("Productos en cada categoría:");
	    for (String categoriaNombre : categoriasMap.keySet()) {
	        Categoria categoriaActual = categoriasMap.get(categoriaNombre);
	        List<Producto> productosEnCategoria = categoriaActual.getProductos(); // Asegúrate de que este método exista en tu clase Categoria

	        System.out.println("Categoría: " + categoriaNombre);
	        if (productosEnCategoria != null && !productosEnCategoria.isEmpty()) {
	            for (Producto producto : productosEnCategoria) {
	            	// Crear un StringBuilder para construir la cadena de especificaciones
	            	StringBuilder especificacionesBuilder = new StringBuilder();

	            	// Agregar cada especificación a la cadena, separadas por ": "
	            	for (int i = 0; i < listaEspecificaciones.size(); i++) {
	            	    especificacionesBuilder.append("especificacion").append(i + 1).append(": ").append(listaEspecificaciones.get(i).trim());
	            	    if (i < listaEspecificaciones.size() - 1) {
	            	        especificacionesBuilder.append(" - "); // Separador entre especificaciones
	            	    }
	            	}

	            	// Imprimir el producto con las especificaciones
	            	System.out.println("- Producto: " + producto.getNombreProducto() + 
	            	                   " (Número de referencia: " + producto.getNumReferencia() + 
	            	                   ", Descripción: " + producto.getDescripcion() + 
	            	                   ", Precio: $" + producto.getPrecio() + 
	            	                   ", " + especificacionesBuilder.toString() + ")");
	            }
	        } else {
	            System.out.println("- No hay productos en esta categoría.");
	        }
	    }
	    
	    session.setAttribute("usuarioActual", sis.getUsuarioActual());

	 // Imprimir el valor de "usuarioActual" para depuración
	 System.out.println("Usuario actual en la sesiónAAA: " + session.getAttribute("usuarioActual"));

	
	 request.getRequestDispatcher("infoUsuario").forward(request, response); // No pierdes el request ni los atributos

	}

	

}
