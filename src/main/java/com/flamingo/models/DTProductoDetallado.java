package com.flamingo.models;


import java.util.List;
import java.util.ArrayList;

public class DTProductoDetallado extends DTProducto{
	
	private List<String> especificaciones;
	private List<String> categorias;
	private DTProveedor proveedor;
	private String nombreTienda;
	private int estrellas;
	private int cantCompras;
	
	
	public List<String> getEspecificaciones() {
		return especificaciones;
	}
	
	public void setEspecificaciones(List<String> especificaciones) {
		this.especificaciones = especificaciones;
	}
	
	public List<String> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	
	public DTProveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(DTProveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public int getCantCompras() {
		return cantCompras;
	}

	public void setCantCompras(int cantCompras) {
		this.cantCompras = cantCompras;
	}

	public DTProductoDetallado(String nombre, String descripcion, float precio, int numReferencia, List<String> especificaciones, List<String> categorias, DTProveedor proveedor, List<String> imagenes, String nombreTienda, int estrellas, int cantCompras) {
		super(nombre, numReferencia, descripcion, precio, imagenes);
		this.especificaciones = especificaciones;
		this.categorias = categorias;
		this.proveedor = proveedor;
		this.nombreTienda = nombreTienda;
		this.estrellas = estrellas;
		this.cantCompras = cantCompras;
	}
	
	public String toString() {
		return "Nombre: " + this.getNombre() + System.lineSeparator() +
				"Num. de Referencia: " + this.getNumero() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator() +
				"Especificación: " + this.getNumero() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator();
	}
	
	
	
	
}
