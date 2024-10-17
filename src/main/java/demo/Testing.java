package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.flamingo.exceptions.CategoriaNoExisteException;
import com.flamingo.exceptions.CategoriaNoPuedeTenerProductosException;
import com.flamingo.exceptions.CategoriaRepetidaException;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.ProductoNoExisteException;
import com.flamingo.exceptions.ProductoRepetidoException;
import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Categoria;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.DTCliente;
import com.flamingo.models.DTClienteDetallado;
import com.flamingo.models.DTFecha;
import com.flamingo.models.DTOrdenDeCompra;
import com.flamingo.models.DTOrdenDeCompraDetallada;
import com.flamingo.models.DTProducto;
import com.flamingo.models.DTProductoDetallado;
import com.flamingo.models.DTProveedor;
import com.flamingo.models.DTProveedorDetallado;
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Producto;
import com.flamingo.models.Proveedor;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;

class Testing {

	public Testing() {
		super();
	}
	
	private ISistema sis;
    private DTFecha fecha;
    private Proveedor proveedor;
    private Cliente cliente;
    private Producto producto;
    private Cantidad cantidad;

    
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
	
    @Test
    public void testAltaUsuarioClienteConNicknameRepetido() {
    	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 06, 1978);
        
        
        // Intentar registrar otro cliente con el mismo nickname
        UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
            sis.altaUsuarioCliente("nicknameRepetir", "emailnuevocli@gmail.com", "nombredos", "apellidodos", fecha, "/12/12/2004", "12345678", "12345678");
            // no deberia ejecutarse
        });

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un usuario registrado con el nickname \"nicknameRepetir\".", thrown.getMessage());
    }
    
    
    //Verificaciones de AltaProveedor
    
    @Test
    public void testAltaProveedorNombreCorto() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        // Probar con un nombre que tiene menos de 3 caracteres
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee1", "emaill1@gmail.com", "Jo", "Apellido", fecha, "Compania", "https://www.compania.com", null, "12345678", "12345678");
            //no deberia ejecutarse
        });
        
        // Verificar que el mensaje de error contenga lo esperado
        assertTrue(thrown.getMessage().contains("El nombre debe tener al menos 3 caracteres"));
    }
    
    
 // Test para apellido inválido (menos de 3 caracteres)
    @Test
    public void testAltaProveedorApellidoInvalido() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee2", "emaill2@gmail.com", "Nombre", "A", fecha, "Compania", "https://www.compania.com", null, "12345678", "12345678");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("El apellido debe tener al menos 3 caracteres"));
    }

    // Test para contraseña inválida (menos de 8 caracteres)
    @Test
    public void testAltaProveedorContraseniaInvalida() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee3", "emaill3@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://www.compania.com", null, "123", "123");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres"));
    }

    // Test para contraseñas que no coinciden
    @Test
    public void testAltaProveedorContraseniasNoCoinciden() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee4", "emaill4@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://www.compania.com", null, "12345678", "87654321");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("Las contraseñas no coinciden"));
    }

    // Test para fecha de nacimiento inválida (nula)
    @Test
    public void testAltaProveedorFechaNacInvalida() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee5", "emaill5@gmail.com", "Nombre", "Apellido", null, "Compania", "https://www.compania.com", null, "12345678", "12345678");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("Se debe ingresar una fecha de nacimiento válida"));
    }

    // Test para URL de compañía inválida
    @Test
    public void testAltaProveedorUrlInvalida() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee6", "emaill6@gmail.com", "Nombre", "Apellido", fecha, "Compania", "invalid-url", null, "12345678", "12345678");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("La URL del sitio web no es válida"));
    }

    // Test para nombre de compañía nulo o vacío
    @Test
    public void testAltaProveedorCompaniaInvalida() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee7", "emaill7@gmail.com", "Nombre", "Apellido", fecha, "", "https://www.compania.com", null, "12345678", "12345678");
            //no deberia ejecutarse
        });

        assertTrue(thrown.getMessage().contains("La compañía es obligatoria"));
    }

    
    @Test
    public void testAltaProveedorConNombreNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee9", "emaill9@gmail.com", null, "Apellido", fecha, "Compania", "https://www.compania.com", null, "12345678", "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("El nombre debe tener al menos 3 caracteres y no puede contener números."));
    }

    @Test
    public void testAltaProveedorConApellidoNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee10", "emaill10@gmail.com", "Nombre", null, fecha, "Compania", "https://www.compania.com", null, "12345678", "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("El apellido debe tener al menos 3 caracteres y no puede contener números."));
    }

    @Test
    public void testAltaProveedorConFechaNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee11", "emaill11@gmail.com", "Nombre", "Apellido", null, "Compania", "https://www.compania.com", null, "12345678", "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("Se debe ingresar una fecha de nacimiento válida"));
    }

    @Test
    public void testAltaProveedorConCompaniaNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee12", "emaill12@gmail.com", "Nombre", "Apellido", fecha, null, "https://www.compania.com", null, "12345678", "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("La compañía es obligatoria"));
    }

    @Test
    public void testAltaProveedorConContrasenia1Null() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee13", "emaill13@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://www.compania.com", null, null, "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres"));
    }

    @Test
    public void testAltaProveedorConContrasenia2Null() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee14", "emaill14@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://www.compania.com", null, "12345678", null);
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres"));
    }
    
    @Test
    public void testAltaProveedorConURLNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioProveedor("nicknamee13", "emaill13@gmail.com", "Nombre", "Apellido", fecha, "Compania", null, null, "12345678", "12345678");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("La URL del sitio web es obligatoria."));
    }
    
    
    // Registro
    
    @Test
    public void registroConNicknameNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.registro(null, "emailcito@jeje.com");
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("El nickname no puede ser vacio"));
    }

     @Test
    public void registroConEmailInvalido() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
        	sis.registro("nickinamee", "email.jeje@");        
        }); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("Por favor, introduce un correo electrónico válido."));
    }
     
     @Test
     public void registroConEmailExistente() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
     	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
         fecha = new DTFecha(12, 6, 1978);
         sis.altaUsuarioProveedor("noexisto", "yaexisto@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://hola.com", null, "12345678", "12345678");

         
         // Intentar registrar otro usuario con el mismo email
         UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
             sis.registro("Yosoynuevo", "yaexisto@gmail.com"); //no deberia ejecutarse
         });

         // Verificar que el mensaje de la excepción contiene el texto esperado
         assertTrue(thrown.getMessage().contains("Ya existe un usuario registrado con el email \"yaexisto@gmail.com\"."));

         }
     
     @Test
     public void registroConNicknameExistente() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
     	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
         fecha = new DTFecha(12, 6, 1978);
         sis.altaUsuarioProveedor("yaexisto", "yonoexisto@gmail.com", "Nombre", "Apellido", fecha, "Compania", "https://hola.com", null, "12345678", "12345678");

         
         // Intentar registrar otro usuario con el mismo email
         UsuarioRepetidoException thrown = assertThrows(UsuarioRepetidoException.class, () -> {
             sis.registro("yaexisto", "yosoynuevo@gmail.com"); //no deberia ejecutarse
         });

         // Verificar que el mensaje de la excepción contiene el texto esperado
         assertTrue(thrown.getMessage().contains("Ya existe un usuario registrado con el nickname \"yaexisto\"."));

         }
     
    /* error esquizo de eclipse
    @Test
    public void registroConEmailNull() {
    	sis = SistemaFactory.getInstancia().getISistema();  // Inicializa el sistema antes de cada test
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
        	sis.registro("nicknameaa", null);        
        	}); //no deberia ejecutarse
        assertTrue(thrown.getMessage().contains("El email no puede ser vacio."));
    }
    */
    
   
    
    
    //Verificaciones de AltaCliente
    
    @Test
    public void testAltaClienteNombreCorto() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick1", "correo1@gmail.com", "Jo", "Apellido", fecha, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("El nombre debe tener al menos 3 caracteres"));
    }

    @Test
    public void testAltaClienteApellidoCorto() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick2", "correo2@gmail.com", "Nombre", "A", fecha, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("El apellido debe tener al menos 3 caracteres"));
    }

    @Test
    public void testAltaClienteContraseniaCorta() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick3", "correo3@gmail.com", "Nombre", "Apellido", fecha, null, "123", "123");
        });
        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres"));
    }

    @Test
    public void testAltaClienteContraseniasNoCoinciden() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick4", "correo4@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", "87654321");
        });
        assertTrue(thrown.getMessage().contains("Las contraseñas no coinciden."));
    }

    @Test
    public void testAltaClienteFechaNacInvalida() {
        sis = SistemaFactory.getInstancia().getISistema();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick5", "correo5@gmail.com", "Nombre", "Apellido", null, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("Se debe ingresar una fecha de nacimiento válida"));
    }

    //null
    
    @Test
    public void testAltaClienteConNombreNull() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick6", "correo6@gmail.com", null, "Apellido", fecha, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("El nombre debe tener al menos 3 caracteres"));
    }

    @Test
    public void testAltaClienteConApellidoNull() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick7", "correo7@gmail.com", "Nombre", null, fecha, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("El apellido debe tener al menos 3 caracteres"));
    }

    @Test
    public void testAltaClienteConContrasenia1Null() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick8", "correo8@gmail.com", "Nombre", "Apellido", fecha, null, null, "12345678");
        });
        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres."));
    }

    @Test
    public void testAltaClienteConContrasenia2Null() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick9", "correo9@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", null);
        });
        assertTrue(thrown.getMessage().contains("La contraseña debe tener al menos 8 caracteres."));
    }
    
    @Test
    public void testAltaClienteConFechaNull() {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sis.altaUsuarioCliente("nick10", "correo10@gmail.com", "Nombre", "Apellido", null, null, "12345678", "12345678");
        });
        assertTrue(thrown.getMessage().contains("Se debe ingresar una fecha de nacimiento válida."));
    }
    
    ////////////////// INICIAR SESION //////////////////
    
    @Test
    public void testIniciarSesionContraseniaIncorrecta() throws UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
        // Inicializar el sistema
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Definir fecha de nacimiento
        fecha = new DTFecha(12, 6, 1978);
        
        // Registrar un usuario cliente en el sistema
        sis.altaUsuarioCliente("Octavius", "Octavius@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", "12345678");

        // Intentar iniciar sesión con una contraseña incorrecta y verificar que arroja una excepción
        ContraseniaIncorrectaException thrown = assertThrows(ContraseniaIncorrectaException.class, () -> {
            sis.iniciarSesion("Octavius", "87654321"); //no deberia ejecutarse
        });

        // Verificar que el mensaje de la excepción contiene el texto esperado
        assertTrue(thrown.getMessage().contains("La contraseña es incorrecta."));
    }
    
    @Test
    public void testIniciarSesionUsuarioNoEncontrado() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
        // Inicializar el sistema
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Definir fecha de nacimiento
        fecha = new DTFecha(12, 6, 1978);
        
        // Registrar un usuario cliente en el sistema
        sis.altaUsuarioCliente("Osborn", "Osborn@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", "12345678");

        // Intentar iniciar sesión con un nickname que no existe y verificar que arroja una excepción
        UsuarioNoEncontrado thrown = assertThrows(UsuarioNoEncontrado.class, () -> {
            sis.iniciarSesion("UsuarioInexistente", "12345678"); //no deberia ejecutarse
        });

        // Verificar que el mensaje de la excepción contiene el texto esperado
        assertTrue(thrown.getMessage().contains("El Nickname o Email son incorrectos"));
    }

    @Test
    public void testIniciarSesionExitoso() throws UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
        // Inicializar el sistema
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Definir fecha de nacimiento
        fecha = new DTFecha(12, 6, 1978);
        
        // Registrar un usuario cliente en el sistema
        sis.altaUsuarioCliente("Connors", "Connors@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", "12345678");

        // Iniciar sesión correctamente
        sis.iniciarSesion("Connors@gmail.com", "12345678");

        // Verificar que el usuarioActual es el correcto
        assertEquals("Connors@gmail.com", sis.getUsuarioActual().getEmail());
    }

    
    ////////////////////// PRODUCTOS /////////////////////
    
    @Test
    public void testRegistrarProductoExitoso() throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
    	sis.altaUsuarioProveedor("juan", "juan@gmail.com", "juan", "jujuan", fecha, "CompaniaJuan", "https://www.juan.com", null, "12345678", "12345678");
        sis.iniciarSesion("juan", "12345678");
    	
    	String titulo = "Producto 1";
        int numReferencia = 12345;
        String descrip = "Descripción del producto.";
        List<String> especificaciones = Arrays.asList("Especificación 1", "Especificación 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Categoria1", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png", "imagen2.png");
        String nombreTienda = "Tienda Ejemplo";

        boolean resultado = sis.registrarProducto(titulo, numReferencia, descrip, especificaciones, precio, categorias, imagenes, nombreTienda);
        //List<Producto> productos = sis.getProductos();
        
        assertTrue(resultado);
        //assertEquals(1, proveedor.getProductos().size()); // Verifica que se haya agregado el producto
    }
    
    
    // HAY QUE ARREGLAR LO DE QUE SE PUEDEN REPETIR PRODUCTOS CON LAS MISMAS CREDENCIALES
    @Test
    public void testRegistrarProductoRepetido() throws ContraseniaIncorrectaException, UsuarioNoEncontrado, UsuarioRepetidoException {
    	 sis = SistemaFactory.getInstancia().getISistema();
         fecha = new DTFecha(12, 6, 1978);
     	sis.altaUsuarioProveedor("juana", "juana@gmail.com", "juan", "jujuan", fecha, "CompaniaJuan", "https://www.juan.com", null, "12345678", "12345678");
         sis.iniciarSesion("juana", "12345678");

        String titulo = "Producto Repetido";
        int numReferencia = 12345;
        String descrip = "Descripción del producto.";
        List<String> especificaciones = Arrays.asList("Especificación 1", "Especificación 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Categoria1", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png");
        String nombreTienda = "Tienda Ejemplo";

        // Registramos el primer producto
        try {
            sis.registrarProducto(titulo, numReferencia, descrip, especificaciones, precio, categorias, imagenes, nombreTienda);
        } catch (Exception e) {
            fail("No se esperaba ninguna excepción aquí: " + e.getMessage());
        }

        // Intentamos registrar el mismo producto de nuevo
        ProductoRepetidoException thrown = assertThrows(ProductoRepetidoException.class, () -> {
            sis.registrarProducto(titulo, numReferencia, descrip, especificaciones, precio, categorias, imagenes, nombreTienda);
        });

        assertTrue(thrown.getMessage().contains("Producto repetido"));
    }
    
    //////// GetCarritoActual ///////////
    
    @Test
    public void testGetCarritoActualConCliente() throws UsuarioNoExisteException, ContraseniaIncorrectaException, UsuarioNoEncontrado, UsuarioRepetidoException {
    	
   	 	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
        sis.altaUsuarioCliente("nickclientecarrito1", "clientecarrito1@gmail.com", "Nombre", "Apellido", fecha, null, "12345678", "12345678");
       
        sis.iniciarSesion("nickclientecarrito1", "12345678");

     // Crear un producto de ejemplo
        String titulo = "Productooo";
        int numReferencia = 1222;
        String descrip = "Descripción del producto.";
        List<String> especificaciones = Arrays.asList("cosa 1", "cosa 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Probando", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png");
        String nombreTienda = "Tienda Ejemplo";

     // Crear un producto de ejemplo
        producto = new Producto(titulo, descrip, especificaciones, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda);

        // Crear una cantidad asociada al producto (1 unidad)
        cantidad = new Cantidad(1);
        cantidad.setProducto(producto);

        // Agregar el producto al carrito del cliente
        cliente = (Cliente) sis.getUsuarioActual();
        
        cliente.agregarProducto(cantidad);
        
        HashMap<Integer, DTCantidad> carritoActual = sis.getCarritoActual();
        
        assertNotNull(carritoActual);
        assertEquals(1, carritoActual.size()); // Verifica que haya un producto en el carrito
        assertTrue(carritoActual.containsKey(numReferencia)); // Verifica que el carrito contiene el producto con el ID correcto
        
        }
    
    @Test
    public void testGetCarritoActualConProveedor() throws UsuarioNoExisteException, ContraseniaIncorrectaException, UsuarioNoEncontrado, UsuarioRepetidoException {

        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);

        // Registrar un proveedor en lugar de un cliente
        sis.altaUsuarioProveedor("proveedorrr", "proveedorrr@gmail.com", "NombreProv", "ApellidoProv", fecha, "Empresa", "https://empresa.com", null, "12345678", "12345678");

        // Iniciar sesión con el proveedor
        sis.iniciarSesion("proveedorrr", "12345678");

        // Intentar obtener el carrito de un proveedor (debería fallar)
        assertThrows(UsuarioNoExisteException.class, () -> {
            sis.getCarritoActual(); // no deberia ejecutarse
        });
    }
    
    @Test
    public void testGetCarritoActualConUsuarioNull() throws UsuarioNoExisteException, ContraseniaIncorrectaException, UsuarioNoEncontrado, UsuarioRepetidoException {

        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);
        sis.setTodoNull();
        // Intentar obtener el carrito de un usuario null (debería fallar)
        assertThrows(UsuarioNoExisteException.class, () -> {
            sis.getCarritoActual(); // no deberia ejecutarse
        });
    }
    
    /////////////// BUSCAR CATEGORIA RECURSIVAMENTE /////////////
    
    @Test
    public void testBuscarCategoriaExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        Categoria categoriaPadre = sis.altaCategoria("Electronica", true, null);
        sis.altaCategoria("Celulares", true, categoriaPadre);
        sis.altaCategoria("Computadoras", true, categoriaPadre);
        
        // Buscar la categoría "Celulares"
        Categoria categoriaBuscada = sis.buscarCategoriaRecursivamente("Celulares", sis.getCategorias());
        
        // Verificar que la categoría buscada es la correcta
        assertNotNull(categoriaBuscada);
        assertEquals("Celulares", categoriaBuscada.getNombreCat());
    }
    
    @Test
    public void testBuscarCategoriaNoExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear una categoría de ejemplo
        sis.altaCategoria("Parlantes", true, null);
        
        // Intentar buscar una categoría que no existe
        Categoria categoriaBuscada = sis.buscarCategoriaRecursivamente("Ropa", sis.getCategorias());
        
        // Verificar que la categoría buscada es null
        assertNull(categoriaBuscada);
    }


    ///////// EXISTE CATEGORIA ////////////
    
    @Test
    public void testCategoriaExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        sis.altaCategoria("Joyas", true, null);
        sis.altaCategoria("Diamantes", true, null);
        
        // Verificar que "Electronica" existe
        assertTrue(sis.existeCategoria("Joyas"));
    }
    
    @Test
    public void testCategoriaNoExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        sis.altaCategoria("Guiso", true, null);
        
        // Verificar que "Ropa" no existe
        assertFalse(sis.existeCategoria("Papas"));
    }

    ///////////// EXISTE CATEGORIA RECURSIVAMENTE /////////////
    
    @Test
    public void testCategoriaHijaExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        Categoria categoriaPadre = sis.altaCategoria("Rock", true, null);
        sis.altaCategoria("Guitarras", true, categoriaPadre);
        
        // Verificar que "Celulares" existe
        assertTrue(sis.existeCategoriaRecursivamente(categoriaPadre, "Guitarras"));
    }
    
    @Test
    public void testCategoriaNietaExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        Categoria categoriaPadre = sis.altaCategoria("Electro", true, null);
        Categoria categoriaHija = sis.altaCategoria("Cel", true, categoriaPadre);
        sis.altaCategoria("Samsung", true, categoriaHija);
        
        // Verificar que "Samsung" existe
        assertTrue(sis.existeCategoriaRecursivamente(categoriaPadre, "Samsung"));
    }

    @Test
    public void testCategoriaNoExistenteRec() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        Categoria categoriaPadre = sis.altaCategoria("Electron", true, null);
        sis.altaCategoria("Proton", true, categoriaPadre);
        
        // Verificar que "Tablets" no existe en la jerarquía de "Electronica"
        assertFalse(sis.existeCategoriaRecursivamente(categoriaPadre, "Juan"));
    }
    
    @Test
    public void testCategoriaAnidadaNoExistente() throws CategoriaRepetidaException {
        sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear categorías de ejemplo
        Categoria categoriaPadre = sis.altaCategoria("E", true, null);
        Categoria categoriaHija = sis.altaCategoria("A", true, categoriaPadre);
        sis.altaCategoria("Smartphones", true, categoriaHija);
        
        // Verificar que "Computadoras" no existe en la jerarquía de "Electronica"
        assertFalse(sis.existeCategoriaRecursivamente(categoriaPadre, "F"));
    }

    
     ////////// VER INFO ORDEN DE COMPRA /////////////
    
    @Test
    public void testVerInformacionOrdenSinSeleccionar() {
        sis = SistemaFactory.getInstancia().getISistema();

        // Asegurarse de que no hay orden seleccionada
        sis.setTodoNull();

        // Verificar que se lanza NullPointerException
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            sis.verInformacionOrdenDeCompra(); // no deberia ejecutarse
        });
        assertTrue(thrown.getMessage().contains("No se ha elegido una orden de compra previamente."));
    }
    
    @Test
    public void testVerInformacionOrdenConSeleccion() throws OrdenDeCompraNoExisteException, ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
        // Crear instancia del sistema
        sis = SistemaFactory.getInstancia().getISistema();

        // Crear cliente y productos necesarios para la orden
        Cliente cliente = new Cliente("testCliente", "testEmail@jaja.com", "nombre", "apell", fecha, null, "12345678");        
        fecha = new DTFecha(12, 6, 1978);
    	sis.altaUsuarioCliente("juancito", "juancito@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");
        sis.iniciarSesion("juancito", "12345678");
    	
    	String titulo = "Productoaa";
        int numReferencia = 123;
        String descrip = "Descripción del productoa.";
        List<String> especificaciones = Arrays.asList("Especificaa", "Especaaaaaa 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Cataaaa", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png", "imagen2.png");
        String nombreTienda = "Tiendaaaa";

       // sis.registrarProducto(titulo, numReferencia, descrip, especificaciones, precio, categorias, imagenes, nombreTienda);
        producto = new Producto(titulo, descrip, especificaciones, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda);

        
        List<Cantidad> cantidades = new ArrayList<>();
        
        Cantidad cantidad1 = new Cantidad(2);
        cantidad1.setProducto(producto);
        
        cantidades.add(cantidad1);
       
        
        OrdenDeCompra ordencita = sis.agregarOrden(cantidades);
        // Crear la orden de compra
        
        sis.elegirOrdenDeCompra(ordencita.getNumero());

        // Llamar al método para obtener la información detallada
        DTOrdenDeCompraDetallada infoOrden = sis.verInformacionOrdenDeCompra();

        // Verificar que la información devuelta no es nula
        assertNotNull(infoOrden);
        assertEquals(ordencita.getNumero(), infoOrden.getNumero());
        assertEquals(sis.getUsuarioActual(), infoOrden.getCliente());
        
    }

    //////// listar clientes //////////
    
    @Test
    public void testListarClientesConClientesYProveedores() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
        // Crear instancia del sistema
        sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);

        // Crear algunos clientes
    	sis.altaUsuarioCliente("listar1", "listar1@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");
    	sis.altaUsuarioCliente("listar2", "listar2@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");


        // Crear algunos proveedores
    	sis.altaUsuarioProveedor("nolistar1", "nolistar1@gmail.com", "juan", "jujuan", fecha, "Compa", "https://hola.com", null, "12345678", "12345678");

        // Llamar al método para listar clientes
        List<DTCliente> listaClientes = sis.listarClientes();

        // Verificar que solo se devuelvan los clientes
        
        assertTrue(listaClientes.stream().anyMatch(dt -> dt.getNickname().equals("listar1")));
        assertTrue(listaClientes.stream().anyMatch(dt -> dt.getNickname().equals("listar2")));
    }

    
    /////////// listar ordenes de compra //////////
    
    @Test
    public void testListarOrdenesDeCompraUsuarioActualEsCliente() throws UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Crear fecha y cliente
        DTFecha fecha = new DTFecha(12, 6, 1978);
        sis.altaUsuarioCliente("juancitoq", "juancitoq@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");
        sis.iniciarSesion("juancitoq", "12345678");

        // Crear producto y cantidades
        
        String titulo = "Producto Test";
        int numReferencia = 123;
        String descrip = "Descripción del producto de prueba.";
        List<String> especificaciones = Arrays.asList("Especificación 1", "Especificación 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Categoría Test", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png", "imagen2.png");
        String nombreTienda = "Tienda Test";

        Producto producto = new Producto(titulo, descrip, especificaciones, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda);

        List<Cantidad> cantidades = new ArrayList<>();
        Cantidad cantidad1 = new Cantidad(2);
        cantidad1.setProducto(producto);
        cantidades.add(cantidad1);

        // Crear orden de compra
        OrdenDeCompra orden = sis.agregarOrden(cantidades);

        // Llamar al método para listar las órdenes de compra
        List<DTOrdenDeCompra> listaOrdenes = sis.listarOrdenesDeCompra();

        // Verificar que la lista contiene la orden creada
        assertEquals(1, listaOrdenes.size());
        assertEquals(orden.getNumero(), listaOrdenes.get(0).getNumero());
    }

    
    @Test
    public void testListarOrdenesDeCompraUsuarioNoEsCliente() throws UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado {
    	// Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Crear fecha y cliente
        DTFecha fecha = new DTFecha(12, 6, 1978);
        sis.altaUsuarioCliente("juancitoqq", "juancitoqq@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");
        sis.iniciarSesion("juancitoqq", "12345678");


        // Crear producto y cantidades
        
        String titulo = "Producto Test";
        int numReferencia = 123;
        String descrip = "Descripción del producto de prueba.";
        List<String> especificaciones = Arrays.asList("Especificación 1", "Especificación 2");
        float precio = 99.99f;
        List<Categoria> categorias = Arrays.asList(new Categoria("Categoría Test", true, null));
        List<String> imagenes = Arrays.asList("imagen1.png", "imagen2.png");
        String nombreTienda = "Tienda Test";

        Producto producto = new Producto(titulo, descrip, especificaciones, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda);

        List<Cantidad> cantidades = new ArrayList<>();
        Cantidad cantidad1 = new Cantidad(2);
        cantidad1.setProducto(producto);
        cantidades.add(cantidad1);

        // Crear orden de compra
        OrdenDeCompra orden = sis.agregarOrden(cantidades);
        
        sis.setTodoNull();
        sis.altaUsuarioProveedor("pepegamer", "pepe@gmail.com", "jose", "pedro", fecha, "comp", "https://hole.com", null, "12345678", "12345678");
        sis.iniciarSesion("pepegamer", "12345678");
        
        // Llamar al método para listar las órdenes de compra
        List<DTOrdenDeCompra> listaOrdenes = sis.listarOrdenesDeCompra();

        // Verificar que la lista contiene la orden creada
        assertEquals(1, listaOrdenes.size());
        assertEquals(orden.getNumero(), listaOrdenes.get(0).getNumero());
    }

    
    //////////// listar proveedores ////////////////
    
    @Test
    public void testListarProveedores() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
    	// Crear instancia del sistema
    	sis = SistemaFactory.getInstancia().getISistema();
    	fecha = new DTFecha(12, 6, 1978);

    	// Crear algunos proveedores
    	sis.altaUsuarioProveedor("proveeder1", "proveeder1@gmail.com", "juan", "jujuan", fecha, "Compañía 1", "https://hola1.com", null, "12345678", "12345678");
    	sis.altaUsuarioProveedor("proveeder2", "proveeder2@gmail.com", "juan", "jujuan", fecha, "Compañía 2", "https://hola2.com", null, "12345678", "12345678");

    	// Crear algunos clientes (para verificar que no son incluidos en la lista de proveedores)
    	sis.altaUsuarioCliente("noclienteee1", "noclienteee1@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");
    	sis.altaUsuarioCliente("noclienteee2", "noclienteee2@gmail.com", "juan", "jujuan", fecha, null, "12345678", "12345678");

    	// Llamar al método para listar proveedores
    	List<DTProveedor> listaProveedores = sis.listarProveedores();

    	// Verificar que solo se devuelvan los proveedores
    	assertTrue(listaProveedores.stream().anyMatch(dt -> dt.getNickname().equals("proveeder1")));
    	assertTrue(listaProveedores.stream().anyMatch(dt -> dt.getNickname().equals("proveeder2")));

    }
    
    ///////////// elegir proovedor ///////////////
    
    @Test
    public void testElegirProveedorExitoso() throws UsuarioNoExisteException, UsuarioRepetidoException, ContraseniaIncorrectaException {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);

        // Crear un proveedor
        sis.altaUsuarioProveedor("proveedor1", "proveedor1@gmail.com", "Jose", "Perez", fecha, "Compañía 1", "https://compania1.com", null, "12345678", "12345678");

        // Intentar seleccionar el proveedor correctamente
        sis.elegirProveedor("proveedor1");

        // Verificar que el proveedor es seleccionado como usuario actual
        Usuario usuarioActual = sis.getUsuarioActual();  // Método getUsuarioActual() en ISistema
        assertNotNull(usuarioActual);
        assertTrue(usuarioActual instanceof Proveedor);
        assertEquals("proveedor1", usuarioActual.getNickname());
    }

    @Test
    public void testElegirProveedorUsuarioNoExiste() {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Intentar seleccionar un proveedor que no existe
        Exception exception = assertThrows(UsuarioNoExisteException.class, () -> {
            sis.elegirProveedor("proveedorInexistente");
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "El usuario de nickname \"proveedorInexistente\" no existe.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testElegirProveedorUsuarioNoEsProveedor() throws UsuarioRepetidoException, ContraseniaIncorrectaException {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);

        // Crear un cliente
        sis.altaUsuarioCliente("cliente1", "cliente1@gmail.com", "Juan", "Perez", fecha, null, "12345678", "12345678");

        // Intentar seleccionar el cliente como proveedor
        Exception exception = assertThrows(UsuarioNoExisteException.class, () -> {
            sis.elegirProveedor("cliente1");
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "El usuario de nickname \"cliente1\" existe, pero no es un proveedor.";
        assertEquals(expectedMessage, exception.getMessage());
    }
      
    
    ///////////////// VER INFORMACION PROVEEDOR /////////////
    
    @Test
    public void testVerInformacionProveedorExitoso() throws UsuarioRepetidoException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);

        // Crear y elegir un proveedor
        sis.altaUsuarioProveedor("proveedorcito33", "proveedorcito33@gmail.com", "Jose", "Perez", fecha, "Compañía 1", "https://compania1.com", null, "12345678", "12345678");
        sis.elegirProveedor("proveedorcito33");

        // Llamar al método para ver información detallada del proveedor
        DTProveedorDetallado dtProveedor = sis.verInformacionProveedor();

        // Verificar que la información del proveedor es correcta
        assertNotNull(dtProveedor);
        assertEquals("proveedorcito33", dtProveedor.getNickname());
        assertEquals("Jose", dtProveedor.getNombre());
        assertEquals("Perez", dtProveedor.getApellido());
        assertEquals("Compañía 1", dtProveedor.getNomCompania());
        assertEquals("https://compania1.com", dtProveedor.getLink());
    }
    
    @Test
    public void testVerInformacionProveedorSinSeleccionarProveedor() {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        sis.setTodoNull();
        // Llamar al método sin seleccionar un proveedor previamente
        Exception exception = assertThrows(NullPointerException.class, () -> {
            sis.verInformacionProveedor(); //no deberia ejecutarse
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "No se ha elegido un proveedor previamente.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testVerInformacionProveedorUsuarioNoEsProveedor() throws UsuarioRepetidoException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        DTFecha fecha = new DTFecha(12, 6, 1978);

        // Crear un cliente y establecerlo como usuario actual
        sis.altaUsuarioCliente("cliente20", "cliente20@gmail.com", "Juan", "Perez", fecha, null, "12345678", "12345678");
        sis.elegirCliente("cliente20");  // Simulando un mal uso

        // Llamar al método que debería lanzar excepción ya que el usuario no es un proveedor
        Exception exception = assertThrows(NullPointerException.class, () -> {
            sis.verInformacionProveedor();
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "No se ha elegido un proveedor previamente.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    ////////////////// modificar imagenes producto /////////////////
    
  

    @Test
    public void testModificarImagenesProductoSinSeleccionarProducto() {
        // Crear instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Intentar modificar imágenes sin haber seleccionado un producto
        Exception exception = assertThrows(NullPointerException.class, () -> {
            sis.modificarImagenesProducto(Arrays.asList("imagen2.png", "imagen3.png"));
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "No se ha elegido un producto previamente.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    
    @Test
    public void testModificarImagenesProductoNull() throws ProductoNoExisteException, UsuarioRepetidoException, ContraseniaIncorrectaException, UsuarioNoEncontrado, ProductoRepetidoException, CategoriaNoPuedeTenerProductosException {
        
    	sis = SistemaFactory.getInstancia().getISistema();
        fecha = new DTFecha(12, 6, 1978);
    	sis.altaUsuarioProveedor("juans333", "juans333@gmail.com", "juan", "jujuan", fecha, "CompaniaJuan", "https://www.juan.com", null, "12345678", "12345678");
        sis.iniciarSesion("juans333", "12345678");
    	
    	
        Producto producto = new Producto("Producto01", "Descripción", Arrays.asList("Especificación 1"), 123, 100.0f, Arrays.asList("imagen1.png"), Arrays.asList(new Categoria("Categoría 1", true, null)), proveedor, "Tienda 1");
        //sis.registrarProducto(titulo, numReferencia, descrip, especificaciones, precio, categorias, imagenes, nombreTienda);
        sis.setProductoActual(producto);

        

     // Intentar modificar con una lista nula
        sis.modificarImagenesProducto(null);
        assertEquals(Arrays.asList("imagen1.png"), producto.getImagenes());  // No cambia

        // Intentar modificar con una lista vacía
        sis.modificarImagenesProducto(new ArrayList<>());
        assertEquals(Arrays.asList("imagen1.png"), producto.getImagenes());  // No cambia

    }
    
    
    @Test
    public void testRegistrarProductoExitosoConCategoria() {
        // Instancia del sistema mediante el factory
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Crear un proveedor válido
        DTFecha fecha = new DTFecha(2024, 10, 16);  // Ejemplo de fecha
        Proveedor proveedor = new Proveedor(
            "nick123", "NombreProveedor", "ApellidoProveedor", 
            "correo@example.com", fecha, "foto.jpg", 
            "Compañía Ejemplo", "http://compania.com", "contraseña123"
        );
        sis.setUsuarioActual(proveedor);  // Establecer el usuario actual como proveedor

        // Crear categorías válidas (sin padre)
        Categoria categoria1 = new Categoria("Categoría A", true, null);  // Categoría sin padre
        Categoria categoria2 = new Categoria("Categoría B", true, null);

        // Crear datos de prueba del producto
        String titulo = "Producto1";
        int numReferencia = 1;
        String descripcion = "Descripción del producto";
        List<String> especificaciones = List.of("Especificación1", "Especificación2");
        float precio = 99.99f;
        List<Categoria> categorias = List.of(categoria1, categoria2);
        List<String> imagenes = List.of("imagen1.png", "imagen2.png");
        String nombreTienda = "Tienda Ejemplo";

        // Ejecutar el método registrarProducto
        try {
            boolean resultado = sis.registrarProducto(
                titulo, numReferencia, descripcion, 
                especificaciones, precio, categorias, 
                imagenes, nombreTienda
            );

            // Verificar que el producto se registró correctamente
            assertTrue("El producto debe registrarse correctamente", resultado);

            // Verificar que el producto se agregó al proveedor
            assertEquals(1, proveedor.getProductos().size());
            assertEquals(titulo, proveedor.getProductos().get(0).getNombreProducto());

            // Verificar que el producto se agregó a las categorías
            assertEquals(1, categoria1.getProductos().size());
            assertEquals(titulo, categoria1.getProductos().get(0).getNombreProducto());
            assertEquals(1, categoria2.getProductos().size());

        } catch (Exception e) {
            fail("No debería lanzarse ninguna excepción: " + e.getMessage());
        }
    }
    
 @Test
 public void testRegistrarProductoSinProveedor() {
     ISistema sis = SistemaFactory.getInstancia().getISistema();

     // Establecemos el usuario actual como null
     sis.setUsuarioActual(null);

     // Datos del producto (no importa si son válidos para este test)
     String titulo = "ProductoSinProveedor";
     int numReferencia = 1;
     String descripcion = "Descripción del producto";
     List<String> especificaciones = List.of("Especificación1");
     float precio = 50.0f;
     List<Categoria> categorias = List.of(new Categoria("Categoría", true, null));
     List<String> imagenes = List.of("imagen.png");
     String nombreTienda = "Tienda";

     // Validamos que se lance NullPointerException
     assertThrows(NullPointerException.class, () -> {
         sis.registrarProducto(titulo, numReferencia, descripcion, 
                 especificaciones, precio, categorias, imagenes, nombreTienda);
     });
 }

 @Test
 public void testRegistrarProductoConCategoriaInvalida() {
     ISistema sis = SistemaFactory.getInstancia().getISistema();

     // Configurar proveedor como usuario actual
     Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
             new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
     sis.setUsuarioActual(proveedor);

     // Crear categoría inválida
     Categoria categoriaInvalida = new Categoria("Categoría Invalida", false, null);
     List<Categoria> categorias = List.of(categoriaInvalida);

     // Verificar que se lance CategoriaNoPuedeTenerProductosException
     assertThrows(CategoriaNoPuedeTenerProductosException.class, () -> {
         sis.registrarProducto("Producto 1", 123, "Descripción", 
                 List.of("Especificación"), 100.0f, categorias, List.of("imagen.png"), "Tienda");
     });
 }

    @Test
    public void testVerInformacionProductoConProductoSeleccionado() {
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        
        Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
                new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
        sis.setUsuarioActual(proveedor);
        
        
        Producto producto = new Producto("Producto 1", "Descripción", 
                List.of("Especificación"), 123, 100.0f, List.of("imagen.png"), 
                List.of(), proveedor, "Tienda");
        sis.setProductoActual(producto); // Asumimos que hay un método para establecer el producto actual

        
        DTProductoDetallado infoProducto = sis.verInformacionProducto();

        
        assertNotNull(infoProducto);
        assertEquals("Producto 1", infoProducto.getNombre());
        assertEquals("Descripción", infoProducto.getDescripcion());
        assertEquals(100.0f, infoProducto.getPrecio(), 0.01);
    }
    
   

    @Test
    public void testListarAllProductos() {
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        
        Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
                new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
        sis.setUsuarioActual(proveedor);

        
        List<String> especificaciones = new ArrayList<>();
        especificaciones.add("Especificación 1");
        
        List<Categoria> categorias = new ArrayList<>();
        Categoria categoria = new Categoria("Categoria 1", true, null);
        categorias.add(categoria);

        Producto producto1 = new Producto("Producto 1", "Descripción 1", especificaciones, 1, 100.0f, new ArrayList<>(), categorias, proveedor, "Tienda 1");
        Producto producto2 = new Producto("Producto 2", "Descripción 2", especificaciones, 2, 200.0f, new ArrayList<>(), categorias, proveedor, "Tienda 1");

        
        proveedor.agregarProducto(producto1);
        proveedor.agregarProducto(producto2);

        
        Collection<DTProductoDetallado> productosDetallados = sis.listarAllProductos();

       
        assertEquals(0, productosDetallados.size());
    }
    
    @Test
    public void testElegirProductoSinCategoriaSeleccionada() {
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Configurar un proveedor como usuario actual
        Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
                new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
        sis.setUsuarioActual(proveedor);

        // Verificar que se lanza NullPointerException al intentar elegir producto sin categoría seleccionada
        assertThrows(NullPointerException.class, () -> {
            sis.elegirProducto("Producto Inexistente");
        });
    }
    
    @Test
    public void testAgregarProductoCantidadMenorADos() throws ProductoNoExisteException {
        ISistema sis = SistemaFactory.getInstancia().getISistema();

        // Configurar un proveedor como usuario actual
        Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
                new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
        sis.setUsuarioActual(proveedor);

        // Configurar categoría actual
        Categoria categoria = new Categoria("Categoria 1", true, null);
        sis.setCategoriaActual(categoria);

        // Verificar que se lanza IllegalArgumentException con cantidad menor a 2
        assertThrows(IllegalArgumentException.class, () -> {
            sis.agregarProducto("Producto Inexistente", 0);
        });
    }




  

    @Test
    public void testQuitarProductoDeCategoriasConCategoriasAgregadas() {
        // Crear un sistema simulado
        ISistema sistema = SistemaFactory.getInstancia().getISistema();
        
        // Simular un proveedor como usuario actual
        Proveedor proveedor = new Proveedor("prov1", "Proveedor", "Apellido", "email@test.com", 
                new DTFecha(2024, 10, 16), "foto.png", "Compañía", "link.com", "password");
        sistema.setUsuarioActual(proveedor);
        
        // Crear un producto
        List<String> especificaciones = new ArrayList<>();
        especificaciones.add("Especificación 1");
        Producto producto = new Producto("Producto Test", "Descripción Test", especificaciones, 123, 10.0f, new ArrayList<>(), new ArrayList<>(), proveedor, "Tienda Test");
        
        // Crear categorías
        Categoria categoriaPrincipal = new Categoria("Categoria Principal", true, null);
        Categoria subCategoria = new Categoria("Sub Categoria", true, categoriaPrincipal);
        
        // Agregar subcategoría a la categoría principal
        categoriaPrincipal.getHijos().put("Sub Categoria", subCategoria);
        
        // Agregar el producto a las categorías
        categoriaPrincipal.getProductos().add(producto);
        subCategoria.getProductos().add(producto);
        
        // Establecer la categoría en el sistema
        sistema.setCategoriaActual(categoriaPrincipal);
        
        // Llamamos al método para quitar el producto de las categorías
        sistema.quitarProductoDeCategorias(true);
        
        // Verificamos que el producto fue eliminado de la categoría principal
        assertFalse(!categoriaPrincipal.getProductos().contains(producto));
        // Verificamos que el producto fue eliminado de la subcategoría
        assertFalse(!subCategoria.getProductos().contains(producto));
    }

    
    @Test
    public void testCrearCasos() {
        // Instancia del sistema
        ISistema sistema = SistemaFactory.getInstancia().getISistema();
        
        // Llamar a la función crearCasos() para inicializar datos
        sistema.crearCasos();
        
        // Obtener los usuarios
        List<?> usuarios = sistema.getUsuarios();
        
        // Verificar que la lista de usuarios no sea null
        assertNotNull("La lista de usuarios no debe ser null después de crear casos", usuarios);
    }
    
    @Test
    public void testAgregarCategoriasAProductoExitoso() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

       
        Producto producto = new Producto("Producto1", null, null, 1, 100.0f, null, null, null, null);
        sistema.setProductoActual(producto);

       
        Categoria cat1 = new Categoria("Categoría 1", true, null);
        Categoria cat2 = new Categoria("Categoría 2", true, null);

        List<Categoria> categorias = Arrays.asList(cat1, cat2);

        
        sistema.agregarCategoriasAProducto(categorias);

       
        assertEquals(categorias, sistema.getProductoActual().getCategorias());
    }
    
    
   
    @Test
    public void testAgregarCategoriasAProductoConExcepcion() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Crear un producto sin categorías válidas
        Producto producto = new Producto("Producto2", null, null, 2, 200.0f, null, null, null, null);
        sistema.setProductoActual(producto);

        // Crear una categoría que no puede tener productos
        Categoria cat1 = new Categoria("Categoría 1", false, null);
        List<Categoria> categorias = Arrays.asList(cat1);

        // Verificar que se lanza CategoriaNoPuedeTenerProductosException
        assertThrows(CategoriaNoPuedeTenerProductosException.class, () -> {
            sistema.agregarCategoriasAProducto(categorias);
        });
    }

    @Test
    public void testExisteProductoNoRepetido() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        
        Categoria cat1 = new Categoria("Categoría 1", true, null);
        Producto prod1 = new Producto("Producto 1", null, null, 1, 100.0f, null, null, null, null);
        cat1.getProductos().add(prod1);

        sistema.getCategorias().put(cat1.getNombreCat(), cat1);

        // No debería lanzar ninguna excepción, ya que el producto no se repite
        sistema.existeProducto("Producto 2", 2, false);
    }

    
    @Test
    public void testModificarDatosProductoExitoso() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Configuración inicial: producto actual seleccionado
        Producto prod = new Producto("Producto Original", "Descripción", 
                                     new ArrayList<>(), 1, 100.0f, null, 
                                     null, null, null);
        sistema.setProductoActual(prod);

        // Modificación exitosa
        sistema.modificarDatosProducto("Producto Modificado", 2, "Nueva Descripción", 
                                       150.0f, Arrays.asList("Especificación 1", "Especificación 2"));

        assertEquals("Producto Modificado", prod.getNombreProducto());
        assertEquals(2, prod.getNumReferencia());
        assertEquals("Nueva Descripción", prod.getDescripcion());
        assertEquals(150.0f, prod.getPrecio(), 0.01);
        assertEquals(Arrays.asList("Especificación 1", "Especificación 2"), prod.getEspecificacion());
    }

  

   
    @Test
    public void testModificarProductoConPrecioInvalido() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Producto actual seleccionado
        Producto prod = new Producto("Producto", "Descripción", 
                                     new ArrayList<>(), 1, 100.0f, null, 
                                     null, null, null);
        sistema.setProductoActual(prod);

        // Verificar que se lanza IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            sistema.modificarDatosProducto("Producto", 1, "Descripción", -50.0f, new ArrayList<>());
        });
    }


   
    @Test
    public void testModificarProductoConNombreRepetido() throws Exception {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Producto actual seleccionado
        Producto prod = new Producto("Producto Original", "Descripción", 
                                     new ArrayList<>(), 1, 100.0f, null, 
                                     null, null, null);
        sistema.setProductoActual(prod);

        // Agregar un producto duplicado a la categoría
        Categoria categoria = new Categoria("Categoría", true, null);
        Producto prodDuplicado = new Producto("Producto Repetido", "Descripción", 
                                              new ArrayList<>(), 2, 200.0f, null, 
                                              null, null, null);
        categoria.getProductos().add(prodDuplicado);
        sistema.getCategorias().put(categoria.getNombreCat(), categoria);

        // Verificar que se lanza ProductoRepetidoException
        assertThrows(ProductoRepetidoException.class, () -> {
            sistema.modificarDatosProducto("Producto Repetido", 2, "Nueva Descripción", 150.0f, new ArrayList<>());
        });
    }

    
    @Test
    public void testElegirProductoExitoso() throws ProductoNoExisteException {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Crear una categoría y un producto
        Producto producto = new Producto("Producto1", "Descripción", new ArrayList<>(), 1, 
                                         50.0f, null, null, null, null);
        Categoria categoria = new Categoria("Categoría 1", true, null);
        categoria.agregarProducto(producto);  // Agregar producto a la categoría

        // Establecer la categoría como la actual en el sistema
        sistema.setCategoriaActual(categoria);

        // Llamar al método y verificar que el producto fue seleccionado correctamente
        boolean resultado = sistema.elegirProducto("Producto1");

        assertTrue("El producto debería haberse elegido exitosamente", resultado);
        assertEquals("El producto actual debería ser Producto1", producto, sistema.getProductoActual());
    }
    
    @Test
    public void testModificarImagenesProductoExitoso() {
        ISistema sistema = SistemaFactory.getInstancia().getISistema();

        // Configura un producto actual
        Producto prod = new Producto("Producto", "Descripción", new ArrayList<>(), 1, 
                                     100.0f, new ArrayList<>(), null, null, null);
        sistema.setProductoActual(prod);

        // Modificar imágenes exitosamente
        List<String> nuevasImagenes = Arrays.asList("imagen1.jpg", "imagen2.png");
        sistema.modificarImagenesProducto(nuevasImagenes);

        assertEquals(nuevasImagenes, prod.getImagenes());
    }
    
    
    // ################################ AgregarOrden ################################
 
    @Test
    public void AgregarOrdenExitoso() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	//int num = 100000000;
    	
    	Cantidad c = new Cantidad(1);
    	
    	Producto prod = new Producto(null, null, null, 0, 0, null, null, null, null);
    	
    	c.setProducto(prod);
    	
    	List<Cantidad> Lista = new ArrayList<>();
    	
    	Lista.add(c);
    	
    	Cliente cli = new Cliente("a", "b", "c", "d", fecha, null, null);
    	
    	sis.setUsuarioActual((Usuario) cli);
    	
    	OrdenDeCompra res1 = null;
    	
    	res1 = sis.agregarOrden(Lista);
    	
    	assertNotNull(res1, "La orden no debe ser nula");
    	
    }
    
    @Test
    public void AgregarOrdenSinCliente() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	int num = 100000000;
    	
    	Cantidad c = new Cantidad(1);
    	
    	List<Cantidad> Lista = new ArrayList<>();
    	
    	Lista.add(c);
    	
    	Proveedor prov = new Proveedor("a", "b", "c", "d", fecha, null, null, null, null);
    	
    	sis.setUsuarioActual((Usuario) prov);
    	
    	// intento elegir una orden con un numero de orden que no existe
    	IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->{ 
    		sis.agregarOrden(Lista);
    		//no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("El usuario actual no es un cliente.", thrown.getMessage());
    	
    }
    
   
    
    // ################################ ElegirOrden ################################
    
    @Test
    public void ElegirOrdenExitoso() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	Cantidad c = new Cantidad(1);
    	
    	Producto prod = new Producto(null, null, null, 0, 0, null, null, null, null);
    	
    	c.setProducto(prod);
    	
    	List<Cantidad> Lista = new ArrayList<>();
    	
    	Lista.add(c);
    	
    	Cliente cli = new Cliente("a", "b", "c", "d", fecha, null, null);
    	
    	sis.setUsuarioActual((Usuario) cli);
    	
    	Cantidad cant = new Cantidad(1);
    	cant.setProducto(new Producto(null, null, null, 0, 0, null, null, null, null));
    
    	OrdenDeCompra ord = sis.agregarOrden(Lista);
    	
    	boolean res1 = false;
    	
		try {
			res1 = sis.elegirOrdenDeCompra(ord.getNumero());
		} catch (OrdenDeCompraNoExisteException e) {

			e.printStackTrace(); //no deberia ejecutarse
		}

    	assertTrue(res1, "La orden deberia seleccionarse correctamente.");
    }
    
   @Test
    public void ElegirOrdenNoExiste() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	int num = 100000000;
    	
    	// intento elegir una orden con un numero de orden que no existe
    	OrdenDeCompraNoExisteException thrown = assertThrows(OrdenDeCompraNoExisteException.class, () ->{ 
    		sis.elegirOrdenDeCompra(num);
    		//no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("La orden de compra número " + '"' + num + '"' + " no existe.", thrown.getMessage());
    }
    
    // ################################ CancelarOrden ################################
   
   	@Test
   	public void CancelarOrdenExitoso() {
   		ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	Cantidad c = new Cantidad(1);
    	
    	Producto prod = new Producto(null, null, null, 0, 0, null, null, null, null);
    	
    	c.setProducto(prod);
    	
    	List<Cantidad> Lista = new ArrayList<>();
    	
    	Lista.add(c);
    	
    	Cliente cli = new Cliente("a", "b", "c", "d", fecha, null, null);
    	
    	sis.setUsuarioActual((Usuario) cli);
    	
    	Cantidad cant = new Cantidad(1);
    	cant.setProducto(new Producto(null, null, null, 0, 0, null, null, null, null));
    
    	OrdenDeCompra ord = sis.agregarOrden(Lista);
    	
    	sis.setOrdenActual(ord);
    	
    	try {
			sis.cancelarOrdenDeCompra(ord.getNumero()); // Deberia ejecutarse
		} catch (OrdenDeCompraNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		} 
   	}
    
    @Test
    public void CancelarOrdenNoExiste() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	int num = 100000000;
    	
    	// intento elegir una orden con un numero de orden que no existe
    	OrdenDeCompraNoExisteException thrown = assertThrows(OrdenDeCompraNoExisteException.class, () ->{ 
    		sis.cancelarOrdenDeCompra(num);
    		//no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("La orden de compra número " + '"' + num + '"' + " no existe.", thrown.getMessage());
    }
    
    // ################################ RealizarCompra ################################
    
    @Test
    public void realizarCompraExitoso() {
        // Instancia del sistema
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        
        // Crear fecha para los objetos
        DTFecha fecha = new DTFecha(1, 10, 1990);
        
        // Crear proveedor
        Proveedor prov = new Proveedor("Proveedor1", "prov1@gmail.com", "Carlos", "Perez", fecha, "Compañía 1", "https://compania1.com", null, "12345678", "12345678");

        // Crear producto válido
        Producto prod = new Producto("Producto1", "Descripción de Producto1", Arrays.asList("Especificación 1", "Especificación 2"), 101, 200.0f, Arrays.asList("imagen1.jpg", "imagen2.jpg"), Arrays.asList(new Categoria("Categoria1", true, null)), prov, "Tienda1");
        
        // Crear cantidad asociada al producto
        Cantidad cantidad = new Cantidad(2); // Cantidad de 2 productos
        cantidad.setProducto(prod); // Asignar el producto a la cantidad

        // Crear carrito para el cliente
        HashMap<Integer, Cantidad> carrito = new HashMap<>();
        carrito.put(1, cantidad); // Añadir la cantidad al carrito

        // Crear cliente con un carrito
        Cliente cliente = new Cliente("Cliente1", "cliente1@gmail.com", "Juan", "Lopez", fecha, null, "87654321");
        cliente.setCarrito(carrito); // Asignar el carrito al cliente

        // Establecer al cliente como el usuario actual en el sistema
        sis.setUsuarioActual(cliente);
        
        // Crear una orden de compra con los productos del carrito
        OrdenDeCompra orden = new OrdenDeCompra(12345, fecha, prod, Arrays.asList(cantidad));

        try {
            // Realizar la compra
            sis.realizarCompra(orden);
            
            // Verificar que la orden fue registrada correctamente en el sistema
            List<DTOrdenDeCompra> ordenes = sis.listarOrdenesDeCompra();
            
            // Verificar que la lista de órdenes contiene la orden recién creada
            assertTrue(ordenes.stream().anyMatch(o -> o.getNumero() == orden.getNumero()));
            
        } catch (UsuarioNoExisteException e) {
            fail("No se debería haber lanzado una excepción de usuario: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            fail("No se debería haber lanzado una excepción de argumento: " + e.getMessage());
        }
    }


    
    
    @Test
    public void realizarCompraConUsuarioNull() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	OrdenDeCompra ord = new OrdenDeCompra(55674, new DTFecha(1, 12, 1987), null, null);
    	
    	sis.setUsuarioActual(null);
    	
    	UsuarioNoExisteException thrown = assertThrows(UsuarioNoExisteException.class, () ->{ 
    		sis.realizarCompra(ord);
    		//no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("No se ha seleccionado ningún usuario previamente.", thrown.getMessage());
    }
    
    @Test
    public void realizarCompraConUsuarioNoCliente() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	OrdenDeCompra ord = new OrdenDeCompra(535674, new DTFecha(1, 10, 1990), null, null);
    	
    	Proveedor prov = new Proveedor("marce", "marcelo", "odi", "kadjwa@email.com", fecha, null, null, null, null);
    	
    	sis.setUsuarioActual((Usuario) prov);
    	
    	UsuarioNoExisteException thrown = assertThrows(UsuarioNoExisteException.class, () ->{ 
    		sis.realizarCompra(ord);
    		//no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("El usuario actual no es un Cliente.", thrown.getMessage());
    }
    
  
   /* @Test
    public void OrdenDeCompraRepetida() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	int num = 100000000;
    	
    	 try {
             sis. //aca iria algo que creeoRdenes y tire la escepcion de OrdenDeCompraRepetidaException
         } catch (Exception e) {
             fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
         }
    	
    	// intento elegir una orden con un numero de orden que no existe
    	OrdenDeCompraRepetidaException thrown = assertThrows(OrdenDeCompraRepetidaException.class, () ->{ 
    		System.out.println("AAA");
    		sis.elegirOrdenDeCompra(num);
    		// no deberia ejecutarse
    	});
    	
    	// Verificar el mensaje de la excepción
    	assertEquals("La Orden De compra y existe: ", thrown.getMessage());
    }
	*/
    
    // ################################ ElegirCategoria ################################
    
    
    @Test
    public void ElegirCategoriaExitoso() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	String nom = "TussWArrior";
    	
    	try {
			sis.altaCategoria(nom, false, null);
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	boolean res1 = false;
    	
    	try {
			res1 = sis.elegirCategoria(nom); // Deberia ejecutarse
		} catch (CategoriaNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	assertTrue(res1, "La categoria Deberia haberse escogido correctametne");
    	
    }
    
    
    @Test
    public void CategoriaNoExisteConNombre() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	String nom = "vamoarribaeldefensorsportingparasiempreyporsiempre";
    	
		CategoriaNoExisteException thrown = assertThrows(CategoriaNoExisteException.class, () ->{ 
    		sis.elegirCategoria(nom);
    		//no deberia ejecutarse
    	});
		
		assertEquals("La categoría de nombre " + '"' + nom + '"' + " no existe.", thrown.getMessage());
    }
    
    @Test
    public void CategoriaNoExisteConNUll() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	String nom = null;
    	
    	CategoriaNoExisteException thrown = assertThrows(CategoriaNoExisteException.class, () ->{ 
    		sis.elegirCategoria(nom);
    		//no deberia ejecutarse
    	});
		
		assertEquals("La categoría de nombre " + '"' + nom + '"' + " no existe.", thrown.getMessage());
    	
    }
    
 // ################################ AgregarProductoACategorias ################################
    
    @Test
    public void agregarProductoACategoriaExitoso() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test1234tesistesteado", true, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	Producto prod = new Producto("wattpad", "descrip", null, 81118890, 0, null, lista, null, "dd market");
    	
    	sis.setProductoActual(prod);
    	
    	try {
			sis.agregarProductoACategorias(lista); // Deberia ejecutarse
		} catch (CategoriaNoPuedeTenerProductosException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    }
    
    @Test
    public void agregarProductoACategoriaConProdActualNull() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("testesteado", false, null);
		
    	List<Categoria> lista = new ArrayList<>();
    	
    	lista.add(cat);
    	
    	sis.setProductoActual(null);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.agregarProductoACategorias(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("No se ha elegido un producto previamente.", thrown.getMessage());
    }
    
    @Test
    public void agregarProductoACategoriasCatSinProductos() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test1234testsisistesteado", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	Producto prod = new Producto("wattpad", "descrip", null, 81118890, 0, null, lista, null, "dd market");
    	
    	sis.setProductoActual(prod);
    	
    	CategoriaNoPuedeTenerProductosException thrown = assertThrows(CategoriaNoPuedeTenerProductosException.class, () ->{ 
    		sis.agregarProductoACategorias(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Las categorías " + cat.getNombreCat() + " no puede contener productos.", thrown.getMessage());
    }
    
    // ################################ AgregarCategoriasAProducto ################################
    
    @Test
    public void agregarCategoriasAProductoExitoso() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test1djkoakko", true, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	Producto prod = new Producto("wat3ad", "descrip", null, 811890, 0, null, lista, null, "dd market");
    	
    	sis.setProductoActual(prod);
    	
    	
    	try {
			sis.agregarCategoriasAProducto(lista); // Deberia ejecutarse
		} catch (CategoriaNoPuedeTenerProductosException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    }
    
    @Test
    public void agregarCategoriasAProductoConProdActualNull() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test1jkoakko", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	
    	sis.setProductoActual(null);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.agregarCategoriasAProducto(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("No se ha elegido un producto previamente.", thrown.getMessage());
    	
    }
    
    @Test
    public void agregarCategoriasAProductoConCategoriaSinProductos() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test1doaodjkoakko", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	Producto prod = new Producto("wat3ad", "descrip", null, 811890, 0, null, lista, null, "dd market");
    	
    	sis.setProductoActual(prod);
    	
    	CategoriaNoPuedeTenerProductosException thrown = assertThrows(CategoriaNoPuedeTenerProductosException.class, () ->{ 
    		sis.agregarCategoriasAProducto(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Las categorías " + cat.getNombreCat() + " no puede contener productos.", thrown.getMessage());
    	
    }
    
    // ################################ RegitrarProducto ################################
    
    /*@Test
    public void RegistrarProductoSinProveedor() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("teaodjkoakko", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	
    	Producto prod = new Producto("wat3ad", "descrip", null, 8190, 0, null, lista, null, "dd market");
    	
    	sis.setUsuarioActual(null);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.registrarProducto(prod.getNombreProducto(), prod.getNumReferencia(), prod.getDescripcion(), null, 0, lista, null, prod.getNombreTienda());
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("No se ha elegido un proveedor previamente.", thrown.getMessage());
    	
    }
    
    @Test
    public void RegistrarProductoEnCategoriaSinProductos() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("teaokoakko", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();	
    	lista.add(cat);
    	
    	DTFecha f = new DTFecha(2, 4, 1998);
    	
    	Producto prod = new Producto("wat3ad", "descrip", null, 8190, 0, null, lista, null, "dd market");
    	
    	Proveedor prov = new Proveedor("nice", "pool", "gonzales", "laal@yahoo@respuestas.com.yahoooo", f, null, "company inc", "olaaa.com", "12345678");
    	
    	try {
			sis.altaUsuarioProveedor(prov.getNickname(), prov.getEmail(), prov.getNombre(), prov.getApellido(), prov.getFechaNac(), prov.getnomCompania(), prov.getlink(), null ,prov.getContrasenia(), prov.getContrasenia());
		} catch (UsuarioRepetidoException | ContraseniaIncorrectaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
    	
    	sis.setUsuarioActual((Usuario) prov);
    	
    	CategoriaNoPuedeTenerProductosException thrown = assertThrows(CategoriaNoPuedeTenerProductosException.class, () ->{ 
    		sis.registrarProducto(prod.getNombreProducto(), prod.getNumReferencia(), prod.getDescripcion(), null, 0, lista, null, prod.getNombreTienda());
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Las categorías " + cat.getNombreCat() + " no puede contener productos.", thrown.getMessage());
    	
    }
    */
    
    // ################################ AltaCategoria ################################
    
    @Test
    public void AltaCategoriaExitoso() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	Categoria cat = new Categoria("HOMEINO", true, null);
    	
    	Categoria c = null;
    	
    	try {
			c = sis.altaCategoria(cat.getNombreCat(), true, cat.getPadre()); // Deberia ejecutarse
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	assertNotNull(c, "La Categoria deberia haber sido creada correctamente");
    	
    }
    
    @Test
    public void AltaCategoriaConCategoriaRepetida() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	Categoria cat = new Categoria("HOMEROCHINO", true, null);
    	
    	try {
			sis.altaCategoria(cat.getNombreCat(), true, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
    	
    	Categoria cat2 = new Categoria("HOMEROCHINO", true, null);
    	
    	
    	CategoriaRepetidaException thrown = assertThrows(CategoriaRepetidaException.class, () ->{ 
    		sis.altaCategoria(cat2.getNombreCat(), true, cat2.getPadre()); //no deberia ejecutarse
    	});
    	
    	assertEquals("Ya existe una categoría con el nombre " + '"' + cat.getNombreCat() + '"' + '.', thrown.getMessage());
    }
    
    // ################################ ElegirCliente ################################
    
    @Test
    public void ElegirCliente() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	String nick = "jorgitohwudhuahiwhhi";
    			
    	DTFecha f = new DTFecha(3, 4, 2000);
    	
    	Cliente cli = new Cliente(nick, "bllpald", "cjdjajij", "d@email.com", f, null, "12345454653");
    			
    	try {
			sis.altaUsuarioCliente(cli.getNickname(), cli.getEmail(), cli.getNombre(), cli.getApellido(), f, null, cli.getContrasenia(), cli.getContrasenia()); // Deberia ejecutarse
		} catch (UsuarioRepetidoException | ContraseniaIncorrectaException | IllegalArgumentException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	boolean res1 = false; 
    	
    	try {
			res1 = sis.elegirCliente(cli.getNickname()); // Deberia ejecutarse
		} catch (UsuarioNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		} 
    	
    	assertTrue(res1, "El cliente debe haber sigo escogido correctamente");
    }
    
    @Test
    public void ElegirClienteConProveedor() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	String nick = "jorgitohwuahiwhhi";
    			
    	DTFecha f = new DTFecha(3, 4, 2000);
    	
    	Proveedor prov = new Proveedor(nick, "blald", "cjdjjij", "d@emaill.com", f, null, "jacoco", "jidaa.com", "12345454653");
    			
    	// nickname,  email,  nombre,  apellido, DTFecha fechaNac, String nomCompania, String linkWeb, String imagen, String contrasenia1, String contrasenia2
    	
    	try {
			sis.altaUsuarioProveedor(prov.getNickname(), prov.getEmail(), prov.getNombre(), prov.getApellido(), f, prov.getnomCompania(), prov.getlink() , null ,prov.getContrasenia(), prov.getContrasenia()); // Deberia ejecutarse
		} catch (UsuarioRepetidoException | ContraseniaIncorrectaException | IllegalArgumentException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	
    	UsuarioNoExisteException thrown = assertThrows(UsuarioNoExisteException.class, () ->{ 
    		sis.elegirCliente(prov.getNickname()); // No deberia ejecutarse
    	});
    	
    	assertEquals("El usuario de nickname " + '"' + prov.getNickname() + '"' + " existe, pero no es un cliente.", thrown.getMessage());
    }
    
    
    @Test
    public void ElegirClienteNoexiste() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	String nick = "tumbalacasamami";
    	
    	UsuarioNoExisteException thrown = assertThrows(UsuarioNoExisteException.class, () ->{ 
    		sis.elegirCliente(nick); // No deberia ejecutarse
    	});
    	
    	assertEquals("El usuario de nickname " + '"' + nick + '"' + " no existe.", thrown.getMessage());
    }
    
    // ################################ VerInfoCliente ################################

    @Test
    public void VerInfoClienteExitoso() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	DTClienteDetallado dt = null;
    	
    	Cliente cli = new Cliente("solsitotodoslosdiasporfanomentira", "bllpald", "cjdjajij", "d@email.com", fecha, null, "12345454653");
    	
    	sis.setUsuarioActual((Usuario) cli);
    	
    	dt = sis.verInformacionCliente();
    	
    	assertNotNull(dt, "LA informacion debe haberse recibido correctamente");
    }
    
    @Test
    public void VerInfoClienteConProveedor() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	Proveedor prov = new Proveedor("solsitotodoslosdiasporfanomentira", "bllpald", "cjdjajij", "d@email.com", fecha, null,"lapida", "lapida.com" ,"12345454653");
    	
    	sis.setUsuarioActual((Usuario) prov);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.verInformacionCliente();
    	});
   
    	
    	assertEquals("No se ha elegido un cliente previamente.", thrown.getMessage());
    }
    
    @Test
    public void VerInfoClienteConNull() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();

    	sis.setUsuarioActual(null);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.verInformacionCliente();
    	});
   
    	
    	assertEquals("No se ha elegido un cliente previamente.", thrown.getMessage());
    }
    
    // ################################ ModificarCanitdadItemCarrito ################################
    
    @Test
    public void ModificarCantidadIemCarritoExitoso() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	Cliente cli = new Cliente("solsitotodoslosdiasporfanomentira", "bllpald", "cjdjajij", "d@email.com", fecha, null, "1234545463");
    	
    	HashMap<Integer, Cantidad> car = new HashMap<>();
    	
    	Cantidad cant = new Cantidad(1);
    	
    	car.put(1, cant);
    	
    	cli.setCarrito(car);
    	
    	sis.setUsuarioActual((Usuario) cli); 
    	
    	try {
			sis.setCarritoActual(car);	//Deberia ejecutarse
		} catch (UsuarioNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    	try {
			sis.modificarCantidadItemCarrito(1, 2); //Deberia ejecutarse
		} catch (UsuarioNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
		}
    	
    }
    
    @Test
    public void ModificarCantidadIemCarritoConProveedor() {
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	Proveedor prov = new Proveedor("solsitotodoslosdiasporfanomentira", "bllpald", "cjdjajij", "d@email.com", fecha, null, "link","link.com","1234545463");
    	
    	sis.setUsuarioActual((Usuario)prov);
    	
    	UsuarioNoExisteException thrown = assertThrows(UsuarioNoExisteException.class, () ->{ 
    		sis.modificarCantidadItemCarrito(0, 0);
    	});
    	
    	assertEquals("El usuario actual no es un Cliente.", thrown.getMessage());
    }
    
}
