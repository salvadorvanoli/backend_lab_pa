package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.DTFecha;
import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;

class Testing {

	public Testing() {
		super();
	}

	@Test
    public void testAltaUsuarioProveedorExitoso() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 06, 1978);
        String contra = "12345678";
        String ruta = "/hola/jeje";
        boolean hola;
        if (sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", ruta, contra, contra)) {
        	hola = true;
        }
        else {
        	hola = false;
        }
            assertEquals(false, hola);
        
   }
    
	
	@Test
	public void testAltaUsuarioClienteExitoso() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
	    ISistema sis = SistemaFactory.getInstancia().getISistema();
	    DTFecha fecha = new DTFecha(12, 06, 1978);

	    // Verifica si el usuario se crea correctamente
	    boolean res1 = sis.altaUsuarioCliente("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "/imagen.jpg", "12345678", "12345678");
	    assertTrue(res1, "El primer usuario cliente debería registrarse correctamente.");
	}



}
