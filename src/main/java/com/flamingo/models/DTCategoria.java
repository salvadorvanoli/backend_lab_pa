package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DTCategoria {
	
	private String nombreCat;
	private List<DTCategoria> hijas;
	
	public String getNombreCat() {
		return nombreCat;
	}
	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}
	public List<DTCategoria> getHijas() {
		return hijas;
	}
	public void setHijas(List<DTCategoria> hijas) {
		this.hijas = hijas;
	}
	
	public DTCategoria(String nombreCat, List<DTCategoria> hijos) {
		super();
		this.nombreCat = nombreCat;
		this.hijas = hijos;
	}
	
}
