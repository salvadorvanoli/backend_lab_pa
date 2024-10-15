package com.flamingo.models;


import java.util.List;
import java.util.ArrayList;

public class DTProductoDetallado extends DTProducto{
	
	private List<String> especificaciones;
	private List<String> categorias;
	private DTProveedor proveedor;
	
	
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
	public DTProductoDetallado(String nombre, String descripcion, float precio, int numReferencia, List<String> especificaciones, List<String> categorias, DTProveedor proveedor, List<String> imagenes) {
		super(nombre, numReferencia, descripcion, precio, imagenes);
		this.especificaciones = especificaciones;
		this.categorias = categorias;
		this.proveedor = proveedor;
	}
	
	public String toString() {
		return "Nombre: " + this.getNombre() + System.lineSeparator() +
				"Num. de Referencia: " + this.getNumero() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator() +
				"Especificación: " + this.getNumero() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator();
	}
	
	
	
	
}
