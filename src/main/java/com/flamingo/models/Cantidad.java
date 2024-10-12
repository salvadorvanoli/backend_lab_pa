package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class Cantidad {
	private Producto producto;
	private int cantidad;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Cantidad(int cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
	public void linkProducto(Producto producto) {
		this.producto = producto;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public Cantidad(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public DTCantidad getDTCantidad() {
		return new DTCantidad(this.cantidad, this.producto.getDTProducto(), this.producto.getImagenes());
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto.getDTProducto(), this.getSubtotal());
	}
	
	public String toString() {
		return "Nombre del producto: " + this.producto.getNombreProducto() + System.lineSeparator()
        + "Cantidad del producto = " + this.cantidad + ";" + System.lineSeparator();
	}
	
	public String toString2() {
		return this.producto.getNombreProducto() + System.lineSeparator()
        + "   X" + this.cantidad + "" + System.lineSeparator();
	}
}
