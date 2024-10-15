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
        
            boolean res = sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", ruta, contra, contra);
            assertEquals(true, res);
        
   }
    
	 @Test
	    public void testAltaUsuarioProveedorConNicknameRepetido() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
	        // Instancia del sistema
	        ISistema sis = SistemaFactory.getInstancia().getISistema();
	        DTFecha fecha = new DTFecha(12, 06, 1978);

	        // Se agrega un proveedor con un nickname existente
	        //boolean res = sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
	        //assertEquals(res, true);
	        
	        
	        // Intentar registrar otro usuario con el mismo nickname
	       
	        boolean ress = sis.altaUsuarioProveedor("nickname2", "email2@gmail.com", "nombredos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "12345678");
	        boolean res2 = sis.altaUsuarioCliente("nickname2", "email2@gmail.com", "nombre", "apellidooss", fecha, "/aa/a", "123456789", "123456789");
	        assertEquals(false, res2);
	    }

}
