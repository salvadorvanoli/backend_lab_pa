package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTFecha {

	private int dia;
	private int mes;
	private int anio;
	
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public DTFecha(int dia, int mes, int anio) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	public String toString() {
		return String.valueOf(this.dia) + '/' + String.valueOf(this.mes) + '/' + String.valueOf(this.anio);
	}
	
}
