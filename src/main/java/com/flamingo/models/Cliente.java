package com.flamingo.models;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente extends Usuario{
		private List <Comentario> Comentarios;
		private HashMap<Integer, Cantidad> carrito;
		
		public Cliente(String nickName, String nombre, String apellido, String email, DTFecha fecha, String foto, String contrasenia){
			super(nickName, nombre, apellido, email, fecha, foto, contrasenia);
			this.Comentarios = new ArrayList<>();
			this.carrito = new HashMap<>();
		}
		
		public DTCliente getDTCliente() {
			DTCliente c = new DTCliente(this.getNickname(), this.getEmail());
			return c;
		}
		
		public void setComentarios(List <Comentario> Comentarios){
			this.Comentarios = Comentarios;
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
		
		public void realizarCompra(OrdenDeCompra ord) {
			ord.setCliente(this); 
			this.vincularOrdenDeCompra(ord);
			List<Proveedor> proveedores = new ArrayList<>();
			for (Cantidad item : this.carrito.values()) {
				Producto prod = item.getProducto();
				prod.setCantCompras(prod.getCantCompras() + item.getCantidad());
				if (!proveedores.contains(item.getProducto().getProveedor())) {
					proveedores.add(item.getProducto().getProveedor());
				}
			}
			ord.setProveedores(proveedores);
			
			for(Proveedor prov : proveedores) {
				prov.getOrdenesDeCompras().add(ord);
			}
			
			this.setCarrito(new HashMap<>());
			//System.out.println("ORDENNNNNNN: ");
			//System.out.println(ord);
		}
}
