package com.flamingo.models;


import java.util.List;
import java.util.ArrayList;

public class DTProductoDetallado extends DTProducto{
	
	private int numReferencia;
	private String especificaciones;
	private List<String> categorias;
	private DTProveedor proveedor;
	private List<String> imagenes;
	
	
	public int getNumReferencia() {
		return numReferencia;
	}
	public void setNumReferencia(int numReferencia) {
		this.numReferencia = numReferencia;
	}
	public String getEspecificaciones() {
		return especificaciones;
	}
	public void setEspecificaciones(String especificaciones) {
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
	public List<String> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}
	public DTProductoDetallado(String nombre, String descripcion, float precio, int numReferencia,
			String especificaciones, List<String> categorias, DTProveedor proveedor, List<String> imagenes) {
		super(nombre, numReferencia, descripcion, precio);
		this.numReferencia = numReferencia;
		this.especificaciones = especificaciones;
		this.categorias = categorias;
		this.proveedor = proveedor;
		this.imagenes = imagenes;
	}
	
	public String toString() {
		return "Nombre: " + this.getNombre() + System.lineSeparator() +
				"Num. de Referencia: " + this.getNumReferencia() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator() +
				"Especificación: " + this.getNumReferencia() + System.lineSeparator() +
				"Descripcion: " + this.getDescripcion() + System.lineSeparator();
	}
	
	
	
	
}
