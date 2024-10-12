package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTOrdenDeCompra {
	private int numero;
	private DTFecha fecha;
	private float precioTotal;
	private Cliente cliente;
	private List<DTCantidadProducto> cantidades;
	
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public List<DTCantidadProducto> getCantidades() {
		return cantidades;
	}
	public void setCantidades(List<DTCantidadProducto> cantidades) {
		this.cantidades = cantidades;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public DTFecha getFecha() {
		return fecha;
	}
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public DTOrdenDeCompra(int numero, Cliente cliente, float precio, DTFecha fecha, List<DTCantidadProducto> cantidades) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.precioTotal = precio;
		this.fecha = fecha;
		this.cantidades = cantidades;
	}
	
	 @Override
	    public String toString() {
	        return "Número de la orden: " + numero;
	    }
	 
	 public String toStringCliente() {
			return this.getCliente().getNickname();
		}
	 
	 public String toStringNumero() {
		 return String.valueOf(this.getNumero());

		}
}
