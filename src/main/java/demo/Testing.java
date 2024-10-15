package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
	
	private ISistema sis;
    private DTFecha fecha;

    
    @Test
	public void testAltaUsuarioProveedorExitoso() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
	    ISistema sis = SistemaFactory.getInstancia().getISistema();
	    DTFecha fecha = new DTFecha(12, 06, 1978);

	    // Verifica si el usuario se crea correctamente
	    boolean res1 = sis.altaUsuarioProveedor("nicknameProveedor", "proveedor@gmail.com", "nombreuno", "apellidouno", fecha, "Compania", "https://compania.com", "/imagen.jpg", "12345678", "12345678");
	    assertTrue(res1, "El primer usuario proveedor debería registrarse correctamente.");
	}
    
    @Test
	public void testAltaUsuarioClienteExitoso() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
	    ISistema sis = SistemaFactory.getInstancia().getISistema();
	    DTFecha fecha = new DTFecha(12, 06, 1978);

	    // Verifica si el usuario se crea correctamente
	    boolean res1 = sis.altaUsuarioCliente("nicknameCliente", "cliente@gmail.com", "nombreuno", "apellidouno", fecha, "/imagen.jpg", "12345678", "12345678");
	    assertTrue(res1, "El primer usuario proveedor debería registrarse correctamente.");
	}

    @Test
    public void testAltaUsuarioProveedorConEmailRepetido() {
    	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 06, 1978);
        try {
            sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
        } catch (Exception e) {
            fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
        }
        
        // Intentar registrar otro proveedor con el mismo email
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioProveedor("nickname2", "email1@gmail.com", "nombredos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "12345678");
            // no deberia ejecutarse
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el email \"email1@gmail.com\".", thrown.getMessage());
    }
    
    
    @Test
    public void testAltaUsuarioClienteConEmailRepetido() {
    	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 06, 1978);
        
        
        // Intentar registrar otro cliente con el mismo email
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioCliente("nickname3", "email1@gmail.com", "nombredos", "apellidodos", fecha, "/12/12/2004", "12345678", "12345678");
            // no deberia ejecutarse
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el email \"email1@gmail.com\".", thrown.getMessage());
    }
    
    @Test
    public void testAltaUsuarioProveedorConNicknameRepetido() {
    	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 06, 1978);
        try {
            sis.altaUsuarioProveedor("nicknameRepetir", "noserepite@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
        } catch (Exception e) {
            fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
        }
        // Intentar registrar otro proveedor con el mismo nickname
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioProveedor("nicknameRepetir", "emailnuevoprov@gmail.com", "nombredos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "12345678");
            // no deberia ejecutarse
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el nickname \"nicknameRepetir\".", thrown.getMessage());
    }
	



}
