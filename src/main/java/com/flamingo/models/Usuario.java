package com.flamingo.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario{
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private DTFecha fechaNac;
    private String foto;
    private String contrasenia;
    private List <OrdenDeCompra> ordenesDeCompras;

    public Usuario(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia){
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.contrasenia = contrasenia;
        this.ordenesDeCompras = new ArrayList<>();
    }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public List <OrdenDeCompra> getOrdenesDeCompras(){
		return this.ordenesDeCompras;
	}
	
	public void setOrdenesDeCompras(List <OrdenDeCompra> OrdenesDeCompras){
		this.ordenesDeCompras = OrdenesDeCompras;
	}
	
	public void desvincularOrdenDeCompra(OrdenDeCompra ord){
		this.ordenesDeCompras.remove(ord);
	}
	
	public void vincularOrdenDeCompra(OrdenDeCompra ord) {
		this.ordenesDeCompras.add(ord);
	}
	
	public String toString() {
		return this.nombre + " - " + this.apellido;
	}
	
}