package com.flamingo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente extends Usuario{
		private HashMap<Integer, Cantidad> carrito;
		private List <OrdenDeCompra> OrdenesDeCompras;
		private List <Comentario> Comentarios;
		
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
		
		public HashMap<Integer, Cantidad> getCarrito(){
			return this.carrito;
		}
		
		public void agregarProducto(Cantidad prod) {
			if (this.carrito.containsKey(prod.getProducto().getNumero()));
			this.carrito.put(prod.getProducto().getNumero(), prod);
		}
		
		public void quitarProducto(int numProd) {
			this.carrito.remove(numProd);
		}
		
		public DTClienteDetallado getDTClienteDetallado(){
			DTClienteDetallado c = new DTClienteDetallado(this.getNickname(), this.getEmail(), this.getNombre(), this.getApellido(), this.getFechaNac(), this.getFoto());
			return c;
		}
		
		
}
