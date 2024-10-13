package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTProducto {
	private String nombre;
	private String descripcion;
	private float precio;
	private int numReferencia;
	private List<String> imagenes;
	
	
	public DTProducto(String nombre, int num, String descripcion, float precio, List<String> imagenes) {
		super();
		this.nombre = nombre;
		this.numReferencia = num;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagenes = imagenes;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descricpion) {
		this.descripcion = descricpion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public int getNumero() {
		return this.numReferencia;
	}
	
	public List<String> getImagenes() {
		return this.imagenes;
	}
	
	public String toString() {
		return this.nombre + " - " + this.descripcion + " - " + String.valueOf(this.precio);
	}
	
	
}
