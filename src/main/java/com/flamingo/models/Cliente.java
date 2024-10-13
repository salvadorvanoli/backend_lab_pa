package com.flamingo.models;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente extends Usuario{
		private List <OrdenDeCompra> OrdenesDeCompras;
		private List <Comentario> Comentarios;
		private HashMap<Integer, Cantidad> carrito;
		
		public Cliente(String nickName, String nombre, String apellido, String email, DTFecha fecha, String foto, String contrasenia){
			super(nickName, nombre, apellido, email, fecha, foto, contrasenia);
			this.OrdenesDeCompras = new ArrayList<>();
			this.Comentarios = new ArrayList<>();
			this.carrito = new HashMap<>();
		}
		
		public DTCliente getDTCliente() {
			DTCliente c = new DTCliente(this.getNickname(), this.getEmail());
			return c;
		}
		
		public void desvincularOrdenDeCompra(OrdenDeCompra ord){
			this.OrdenesDeCompras.remove(ord);
		}
		
		public void vincularOrdenDeCompra(OrdenDeCompra ord) {
			this.OrdenesDeCompras.add(ord);
		}
		
		public void setOrdenesDeCompras(List <OrdenDeCompra> OrdenesDeCompras){
			this.OrdenesDeCompras = OrdenesDeCompras;
		}
		
		public void setComentarios(List <Comentario> Comentarios){
			this.Comentarios = Comentarios;
		}
		
		public List <OrdenDeCompra> getOrdenesDeCompras(){
			return this.OrdenesDeCompras;
		}
		
		public List <Comentario> getComentarios(){
			return this.Comentarios;
		}
		
		public DTClienteDetallado getDTClienteDetallado(){
			DTClienteDetallado c = new DTClienteDetallado(this.getNickname(), this.getEmail(), this.getNombre(), this.getApellido(), this.getFechaNac(), this.getFoto());
			return c;
		}
		
		public HashMap<Integer, Cantidad> getCarrito(){
			return this.carrito;
		}
		
		public void agregarProducto(Cantidad prod) {
			if (this.carrito.containsKey(prod.getProducto().getNumReferencia()));
			this.carrito.put(prod.getProducto().getNumReferencia(), prod);
		}
		
		public void quitarProducto(int numProd) {
			this.carrito.remove(numProd);
		}
		
		public void setCarrito(HashMap<Integer, Cantidad> carrito) {
			this.carrito = carrito;
		}
		
		public void modificarCantidadItemCarrito(int numReferencia, int cantidad) {
			Cantidad cant = this.carrito.get(numReferencia);
			cant.setCantidad(cantidad);
			this.carrito.put(numReferencia, cant);
		}
		
		public HashMap<Integer, DTCantidad> getDTCarrito() {
			HashMap<Integer, DTCantidad> carrito = new HashMap<>();
			for (Cantidad cant : this.carrito.values()) {
				DTCantidad dt = cant.getDTCantidad();
				carrito.put(dt.getProducto().getNumero(), dt);
			}
			return carrito;
		}
		
		public Boolean comproProducto(int numReferencia) {
			for(OrdenDeCompra orden : this.getOrdenesDeCompras()) {
				if(orden.tieneProducto(numReferencia)) {
					return true;
				}
			}
			return false;
		}
}
