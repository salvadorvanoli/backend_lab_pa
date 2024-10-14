package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DTCategoria {
	
	private String nombreCat;
	private HashMap<String, DTCategoria> hijas;
	
	public String getNombreCat() {
		return nombreCat;
	}
	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}
	public HashMap<String, DTCategoria> getHijas() {
		return hijas;
	}
	public void setHijas(HashMap<String, DTCategoria> hijas) {
		this.hijas = hijas;
	}
	
	public DTCategoria(String nombreCat, HashMap<String, DTCategoria> hijos) {
		super();
		this.nombreCat = nombreCat;
		this.hijas = hijos;
	}
	
}
