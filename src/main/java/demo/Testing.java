package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flamingo.exceptions.CategoriaNoExisteException;
import com.flamingo.exceptions.CategoriaNoPuedeTenerProductosException;
import com.flamingo.exceptions.CategoriaRepetidaException;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.OrdenDeCompraRepetidaException;
import com.flamingo.exceptions.ProductoNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.Categoria;
import com.flamingo.models.DTFecha;
import com.flamingo.models.ISistema;
import com.flamingo.models.Producto;
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
    
    @Test
    public void agregarProductoACategoriaSinProductosConProdActualNull() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	// Instacncia de categoria
    	Categoria cat = new Categoria("test12sisistesteado", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
    	List<Categoria> lista = new ArrayList<>();
    	
    	lista.add(cat);
    	
    	NullPointerException thrown = assertThrows(NullPointerException.class, () ->{ 
    		sis.agregarProductoACategorias(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("No se ha elegido un producto previamente.", thrown.getMessage());
    }
    
    @Test
    public void agregarProductoACategoriaSinProductosConProdActual() {
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
    	
    	
    	
    	try {
			sis.elegirCategoria(cat.getNombreCat());
		} catch (CategoriaNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
    	
    	try {
			sis.elegirProducto(prod.getNombreProducto());
		} catch (ProductoNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
    	
    	CategoriaNoPuedeTenerProductosException thrown = assertThrows(CategoriaNoPuedeTenerProductosException.class, () ->{ 
    		sis.agregarProductoACategorias(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Las categorías " + cat.getNombreCat() + " no puede contener productos.", thrown.getMessage());
    }
    
    @Test
    public void agregarCategoriaAProductoACategoriaSinProductos() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    	
    }
    
    @Test
    public void CategoriaRepetida() {
    	// Instancia del sistema
    	ISistema sis = SistemaFactory.getInstancia().getISistema();
    	
    }
    
    
}
