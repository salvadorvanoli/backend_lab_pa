package com.flamingo.models;

import java.util.List;

public class DTCantidad {
	private DTProducto producto;
	private int cantidad;
	
	public DTCantidad(int cantidad, DTProducto prod) {
		super();
		this.cantidad = cantidad;
		this.producto = prod;
	}
	
	public DTProducto getProducto() {
		return this.producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto, this.getSubtotal());
	}
	
	public String toString() {
		return "Producto: " + this.producto.getNombre() + " - Cantidad: " + this.cantidad + " - Subtotal: " + this.getSubtotal();
	}

}
