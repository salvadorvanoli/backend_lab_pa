package com.flamingo.models;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;

// Importamos las excepciones necesarias

import com.flamingo.exceptions.*;

public class Sistema extends ISistema {
	
	private List<Usuario> usuarios;
	private Usuario usuarioActual;
	
	private HashMap<Integer, OrdenDeCompra> ordenes;
	private OrdenDeCompra ordenActual;
	
	private HashMap<String, Categoria> categorias;
	private Categoria categoriaActual;
	
	private List<Producto> productos;
	private Producto productoActual;
	
	private List<Cantidad> listaOrden;
	// private List<Producto> listaOrden;
	
	public Sistema() {
		this.usuarios = new ArrayList<>();
		this.ordenes = new HashMap<Integer, OrdenDeCompra>();
		this.categorias = new HashMap<String, Categoria>();
		this.usuarioActual = null;
		this.ordenActual = null;
		this.categoriaActual = null;
		this.productoActual = null;
		this.listaOrden = new ArrayList<>(); 
		this.productos = new ArrayList<>();
	}
	
	@Override
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	

	@Override
	public HashMap<Integer, OrdenDeCompra> getOrdenes(){
		return this.ordenes;
	}
	
	@Override
	public HashMap<String, Categoria> getCategorias(){
		return this.categorias;
	}
	
