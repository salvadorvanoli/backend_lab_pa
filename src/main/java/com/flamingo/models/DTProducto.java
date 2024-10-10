package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTProducto {
	private String nombre;
	private String descripcion;
	private float precio;
	
	
	public DTProducto(String nombre, String descripcion, float precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
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
	
	public String toString() {
		return this.nombre + " - " + this.descripcion + " - " + String.valueOf(this.precio);
	}
	
	
}
