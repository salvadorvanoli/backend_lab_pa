package com.flamingo.models;

import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String nickname;
    private String tipo; 
    private String email;
    private String fecha; 
    private String foto; 
    private String web; 
    private String empresa; 
    private String id;
    private String contrasena; 
    private List<Orden> ordenes; 
    private List<Producto> productos; 

    // Constructor
    public Usuario(String nombre, String apellido, String nickname, String tipo, String email,
                   String fecha, String foto, String web, String empresa, String id,
                   String contrasena, List<Orden> ordenes, List<Producto> productos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.tipo = tipo;
        this.email = email;
        this.fecha = fecha;
        this.foto = foto;
        this.web = web;
        this.empresa = empresa;
        this.id = id;
        this.contrasena = contrasena;
        this.ordenes = ordenes;
        this.productos = productos;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

 
    
   
}
