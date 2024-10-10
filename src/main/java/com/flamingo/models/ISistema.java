package com.flamingo.models;
import java.util.HashMap;
// import java.util.ArrayList;
import java.util.List;

import com.flamingo.exceptions.*;

public abstract class ISistema{
	
	public ISistema() {};
	
	// También agrego los getters
	
	public abstract List<Usuario> getUsuarios();
	
	public abstract HashMap<Integer, OrdenDeCompra> getOrdenes();
	
	public abstract HashMap<String, Categoria> getCategorias();
	
	public abstract Usuario getUsuarioActual();
	
	public abstract OrdenDeCompra getOrdenDeCompraActual();
	
	public abstract Categoria getCategoriaActual();
	
	public abstract Producto getProductoActual();
	
	public abstract void crearCasos();
	
	// Falta destructor (busqué y creo que no tiene)
	
	// Le puse abstract a todo pero no se si esta bien
	
	public abstract DTFecha getFechaActual();
	
	public abstract boolean altaUsuarioCliente(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException;
	
	public abstract boolean altaUsuarioProveedor(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String nomCompania, String linkWeb, String imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException;

	public abstract boolean registrarProducto(String titulo, int numReferencia, String descrip, List<String> especificaciones, float precio, List<Categoria> categorias, List<String> imagenes) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException;
	
	public abstract DTProductoDetallado verInformacionProducto();

	public abstract List<DTCategoria> listarCategorias();

	public abstract boolean elegirCategoria(String nombreCat) throws CategoriaNoExisteException;
	
	public abstract Categoria buscarCategoriaRecursivamente(String nombreCat, HashMap<String, Categoria> categorias);

	public abstract List<DTProducto> listarProductos();
	
	public abstract boolean elegirProducto(String nombreProd) throws ProductoNoExisteException; // ESTA LA AGREGUÉ DESPUÉS

	public abstract Categoria altaCategoria(String nombre, boolean tieneProductos, Categoria padre) throws CategoriaRepetidaException;
	
	public abstract boolean existeCategoria(String nombreCategoria);

	public abstract List<DTOrdenDeCompra> listarOrdenesDeCompra();

	public abstract boolean elegirOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException;
	
	public abstract DTOrdenDeCompraDetallada verInformacionOrdenDeCompra();
	
	public abstract int generarCodigoOrden();

	public abstract DTOrdenDeCompraDetallada darAltaOrden() throws UsuarioNoExisteException;

	public abstract void cancelarOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException;

	public abstract boolean agregarProducto(String nombreProducto, int cantidad) throws ProductoNoExisteException;

	public abstract List<DTCliente> listarClientes();

	public abstract boolean elegirCliente(String nickname) throws UsuarioNoExisteException;
	
	public abstract DTClienteDetallado verInformacionCliente();

	public abstract void quitarProductoDeCategorias(boolean seAgreganCategorias);
	
	public abstract void agregarProductoACategorias(List<Categoria> listaCat) throws CategoriaNoPuedeTenerProductosException;
	
	public abstract void agregarCategoriasAProducto(List<Categoria> listaCat) throws CategoriaNoPuedeTenerProductosException;
	
	public abstract void existeProducto(String nombreProd, int numReferencia, boolean caso) throws ProductoRepetidoException;

	// public abstract boolean buscarProductoEnCategoria(Categoria categoria, String nombreProd, int numReferencia);
	
	public abstract void modificarDatosProducto(String nombreProd, int numReferencia, String descripcion, float precio, List<String> especificacion) throws ProductoRepetidoException;
	
	public abstract void modificarImagenesProducto(List<String> imagenes);
	
	public abstract List<DTProveedor> listarProveedores();
	
	public abstract void elegirProveedor(String nickname) throws UsuarioNoExisteException;
	
	public abstract DTProveedorDetallado verInformacionProveedor();
	
	public abstract OrdenDeCompra agregarOrden(List<Cantidad> cantidad);
	
	public abstract void setTodoNull();

}