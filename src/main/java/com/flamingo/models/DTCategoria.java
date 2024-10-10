package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DTCategoria {
	
	private String nombreCat;
	private HashMap<String, Categoria> hijas;
	
	public String getNombreCat() {
		return nombreCat;
	}
	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}
	public HashMap<String, Categoria> getHijas() {
		return hijas;
	}
	public void setHijas(HashMap<String, Categoria> hijas) {
		this.hijas = hijas;
	}
	
	public DTCategoria(String nombreCat, HashMap<String, Categoria> hijos) {
		super();
		this.nombreCat = nombreCat;
		this.hijas = hijos;
	}
	
}