	@Override
	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}
	
	@Override
	public OrdenDeCompra getOrdenDeCompraActual() {
		return this.ordenActual;
	}
	
	@Override
	public Categoria getCategoriaActual() {
		return this.categoriaActual;
	}
	
	@Override
	public Producto getProductoActual() {
		return this.productoActual;
	}
	
	@Override
	public DTFecha getFechaActual() {
		// Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Extraer el día, mes y año
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int anio = fechaActual.getYear();
        
        return new DTFecha(dia, mes, anio);
	}
	
	@Override // NO ES NECESARIO QUE SEA BOOL
	public boolean altaUsuarioCliente(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String imagen,  String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException {
		// Lista para almacenar los errores
	    List<String> errores = new ArrayList<>();

	    // Validar nombre
	    if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
	        errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar apellido
	    if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3 || !validarNombreSinNumeros(apellido)) {
	        errores.add("El apellido debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar contraseñas
	    if (contrasenia1 == null || contrasenia1.length() < 8) {
	        errores.add("La contraseña debe tener al menos 8 caracteres.");
	    } else if (!contrasenia1.equals(contrasenia2)) {
	        errores.add("Las contraseñas no coinciden.");
	    }

	    // Validar fecha de nacimiento
	    if (fechaNac == null) {
	        errores.add("Se debe ingresar una fecha de nacimiento válida.");
	    }


	    // Si hay errores tirar excepción
	    if (!errores.isEmpty()) {
	        throw new IllegalArgumentException("Errores de validación: " + String.join(", ", errores));
	    }
		
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		
		if (!contrasenia1.equals(contrasenia2)) {
			throw new ContraseniaIncorrectaException("Las contraseñas no coinciden.");
		}
		
		Cliente nuevo = new Cliente(nickname, nombre, apellido, email, fechaNac, imagen, contrasenia1);
		this.usuarios.add(nuevo);
		return true;
	}
	
	public boolean registro(String nickname, String email) throws UsuarioRepetidoException {
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		return true;
		}
	
	@Override // NO ES NECESARIO QUE SEA BOOL
	public boolean altaUsuarioProveedor(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String nomCompania, String linkWeb, String imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException {
		
		// Lista para almacenar los errores
	    List<String> errores = new ArrayList<>();

	    // Validar nombre
	    if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
	        errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar apellido
	    if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3 || !validarNombreSinNumeros(apellido)) {
	        errores.add("El apellido debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar contraseñas
	    if (contrasenia1 == null || contrasenia1.length() < 8) {
	        errores.add("La contraseña debe tener al menos 8 caracteres.");
	    } else if (!contrasenia1.equals(contrasenia2)) {
	        errores.add("Las contraseñas no coinciden.");
	    }

	    // Validar fecha de nacimiento
	    if (fechaNac == null) {
	        errores.add("Se debe ingresar una fecha de nacimiento válida.");
	    }

	    // Validar URL del sitio web si es proporcionada
	    if (linkWeb != null && !linkWeb.trim().isEmpty() && !validarUrl(linkWeb)) {
	        errores.add("La URL del sitio web no es válida.");
	    }

	    // Validar nombre de la compañía
	    if (nomCompania == null || nomCompania.trim().isEmpty()) {
	        errores.add("La compañía es obligatoria.");
	    }

	    // Si hay errores tirar excepción
	    if (!errores.isEmpty()) {
	        throw new IllegalArgumentException("Errores de validación: " + String.join(", ", errores));
	    }
		
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		
		if (!contrasenia1.equals(contrasenia2)) {
			throw new ContraseniaIncorrectaException("Las contraseñas no coinciden.");
		}
		
		Proveedor nuevo = new Proveedor(nickname, nombre, apellido, email, fechaNac, imagen, nomCompania, linkWeb, contrasenia1);
		this.usuarios.add(nuevo);
		return true;
	}
	
	public boolean validarNombreSinNumeros(String nombre) {
	    return !nombre.matches(".*\\d.*");
	}

	// Método para validar la URL
	public boolean validarUrl(String url) {
	    String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(:[0-9]+)?(/.*)?$";
	    return url.matches(regex);
	}
	
	
	@Override
	public boolean registrarProducto(String titulo, int numReferencia, String descrip, List<String> especificaciones, float precio, List<Categoria> categorias, List<String> imagenes, String nombreTienda) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException {
		if (this.usuarioActual == null || ! (this.usuarioActual instanceof Proveedor)) {
			throw new NullPointerException("No se ha elegido un proveedor previamente.");
		}

		try {
			this.existeProducto(titulo, numReferencia, false);
		} catch (ProductoRepetidoException e) {
			throw new ProductoRepetidoException(e.getMessage());
		}
		
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		Proveedor proveedor = (Proveedor) this.usuarioActual;
		Producto prod = new Producto(titulo, descrip, especificaciones, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda); // Esto esta re mal
		proveedor.agregarProducto(prod);
		this.productoActual = prod;
		this.agregarProductoACategorias(categorias);
		this.productos.add(prod);
		return true;
	}
	
	
	@Override // Lo podría leer directamente
	public DTProductoDetallado verInformacionProducto() {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		return this.productoActual.getDTProductoDetallado();
	}

	@Override
	public List<DTCategoria> listarCategorias(){
		List<DTCategoria> lista = new ArrayList<>();
		for (Categoria cat : this.categorias.values()) {
			DTCategoria dt = cat.getDTCategoria();
			lista.add(dt);
		}
		return lista;
	}
	

	@Override
	public boolean elegirCategoria(String nombreCat) throws CategoriaNoExisteException {
	    Categoria cat = buscarCategoriaRecursivamente(nombreCat, this.categorias);
	    if (cat == null) {
	        throw new CategoriaNoExisteException("La categoría de nombre " + '"' + nombreCat + '"' + " no existe.");
	    }
	    this.categoriaActual = cat;
	    return true;
	}

	@Override
	public Categoria buscarCategoriaRecursivamente(String nombreCat, HashMap<String, Categoria> categorias) {
	    for (Map.Entry<String, Categoria> entry : categorias.entrySet()) {
	        Categoria categoria = entry.getValue();
	        if (categoria.getNombreCat().equals(nombreCat)) {
	            return categoria;
	        }
	        Categoria catEncontrada = buscarCategoriaRecursivamente(nombreCat, categoria.getHijos());
	        if (catEncontrada != null) {
	            return catEncontrada;
	        }
	    }
	    return null;
	}
	
	@Override
	public boolean existeCategoria(String nombreCategoria) {
        for (Categoria categoria : getCategorias().values()) {
            if (categoria.getNombreCat().equals(nombreCategoria) || existeCategoriaRecursivamente(categoria, nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeCategoriaRecursivamente(Categoria categoria, String nombreCategoria) {
        for (Categoria hijo : categoria.getHijos().values()) {
            if (hijo.getNombreCat().equals(nombreCategoria) || existeCategoriaRecursivamente(hijo, nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public List<DTProducto> listarProductos(){
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		List<DTProducto> lista = this.categoriaActual.listarProductos();
		return lista;
	}
	

	// No se si incluir el numero de referencia (para buscar el producto)
	@Override
	public boolean elegirProducto(String nombreProd) throws ProductoNoExisteException {
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		Producto prod = this.categoriaActual.seleccionarProducto(nombreProd);
		if (prod == null) {
			throw new ProductoNoExisteException("El producto de nombre " + '"' + nombreProd + '"' + " no existe.");
		}
		this.productoActual = prod;
		return true;
	}

	
	@Override
	public Categoria altaCategoria(String nombre, boolean tieneProductos, Categoria padre) throws CategoriaRepetidaException{
        if (this.categorias.containsKey(nombre)) {
            throw new CategoriaRepetidaException("Ya existe una categoría con el nombre " + '"' + nombre + '"' + '.');
        }
		Categoria cat = new Categoria(nombre, tieneProductos, padre);
		if (padre == null) {
			this.categorias.put(nombre, cat);
		} else {
			padre.agregarHijo(nombre, cat);
		}
		return cat;
	}

	@Override
	public List<DTOrdenDeCompra> listarOrdenesDeCompra(){
		List<DTOrdenDeCompra> lista = new ArrayList<>();
		if (this.usuarioActual != null && (this.usuarioActual instanceof Cliente)) {
			Cliente cli = (Cliente) this.usuarioActual;
			for (OrdenDeCompra ord : cli.getOrdenesDeCompras()) {
				DTOrdenDeCompra dt = ord.getDTOrden();
				lista.add(dt);
			}
		} else {
			for (OrdenDeCompra ord : this.ordenes.values()) {
				DTOrdenDeCompra dt = ord.getDTOrden(); // Capaz la función no se llama así
				lista.add(dt);
			}
		}
		return lista;
	}
	

	@Override
	public boolean elegirOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException {
        OrdenDeCompra ord = this.ordenes.get(numero);
        if (ord == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra número " + '"' + numero + '"' + " no existe.");
        }
        this.ordenActual = ord;
        return true; // No es necesario que sea bool
	}
	
	
	// FUNCION VER INFORMACION ORDEN DE COMPRA
	
	
	@Override
	public DTOrdenDeCompraDetallada verInformacionOrdenDeCompra() {
		if (this.ordenActual == null) {
			throw new NullPointerException("No se ha elegido una orden de compra previamente.");
		}
		return this.ordenActual.getDTOrdenDetallada();
	}
	
	
	// FUNCION GENERAR CODIGO ORDEN
	
	@Override
	public OrdenDeCompra agregarOrden(List<Cantidad> cantidad) {
		if (this.usuarioActual instanceof Cliente) {
	        Cliente clienteActual = (Cliente) this.usuarioActual;
	        
	        int codigoOrden = this.generarCodigoOrden();
	        OrdenDeCompra nueva = new OrdenDeCompra(codigoOrden, this.getFechaActual(), clienteActual, null);
	        
	        for (Cantidad cant : cantidad) {
	        	nueva.agregarProducto(cant.getProducto().getDTProducto(), cant.getCantidad());
	        }
	        
	        this.ordenes.put(codigoOrden, nueva);
	        
	        List<OrdenDeCompra> ordenes = clienteActual.getOrdenesDeCompras();
	        ordenes.add(nueva);
	        clienteActual.setOrdenesDeCompras(ordenes);
	        System.out.println(nueva);
	        return nueva;
	    } else {
	        throw new IllegalArgumentException("El usuario actual no es un cliente.");
	    }
	}
	
	@Override
	public int generarCodigoOrden() {
		if (this.ordenes.isEmpty()) {
			return 1;
		}
		int numero = 0;
		for (int key : this.ordenes.keySet()) {
			if (key >= numero) {
				numero = key+1;
			}
		}
		return numero;
	}
	
	@Override
	public int generarIdComentario() {
		if (this.productos.isEmpty()) {
			return 0;
		}
		int numero = 0;
		for (Producto prod : this.productos) {
			if (prod.getIdComentarioMayor() >= numero) {
				numero = prod.getIdComentarioMayor() + 1;
			}
		}
		return numero;
	}
	
	// FUNCION DAR ALTA ORDEN DE COMPRA
	
	
	/* ALTERNATIVA A LA FUNCION DAR ALTA ORDEN */
	/*
	@Override
	public DTOrdenDeCompraDetallada darAltaOrden() throws UsuarioNoExisteException {
		if (this.usuarioActual == null) {
			throw new NullPointerException("No se ha elegido un cliente previamente.");
		}
		if (this.usuarioActual instanceof Proveedor) {
			throw new UsuarioNoExisteException("El usuario de nickname " + '"' + this.usuarioActual.getNickname() + '"' + " existe, pero no es un cliente.");
		}
		if (this.listaOrden.isEmpty()) {
			throw new IllegalStateException("No se han elegido productos previamente.");
		}
		int numero = this.generarCodigoOrden();
		DTFecha fecha = this.getFechaActual();
		Cliente cli = (Cliente) this.usuarioActual;
		OrdenDeCompra ord = new OrdenDeCompra(numero, fecha, cli, this.listaOrden);
		this.ordenes.put(numero, ord);
		this.listaOrden.clear();
		return ord.getDTOrdenDetallada();
	}
	*/
	
	
	
	// FUNCION CANCELAR ORDEN DE COMPRA
	

	@Override
	public void cancelarOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException {
		OrdenDeCompra ord = this.ordenes.get(numero);
        if (ord == null){
            throw new OrdenDeCompraNoExisteException("La orden de compra número " + '"' + numero + '"' + " no existe.");
        }
        this.ordenes.remove(numero);
        ord.desvincularCliente();
        ord.setCantidad(null);
        return;
	}
	
	
	// FUNCION AGREGAR PRODUCTO
	
	
	
	@Override
	public boolean agregarProducto(String nombreProducto, int cantidad) throws ProductoNoExisteException {
		if (cantidad < 1) {
			throw new IllegalArgumentException("La cantidad de un producto no puede ser menor a 1.");
		}
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		Producto prod = this.categoriaActual.seleccionarProducto(nombreProducto);
		if (prod == null) {
			throw new ProductoNoExisteException("El producto de nombre " + '"' + nombreProducto + '"' + " no existe.");
		}
		Cantidad cant = new Cantidad(prod, cantidad);
		this.listaOrden.add(cant);
		return true;
	}
	

	@Override
	public List<DTCliente> listarClientes(){

		List<DTCliente> lista = new ArrayList<>();
		for (Usuario user : this.usuarios) {
			if (user instanceof Cliente) {
				Cliente cl = (Cliente) user;
				DTCliente dt = cl.getDTCliente();
				lista.add(dt);
			}
		}
		return lista;
	}
	

	@Override
	public boolean elegirCliente(String nickname) throws UsuarioNoExisteException {
		for (Usuario user : this.usuarios) {
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				if (user instanceof Cliente) {
					Cliente cli = (Cliente) user;
					this.usuarioActual = cli;
					return true; // No es necesario que sea booleano
				}
				throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " existe, pero no es un cliente.");
			}
		}
		throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " no existe.");
	}
	
	@Override
	public DTClienteDetallado verInformacionCliente() {
		if (this.usuarioActual == null || ! (this.usuarioActual instanceof Cliente)) {
			throw new NullPointerException("No se ha elegido un cliente previamente.");
		}
		Cliente cli = (Cliente) this.usuarioActual;
		return cli.getDTClienteDetallado();
	}
	
	//@Override
	private void quitarProductoDeCategoriasSistema(Categoria cat) {
		for(Categoria hijo : cat.getHijos().values()) {
			hijo.getProductos().remove(this.productoActual);
			quitarProductoDeCategoriasSistema(hijo);
		}
	}

	@Override
	public void quitarProductoDeCategorias(boolean seAgreganCategorias) {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		if (seAgreganCategorias) {
			for(Categoria cat : this.categorias.values()) {
				cat.getProductos().remove(this.productoActual);
				quitarProductoDeCategoriasSistema(cat);
			}
		}
	}
	
	// Funciones que no estaban
	@Override
	public void agregarProductoACategorias(List<Categoria> categorias) throws CategoriaNoPuedeTenerProductosException {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		
		if (categorias != null && ! (categorias.isEmpty())) {
			for (Categoria cat : categorias) {
				cat.agregarProducto(this.productoActual);
			}
		}
	}
	
	public void agregarCategoriasAProducto(List<Categoria> categorias) throws CategoriaNoPuedeTenerProductosException {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		if (categorias != null && ! (categorias.isEmpty())) {
			this.productoActual.setCategorias(categorias);
		}
	}
	
	@Override
	public void existeProducto(String nombreProd, int numReferencia, boolean caso) throws ProductoRepetidoException {
		if (caso && this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		for (Categoria categoria : this.categorias.values()) {
			try {
				buscarProductoEnCategoria(categoria, nombreProd, numReferencia, caso);
			} catch (ProductoRepetidoException e) {
				throw new ProductoRepetidoException(e.getMessage());
			}
	    }
	}

	//@Override
	private void buscarProductoEnCategoria(Categoria categoria, String nombreProd, int numReferencia, boolean caso) throws ProductoRepetidoException { // Hay que probar esto
	    for (Producto producto : categoria.getProductos()) {
	        if (producto.getNombreProducto().equalsIgnoreCase(nombreProd)) {
	        	if (caso && producto != this.productoActual) {
	        		throw new ProductoRepetidoException("Ya existe un producto con ese nombre en el sistema.");
	        	}
	        }
	        if (producto.getNumReferencia() == numReferencia){
	        	if (caso && producto != this.productoActual) {
	        		throw new ProductoRepetidoException("Ya existe un producto con ese número de referencia en el sistema.");
	        	}
	        }
	    }

	    for (Categoria subcategoria : categoria.getHijos().values()) {
	    	try {
				buscarProductoEnCategoria(subcategoria, nombreProd, numReferencia, caso);
			} catch (ProductoRepetidoException e) {
				throw new ProductoRepetidoException(e.getMessage());
			}
	    }
	}
	
	@Override
	public void modificarDatosProducto(String nombreProd, int numReferencia, String descripcion, float precio, List<String> especificacion) throws ProductoRepetidoException {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio elegido no puede ser menor o igual a 0.");
		}
		try {
			existeProducto(nombreProd, numReferencia, true);
		} catch (ProductoRepetidoException e) {
			throw new ProductoRepetidoException(e.getMessage());
		}
		this.productoActual.setNombreProducto(nombreProd);
		this.productoActual.setNumReferencia(numReferencia);
		this.productoActual.setDescripcion(descripcion);
		this.productoActual.setEspecificacion(especificacion);
		this.productoActual.setPrecio(precio);
	}
	
	// ESTA RECONTRA MAL
	@Override
	public void modificarImagenesProducto(List<String> imagenes) {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		if (imagenes != null && ! (imagenes.isEmpty())) {
			this.productoActual.setImagenes(imagenes);
		}
	}
	
	public List<DTProveedor> listarProveedores(){
		List<DTProveedor> lista = new ArrayList<>();
		for (Usuario user : this.usuarios) {
			if (user instanceof Proveedor) {
				Proveedor pr = (Proveedor) user;
				DTProveedor dt = pr.getDTProveedor();
				lista.add(dt);
			}
		}
		return lista;
	}
	
	public void elegirProveedor(String nickname) throws UsuarioNoExisteException {
		for (Usuario user : this.usuarios) {
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				if (user instanceof Proveedor) {
					Proveedor pr = (Proveedor) user;
					this.usuarioActual = pr;
					return;
					// return true; // No es necesario que sea booleano
				}
				throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " existe, pero no es un proveedor.");
			}
		}
		throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " no existe.");
	}
	
	public DTProveedorDetallado verInformacionProveedor() {
		if (this.usuarioActual == null || ! (this.usuarioActual instanceof Proveedor)) {
			throw new NullPointerException("No se ha elegido un proveedor previamente.");
		}
		Proveedor pr = (Proveedor) this.usuarioActual;
		return pr.getDTProveedorDetallado();
	}
	
	public void setTodoNull() {
		this.ordenActual = null;
		this.categoriaActual = null;
		this.productoActual = null;
		this.usuarioActual = null;
		this.listaOrden.clear();
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public void crearCasos() {
		// TODO Auto-generated method stub
		DTFecha fecha1 = new DTFecha(2, 4, 2024);
        DTFecha fecha2 = new DTFecha(6, 8, 2024);
       
        Categoria Electronica = new Categoria( "Electrónica", true, null);
        Categoria Moda = new Categoria( "Moda",  true, null);
        Categoria HogaryCocina = new Categoria("Hogar y cocina", true, null);
        Categoria FloresyPlantas = new Categoria("Flores y plantas", true, null);
        Categoria Farmacia = new Categoria("Farmacia", true, null);
        Categoria Automotriz = new Categoria("Automotriz", true, null);
       
        List<Categoria> c1= new ArrayList<>();
        List<Categoria> c2= new ArrayList<>();
        List<Categoria> c3= new ArrayList<>();
      
        this.getCategorias().put(Automotriz.getNombreCat(), Automotriz);
        
        // Crear categoría Electrónica y sus subcategorías
       
        Categoria Celulares = new Categoria("Celulares", true, Electronica);
        Categoria Laptops = new Categoria("Laptops", true, Electronica);

        // Crear subcategorías de Celulares
        Categoria Samsung = new Categoria("Samsung", true, Celulares);
        Categoria iPhone = new Categoria("iPhone", true, Celulares);
        Categoria Xiaomi = new Categoria("Xiaomi", true, Celulares);

        // Crear subcategoría de Laptops
        Categoria Lenovo = new Categoria("Lenovo", true, Laptops);

        HashMap<String, Categoria> categorias = new HashMap<>();
        
        // Agregar categorías al HashMap
        categorias.put("C001", Electronica);
        categorias.put("C002", Celulares);
        categorias.put("C003", Laptops);
        
        this.categorias = categorias;

        // Asociar subcategorías usando agregarHijo (que agrega al HashMap con nombre y categoría)
        Electronica.agregarHijo("Celulares", Celulares);  // Agrega 'Celulares' como subcategoría de 'Electrónica'
        Electronica.agregarHijo("Laptops", Laptops);    // Agrega 'Laptops' como subcategoría de 'Electrónica'

        Celulares.agregarHijo("Samsung",Samsung);      // Agrega 'Samsung' como subcategoría de 'Celulares'
        Celulares.agregarHijo("Iphone", iPhone);       // Agrega 'iPhone' como subcategoría de 'Celulares'
        Celulares.agregarHijo("Xiaomi", Xiaomi);       // Agrega 'Xiaomi' como subcategoría de 'Celulares'

        Laptops.agregarHijo("Lenovo", Lenovo);         // Agrega 'Lenovo' como subcategoría de 'Laptops'

        this.getCategorias().put(Electronica.getNombreCat(), Electronica);
        this.getCategorias().put(Moda.getNombreCat(), Moda);
        this.getCategorias().put(Farmacia.getNombreCat(), Farmacia);
        this.getCategorias().put(FloresyPlantas.getNombreCat(), FloresyPlantas);
        this.getCategorias().put(FloresyPlantas.getNombreCat(), FloresyPlantas);
        this.getCategorias().put(HogaryCocina.getNombreCat(), HogaryCocina);
        
        String imagen1 = "media/images/Chico1.png";
        String imagen2 = "media/images/Chico2.png";
        String imagen3 = "media/images/Chico3.png";
        String imagen4 = "media/images/Chica1.png";
        String imagen5 = "media/images/Chica2.png";
        String imagen6 = "media/images/Chica3.png";
        
        String imagenProducto = "media/images/oreo.webp";
        
        Cliente cl1 = new Cliente("Salva", "Salvador", "Santurio", "Salva.Santu@example.com", fecha1, imagen2, "contrasenia");
        Cliente cl2 = new Cliente("Otto", "Nadia", "Gorría", "Panconqueso1012@example.com", fecha2, imagen5, "supersonic");
        Cliente cl3 = new Cliente("Protowarro", "Samuel", "Veintmilla", "Protowarro22@example.com", fecha2, imagen1, "aguanteoasis");
        
        Proveedor pr1 = new Proveedor("elIsma", "Isma", "Belardo", "lalala@example.com", fecha1, imagen3, "Sugar Cane Bank", "http://SugarCaneBank.com", "WonderWall");
        Proveedor pr2 = new Proveedor("Amy_w", "Amy", "Casadevino", "amy.casadevino@example.com", fecha2, imagen4, "Mary on a cross Enterprise", "http://MaryOnACross.com", "breakingBAld");
        Proveedor pr3 = new Proveedor("Zo3", "Zoe", "Gatusso", "zoe.gatu@example.com", fecha2, imagen6, "Flamin-Go", "http://FlaminGo.com", "Ghost-LOVER");
       
        
        List<String> img1 = new ArrayList<>();
        img1.add(imagenProducto);
        img1.add(imagenProducto);
        img1.add(imagenProducto);

        List<String> imgs = new ArrayList<>();
        imgs.add("img1");
        imgs.add("img2");
        
        List<String> especificaciones = new ArrayList<>();
        especificaciones.add("Buen");
        especificaciones.add("Producto");
        especificaciones.add("!!!!");
        
        Producto producto1 = new Producto("Agua Fresca", "Muy refrescante.", especificaciones, 999, 72.5f, img1, c2,  pr3, "Tienda1");
        Producto producto2 = new Producto("Guitarra", "Guitarra electrica de ebano.", especificaciones, 998, 16500.0f, img1, c1,  pr2, "Tienda2");
        Producto producto3 = new Producto("Control Remoto", "Util para televisores de alta calidad.", especificaciones, 997, 350.20f, img1, c3,  pr1, "Tienda3");
        
        Comentario comentario1 = new Comentario(1, "Me encanta este producto, muy útil en la cocina.", new ArrayList<>(), cl1, producto2, fecha1, 3);
        Comentario comentario2 = new Comentario(2, "No estoy muy satisfecho, esperaba más funciones.", new ArrayList<>(), cl2, producto2, fecha2, 1);
        Comentario comentario3 = new Comentario(3, "Lo recomiendo al 100%, excelente calidad.", new ArrayList<>(), cl3, producto2, fecha1, 5);
        
        Comentario comentario4 = new Comentario(4, "Estoy de acuerdo", new ArrayList<>(), cl1, producto2, fecha1, 0);
        Comentario comentario5 = new Comentario(5, "Estoy en desacuerdo", new ArrayList<>(), cl2, producto2, fecha2, 0);
        Comentario comentario6 = new Comentario(6, "Siu", new ArrayList<>(), cl3, producto2, fecha1, 0);
        
        comentario4.agregarRespuesta(comentario5);
        comentario1.agregarRespuesta(comentario4);
        comentario1.agregarRespuesta(comentario6);
        
        producto2.agregarComentario(comentario1);
        producto2.agregarComentario(comentario2);
        producto2.agregarComentario(comentario3);
        
        producto1.setEstrellas(3);
        producto2.setEstrellas(3);
        producto3.setEstrellas(3);
        
        pr1.agregarProducto(producto1);
        pr1.agregarProducto(producto2);
        pr1.agregarProducto(producto3);
        
        Moda.agregarProducto(producto1);
        Moda.agregarProducto(producto2);
        Moda.agregarProducto(producto3);
        Moda.agregarProducto(producto2);
        
        this.productoActual = producto2;
        
        this.productos.add(producto1);
        this.productos.add(producto2);
        this.productos.add(producto3);
        
        DTCantidad cantprod = new DTCantidad(3, producto2.getDTProducto());
        
        List<DTCantidad> listacantprod = new ArrayList<>();
        listacantprod.add(cantprod);
        
        OrdenDeCompra orden1 = new OrdenDeCompra(12332, fecha1, cl1, listacantprod);
        orden1.setPrecioTotal(1350.50f);
        
        //cl1.getOrdenesDeCompras().add(orden1);
        
        // HashMap<Integer, Cantidad> carrito = new HashMap<>();
        Cantidad cant1 = new Cantidad(producto1, 4);
        Cantidad cant2 = new Cantidad(producto2, 13);
        
        
        cl1.agregarProducto(cant1);
        cl1.agregarProducto(cant2);
        
        OrdenDeCompra orden2 = new OrdenDeCompra(77, fecha2, cl2, null);
        orden2.setPrecioTotal(555.75f);
        
        cl2.getOrdenesDeCompras().add(orden2);
        cl1.getOrdenesDeCompras().add(orden2);
        cl1.getOrdenesDeCompras().add(orden1);
        
        OrdenDeCompra orden3 = new OrdenDeCompra(66, fecha1, cl3, null);
        orden3.setPrecioTotal(625.50f);
        
        cl3.getOrdenesDeCompras().add(orden3);
        cl1.getOrdenesDeCompras().add(orden3);
        
        orden1.agregarProducto(producto1.getDTProducto(), 15);
        orden1.agregarProducto(producto2.getDTProducto(), 3);
        
        orden2.agregarProducto(producto3.getDTProducto(), 20);
        orden2.agregarProducto(producto1.getDTProducto(), 45);
        
        orden3.agregarProducto(producto3.getDTProducto(), 1000);
        orden3.agregarProducto(producto2.getDTProducto(), 1);
        
		ordenes.put(orden1.getNumero(), orden1);
		ordenes.put(orden2.getNumero(), orden2);
		ordenes.put(orden3.getNumero(), orden3);
		
		this.usuarios.add(cl1);
		this.usuarios.add(cl2);
		this.usuarios.add(pr1);
		this.usuarios.add(pr2);
		
       
		
	}

	@Override
	public void iniciarSesion(String emailOrNickname, String password) throws ContraseniaIncorrectaException, UsuarioNoEncontrado {
		
		Usuario usuarioEncontrado = null;

		for (Usuario user : this.usuarios) 
		{
		        if (user.getEmail().equalsIgnoreCase(emailOrNickname) || user.getNickname().equalsIgnoreCase(emailOrNickname)) 
		        {
		            usuarioEncontrado = user;
		            break;
		        }
		}
		if (usuarioEncontrado == null) {
			throw new UsuarioNoEncontrado("El Nickname o Email son incorrectos");
		}
		
	    if (!usuarioEncontrado.getContrasenia().equals(password)) {
	        throw new ContraseniaIncorrectaException("La contraseña es incorrecta.");
	    }

	    this.usuarioActual = usuarioEncontrado;
		
	}
	
	@Override
	public HashMap<Integer, DTCantidad> getCarritoActual() throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				return cli.getDTCarrito();
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
		throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
	}
	
	@Override
	public void eliminarItemCarrito(int numReferencia) throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				cli.getCarrito().remove(numReferencia);
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
	}
	
	@Override
	public void modificarCantidadItemCarrito(int numReferencia, int cantidad) throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				cli.modificarCantidadItemCarrito(numReferencia, cantidad);
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
	}
	
	/*
	
	@Override
	public Producto getProducto(int numReferencia) {
		for (Categoria categoria : this.categorias.values()) {
			return getProductoEnCategoria(categoria, numReferencia);
	    }
		return null;
	}

	//@Override
	private Producto getProductoEnCategoria(Categoria categoria, int numReferencia) { // Hay que probar esto
	    for (Producto producto : categoria.getProductos()) {
	        if (producto.getNumReferencia() == numReferencia){
        		return producto;
	        }
	    }

	    for (Categoria subcategoria : categoria.getHijos().values()) {
			return getProductoEnCategoria(subcategoria, numReferencia);
	    }
	    return null;
	}
	
	@Override
	public void actualizarCarritoActual(HashMap<Integer, DTCantidad> DTCarrito) throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				HashMap<Integer, Cantidad> nuevoCarrito = new HashMap<>();
				for (DTCantidad dt : DTCarrito.values()) {
					Producto prod = this.getProducto(dt.getProducto().getNumero());
					Cantidad cant = new Cantidad(prod, dt.getCantidad());
					nuevoCarrito.put(prod.getNumReferencia(), cant);
				}
				cli.setCarrito(nuevoCarrito);
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
		throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
	}
	
	*/
	
	public void realizarCompra(OrdenDeCompra ord) throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				ord.setCliente(cli);
				cli.vincularOrdenDeCompra(ord);
				cli.setCarrito(new HashMap<>());
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
		throw new UsuarioNoExisteException("No se ha seleccionado ningún usuario previamente.");
	}
	
	
}