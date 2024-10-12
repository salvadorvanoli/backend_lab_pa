package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class Comentario {

		private String id;
		private String contenido;
		private List<Comentario> comentarios;
		private Cliente cliente;
		private Producto producto;
		private DTFecha fecha;
		private int estrellas;
		
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
		public Comentario(String id, String contenido) {
			super();
			this.id = id;
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
		public List<Comentario> getComentarios() {
			return comentarios;
		}
		public void setComentarios(List<Comentario> comentarios) {
			this.comentarios = comentarios;
		}
		public Comentario(String id, String contenido, List<Comentario> comentarios, Cliente cliente,
				Producto producto, DTFecha fecha, int estrellas) {
			super();
			this.id = id;
			this.contenido = contenido;
			this.comentarios = comentarios;
			this.cliente = cliente;
			this.producto = producto;
			this.setFecha(fecha);
			this.setEstrellas(estrellas);
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
		
		public void agregarRespuesta(Comentario comentario) {
			this.comentarios.add(comentario);
		}
}
