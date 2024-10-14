package com.flamingo.controllers;
 
import java.io.IOException; 
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.Producto;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Cantidad;
import com.flamingo.models.DTCantidad;
import com.flamingo.models.ISistema;
import com.flamingo.models.Usuario;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.Cliente;
import com.flamingo.models.DTFecha;
import com.flamingo.models.DTProducto;
import com.flamingo.exceptions.ContraseniaIncorrectaException;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.exceptions.UsuarioNoExisteException;
import com.flamingo.exceptions.UsuarioRepetidoException;

@WebServlet("/VerOrdenDeCompra")
public class infoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrdenDeCompraNoExisteException {
        ISistema sis = SistemaFactory.getInstancia().getISistema();
       
        /*borrar luego:
        	
        
        	//String nickName, String nombre, String apellido, String email, DTFecha fecha, String foto, String contrasenia
        	Usuario user = new Usuario("bellardoa2eda", "israkadkk", "beadawdk,pllizzi", "elmadafackinisraelllll@yahoo.com", new DTFecha(1,1,1), null, "kawdlkalalala");
        	
			sis.altaUsuarioCliente(user.getNickname(), user.getNombre(), user.getApellido(), user.getEmail(), user.getFechaNac(), user.getFoto(), user.getContrasenia(), user.getContrasenia());

        	
        	Cliente cli = new Cliente(user.getNickname(), user.getNombre(), user.getApellido(), user.getEmail(), user.getFechaNac(), user.getFoto(), user.getContrasenia());
        	
        	List<String> espe = new ArrayList<>();
        	espe.add("h20 puro");
        	
        	Producto prod1 = new Producto("Agua Fresca", "Muy refrescante.", espe, 999, 72.5f, null, null,  null, "DD Water");
        	Producto prod2 = new Producto("Semen Fresco", "Muy sabrozo.", null, 2, 14500f, null, null,  null, "SOTO");
        	Producto prod3 = new Producto("Bamboo", "Re duro.", null, 122, 1f, null, null,  null, "be lichi");
        	Producto prod4 = new Producto("Milanga", "Muy rica.", null, 3, 12f, null, null,  null, "Saul Scanino");
        	
        	DTCantidad cant = new DTCantidad(3, prod1.getDTProducto());
        	DTCantidad cant8 = new DTCantidad(90, prod2.getDTProducto());
        	DTCantidad cant9 = new DTCantidad(1, prod3.getDTProducto());
        	DTCantidad cant10 = new DTCantidad(20, prod4.getDTProducto());
        	
        	List<DTCantidad> listaCan = new ArrayList<>();
        	
        	listaCan.add(cant);
        	listaCan.add(cant8);
        	listaCan.add(cant9);
        	listaCan.add(cant10);
        	
        	//int numero, DTFecha fecha, Cliente cliente, List<DTCantidad> cantidades
        	OrdenDeCompra orden = new OrdenDeCompra(1, sis.getFechaActual(), cli, listaCan);
        	
        	cli.vincularOrdenDeCompra(orden);
        	
        */
        	
        Usuario user = sis.getUsuarioActual();
        
        sis.crearCasos();
        
        String num = request.getParameter("ordenId");
        
        if(num == null) {
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(num);

        Cliente cliente = new Cliente(user.getNickname(), user.getNombre(), user.getApellido(), user.getEmail(), user.getFechaNac(), user.getFoto(), user.getContrasenia());
        
        
        List<OrdenDeCompra> ordenes = cliente.getOrdenesDeCompras();
        OrdenDeCompra ordenSeleccionada = null;
        
        for(OrdenDeCompra orden1 : ordenes) {
        	if(orden1.getNumero() == id) {
        		ordenSeleccionada = orden1;
        		break;
        	}
        }

        if (ordenSeleccionada == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra no existe");
        }
        
        
        List<DTProducto> listaProd = new ArrayList<>();
        
        for(DTCantidad ca : ordenSeleccionada.getCantidad()) {
        	listaProd.add(ca.getProducto());
        }
        
        request.setAttribute("usuarioActual", sis.getUsuarioActual());
        request.setAttribute("productos", listaProd);
        request.setAttribute("orden", ordenSeleccionada);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orden/infoOrden.jsp");
        dispatcher.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | OrdenDeCompraNoExisteException e) {
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException | OrdenDeCompraNoExisteException e) {
			 e.printStackTrace();
		}
    }
}