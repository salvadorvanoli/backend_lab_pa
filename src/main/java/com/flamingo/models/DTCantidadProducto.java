package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTCantidadProducto {
	
	private int cantidad;
	private DTProducto producto;
	private float subtotal;
	
	public DTCantidadProducto(int cantidad, DTProducto producto, float subtotal) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.subtotal = subtotal;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public DTProducto getProducto() {
		return producto;
	}
	public void setProducto(DTProducto producto) {
		this.producto = producto;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	@Override
	public String toString() {
		return this.producto.getNombre() + " - Cantidad: " + this.cantidad + " - Subtotal: " + this.subtotal;
	}
	
	
}
