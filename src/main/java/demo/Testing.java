package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flamingo.controllers.Carrito;
import com.flamingo.exceptions.CategoriaNoExisteException;
import com.flamingo.exceptions.CategoriaNoPuedeTenerProductosException;
import com.flamingo.exceptions.CategoriaRepetidaException;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.OrdenDeCompraRepetidaException;
import com.flamingo.exceptions.ProductoNoExisteException;
import com.flamingo.exceptions.ProductoRepetidoException;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;
import com.flamingo.models.Cantidad;
import com.flamingo.models.Categoria;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTClienteDetallado;
import com.flamingo.models.DTFecha;
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
    	
    	OrdenDeCompra ord = new OrdenDeCompra(535674, new DTFecha(1, 10, 1990), null, null);
    	
    	Cliente cli = new Cliente(null, null, null, null, fecha, null, null);
    	
    	HashMap <Integer, Cantidad> car = new HashMap<>();
    	
    	Cantidad cant = new Cantidad(1);
    	
    	Proveedor prov = new Proveedor(null, null, null, null, fecha, null, null, null, null);
    	
    	Producto prod = new Producto(null, null, null, 0, 0, null, null, prov, null);
    	
    	prod.setCantCompras(1);
    	
    	cant.setProducto(prod);
    	
    	car.put(1, cant);
    	
    	cli.setCarrito(car);
    	
    	sis.setUsuarioActual((Usuario) cli);
    	
    	try {
			sis.realizarCompra(ord); // Deberia ejecutarse
		} catch (UsuarioNoExisteException e) {
			fail("No se debería haber lanzado una excepción."); // No deberia ejecutarse
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
    
    
    
}
