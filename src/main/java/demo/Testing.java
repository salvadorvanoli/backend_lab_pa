package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.flamingo.exceptions.CategoriaNoPuedeTenerProductosException;
import com.flamingo.exceptions.CategoriaRepetidaException;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.ProductoRepetidoException;
import com.flamingo.exceptions.UsuarioNoEncontrado;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Categoria;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.DTCliente;
import com.flamingo.models.DTFecha;
import com.flamingo.models.DTOrdenDeCompraDetallada;
import com.flamingo.models.DTProducto;
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Producto;
import com.flamingo.models.Proveedor;
import com.flamingo.models.SistemaFactory;

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

    
}
