package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTClienteDetallado extends DTCliente {
	
	private String nombre;
	private String apellido;
	private DTFecha fechaNac;
	private String foto;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public DTFecha getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(DTFecha fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public DTClienteDetallado(String nickName, String email, String nombre, String apellido, DTFecha fechaNac, String foto){
		super(nickName, email);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setFechaNac(fechaNac);
		this.setFoto(foto);
	}
	
	public String toString() {
	    return "Nickname " + this.getNickname() + System.lineSeparator()
	    	+ "Email: " + this.getEmail() + System.lineSeparator()
	    	+ "Nombre: " + this.nombre + System.lineSeparator()
	    	+ "Apellido: " + this.apellido + System.lineSeparator()
	    	+ "Fecha de nacimiento: " + this.fechaNac + System.lineSeparator()
	        + "Foto: " + this.foto;
	}
	
	
}
