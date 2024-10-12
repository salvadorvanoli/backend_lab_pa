package com.flamingo.models;

public class DTCantidad {
	private DTProducto producto;
	private int cantidad;
	
	public DTCantidad(int cantidad, DTProducto prod) {
		super();
		this.cantidad = cantidad;
		this.producto = prod;
	}
	
	public DTProducto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto, this.getSubtotal());
	}

}
