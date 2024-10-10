package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class OrdenDeCompra {
	
	private int numero;
	private float precioTotal;
	private DTFecha fecha;
	private Cliente cliente;
	private List<Cantidad> cantidad;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public DTFecha getFecha() {
		return fecha;
	}
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cantidad> getCantidad() {
		return cantidad;
	}
	public void setCantidad(List<Cantidad> cantidad) {
		this.cantidad = cantidad;
	}
	
	public void desvincularCliente () {
		this.cliente.desvincularOrdenDeCompra(this);
	}
	
	public void agregarProducto(DTProducto producto, int cantidad) {
		Cantidad nueva = new Cantidad(cantidad);
		nueva.setProducto(producto);
		List<Cantidad> lista = this.getCantidad();
		lista.add(nueva);
		this.setCantidad(lista);
	}
	

	public OrdenDeCompra(int numero, DTFecha fecha, Cliente cliente, List<Cantidad> cantidades) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		if (cantidades != null && !(cantidades.isEmpty())) {
			this.cantidad = cantidades;
			float sumPrecio = 0;
			for (Cantidad can : cantidades) {
				float subtotal = can.getCantidad() * can.getProducto().getPrecio();
				sumPrecio += subtotal;
			}
			this.precioTotal = sumPrecio;
		} else {
			this.cantidad = new ArrayList<>();
			this.precioTotal = 0;
		}
	}
	
	public String toString(){
		String fechaFormateada = String.format("%02d/%02d/%04d", this.fecha.getDia(), this.fecha.getMes(), this.fecha.getAnio());
	    String retorno = "Orden de Compra " + this.numero + System.lineSeparator()
	                + "Fecha: " + fechaFormateada + System.lineSeparator()
	                + "Precio total: " + this.getPrecioTotal() + System.lineSeparator()
	                + "Nombre cliente: " + this.cliente.getNickname() + System.lineSeparator()
	                + System.lineSeparator() +  "-------------------- PRODUCTOS --------------------" + System.lineSeparator() + System.lineSeparator();
	    Integer i = 1;
	    for (Cantidad prod : this.cantidad){
	        retorno += "Producto número " + i.toString() + ": "  + System.lineSeparator() + prod.toString() + System.lineSeparator();
	        i++;
	    }
	    return retorno;
	}
	
	
	public DTOrdenDeCompra getDTOrden() {
		return new DTOrdenDeCompra(this.numero, this.cliente, this.precioTotal, this.fecha, this.cantidad);
	}
	
	public DTOrdenDeCompraDetallada getDTOrdenDetallada() {
		List<DTCantidadProducto> lista = new ArrayList<>();
		for(Cantidad cant : this.cantidad) {
			DTCantidadProducto nuevo = cant.getDTCantidadProducto();
			lista.add(nuevo);
		}
		return new DTOrdenDeCompraDetallada(this.numero, this.cliente, this.precioTotal, this.fecha, this.cantidad, lista);
	}
	

}