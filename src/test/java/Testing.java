import org.junit.jupiter.api.Test;
package com.flamingo.controllers;

import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Sistema;

class Testing {
	
	@Test
	public void test1{
		
		ISistema sis = SistemaFactory.getInstancia().getISistema();
		int dia = 12;
		int mes = 06;
		int anio = 2004;
		DTFecha fecha = new DTFecha(dia, mes, anio);
		sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "ComapaniaUno", "https://www.uno.com", null, "12345678", "12345678");
	}
	
}