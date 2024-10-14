package demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.DTFecha;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.exceptions.ContraseniaIncorrectaException;

class Testing {

    @Test
    public void testAltaUsuarioProveedorExitoso() {
        // Instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 06, 1978);
        
        try {
            // Llamada al método a testear
            boolean res = sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
            
            // Comprobación del resultado esperado
            assertTrue(res, "El registro del proveedor debería ser exitoso.");
        } catch (UsuarioRepetidoException | ContraseniaIncorrectaException e) {
            fail("No debería lanzar una excepción en este caso: " + e.getMessage());
        }
    }

    @Test
    public void testAltaUsuarioProveedorConNicknameRepetido() {
        // Instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 06, 1978);

        // Se agrega un proveedor con un nickname existente
        try {
            sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
        } catch (UsuarioRepetidoException | ContraseniaIncorrectaException e) {
            fail("No debería lanzar una excepción al registrar el primer usuario.");
        }
        
        // Intentar registrar otro usuario con el mismo nickname
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioProveedor("nickname1", "email2@gmail.com", "nombredos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "12345678");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el nickname \"nickname1\".", thrown.getMessage());
    }

    @Test
    public void testAltaUsuarioProveedorConEmailRepetido() {
        // Instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 06, 1978);

        // Se agrega un proveedor con un email existente
        try {
            sis.altaUsuarioProveedor("nickname1", "email1@gmail.com", "nombreuno", "apellidouno", fecha, "CompaniaUno", "https://www.uno.com", null, "12345678", "12345678");
        } catch (UsuarioRepetidoException | ContraseniaIncorrectaException e) {
            fail("No debería lanzar una excepción al registrar el primer usuario.");
        }

        // Intentar registrar otro usuario con el mismo email
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioProveedor("nickname2", "email1@gmail.com", "nombreDos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "12345678");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el email \"email1@gmail.com\".", thrown.getMessage());
    }

    @Test
    public void testAltaUsuarioProveedorConContrasenaIncorrecta() {
        // Instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 06, 1978);

        // Intentar registrar un proveedor con contraseñas que no coinciden
        ContraseniaIncorrectaException thrown = assertThrows(ContraseniaIncorrectaException.class, () -> {
            sis.altaUsuarioProveedor("nickname2", "email2@gmail.com", "nombreDos", "apellidodos", fecha, "CompaniaDos", "https://www.dos.com", null, "12345678", "87654321");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Las contraseñas no coinciden.", thrown.getMessage());
    }
}
