package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTComentario {

		private String id;
		private String contenido;
		private List<Comentario> comentarios;
		private Cliente cliente;
		private Producto producto;
		private DTFecha fecha;
		private int estrellas;
		
		public DTComentario(String id, String contenido, List<Comentario> comentarios, Cliente cliente,
				Producto producto, DTFecha fecha, int estrellas) {
			super();
			this.setId(id);
			this.setContenido(contenido);
			this.setComentarios(comentarios);
			this.setCliente(cliente);
			this.setProducto(producto);
			this.setFecha(fecha);
			this.setEstrellas(estrellas);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getContenido() {
			return contenido;
		}

		public void setContenido(String contenido) {
			this.contenido = contenido;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Producto getProducto() {
			return producto;
		}

		public void setProducto(Producto producto) {
			this.producto = producto;
		}

		public DTFecha getFecha() {
			return fecha;
		}

		public void setFecha(DTFecha fecha) {
			this.fecha = fecha;
		}

		public int getEstrellas() {
			return estrellas;
		}

		public void setEstrellas(int estrellas) {
			this.estrellas = estrellas;
		}

		public List<Comentario> getComentarios() {
			return comentarios;
		}

		public void setComentarios(List<Comentario> comentarios) {
			this.comentarios = comentarios;
		}
		
		

}
