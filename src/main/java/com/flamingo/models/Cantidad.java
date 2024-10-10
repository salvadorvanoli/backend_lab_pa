package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class Cantidad {
	
	private DTProducto producto;
	private int cantidad;
	
	public DTProducto getProducto() {
		return producto;
	}
	public void setProducto(DTProducto producto) {
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
	
	public void linkProducto(DTProducto producto) {
		this.producto = producto;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public Cantidad(DTProducto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this, this.producto, this.getSubtotal());
	}
	
	public String toString() {
		return "Nombre del producto: " + this.producto.getNombre() + System.lineSeparator()
        + "Cantidad del producto = " + this.cantidad + ";" + System.lineSeparator();
	}
	
	public String toString2() {
		return this.producto.getNombre() + System.lineSeparator()
        + "   X" + this.cantidad + "" + System.lineSeparator();
	}
}
