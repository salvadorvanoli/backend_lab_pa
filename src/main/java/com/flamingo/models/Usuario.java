package com.flamingo.models;

import java.util.HashMap;

import com.flamingo.models.DTFecha;

public class Usuario{
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private DTFecha fechaNac;
    private String foto;
    private String contrasenia;
    private HashMap<Integer, Cantidad> carrito;

    public Usuario(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia){
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.contrasenia = contrasenia;
        this.carrito = new HashMap<>();
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
	
	public HashMap<Integer, Cantidad> getCarrito(){
		return this.carrito;
	}
	
	public void agregarProducto(Cantidad prod) {
		if (this.carrito.containsKey(prod.getProducto().getNumero()));
		this.carrito.put(prod.getProducto().getNumero(), prod);
	}
	
	public void quitarProducto(int numProd) {
		this.carrito.remove(numProd);
	}
	
	public void setCarrito(HashMap<Integer, Cantidad> carrito) {
		this.carrito = carrito;
	}
	
}