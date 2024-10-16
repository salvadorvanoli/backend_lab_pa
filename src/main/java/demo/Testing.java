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
import com.flamingo.exceptions.ProductoRepetidoException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.Categoria;
import com.flamingo.models.DTFecha;
import com.flamingo.models.ISistema;
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
	
    
    // ################################ ORDEN ################################
    
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
    
 // ################################ Categoria ################################
    
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
    	Categoria cat = new Categoria("testesteado", false, null);
    	
		try {
			sis.altaCategoria(cat.getNombreCat(), false, cat.getPadre());
		} catch (CategoriaRepetidaException e) {
			fail("No se debería haber lanzado una excepción."); //no deberia ejecutarse
		}
		
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
    	
    	sis.setProductoActual(prod);
    	
    	CategoriaNoPuedeTenerProductosException thrown = assertThrows(CategoriaNoPuedeTenerProductosException.class, () ->{ 
    		sis.agregarProductoACategorias(lista);
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Las categorías " + cat.getNombreCat() + " no puede contener productos.", thrown.getMessage());
    }
    
    @Test
    public void agregarCategoriaSinProductosAProductoConProdActualNull() {
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
    
    @Test
    public void RegistrarProductoEnCategoriaSinProductosSinProveedor() {
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
    
    @Test
    public void CategoriaRepetida() {
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
    		sis.altaCategoria(cat2.getNombreCat(), true, cat2.getPadre());
    		//no deberia ejecutarse
    	});
    	
    	assertEquals("Ya existe una categoría con el nombre " + '"' + cat.getNombreCat() + '"' + '.', thrown.getMessage());
    }
    
    
}
