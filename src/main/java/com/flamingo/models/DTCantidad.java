package com.flamingo.models;

import java.util.List;

public class DTCantidad {
	private DTProducto producto;
	private int cantidad;
	private List<String> imagenesProd;
	
	public DTCantidad(int cantidad, DTProducto prod, List<String> imagenes) {
		super();
		this.cantidad = cantidad;
		this.producto = prod;
		this.imagenesProd = imagenes;
	}
	
	public DTProducto getProducto() {
		return this.producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}
	
	public List<String> getImagenes() {
		return this.imagenesProd;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto, this.getSubtotal());
	}

}
