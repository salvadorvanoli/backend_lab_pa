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

import com.flamingo.exceptions.OrdenDeCompraNoExisteException;

@WebServlet("/VerOrdenDeCompra")
public class infoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrdenDeCompraNoExisteException {
        ISistema sis = SistemaFactory.getInstancia().getISistema();
        Usuario user = sis.getUsuarioActual();

        sis.crearCasos();
        
        String num = request.getParameter("ordenId");
        
        int id;
        try {
            id = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID de orden inválido.");
            return;
        }
        
        System.out.println("a");

        if (!(user instanceof Cliente)) {
            return; // Podrías redirigir a una página de acceso denegado
        }

        Cliente cliente = (Cliente) user;
        List<OrdenDeCompra> ordenes = cliente.getOrdenesDeCompras();
        OrdenDeCompra ordenSeleccionada = ordenes.stream()
            .filter(ord -> ord.getNumero() == id)
            .findFirst()
            .orElse(null);

        if (ordenSeleccionada == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra no existe");
        }

        List<DTCantidad> cantidades = ordenSeleccionada.getCantidad();
        List<DTProducto> productos = new ArrayList<>();
        
        for(DTCantidad cant : cantidades) {
        	productos.add(cant.getProducto());
        }
        
        request.setAttribute("productos", productos);

        HashMap<Integer, Cantidad> carri = cliente.getCarrito();
        request.setAttribute("carrito", carri);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orden/orden.jsp");
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