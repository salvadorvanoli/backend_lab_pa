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
        assertTrue(thrown.getMessage().contains("Las contraseñas no coinciden"));
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

    
}
