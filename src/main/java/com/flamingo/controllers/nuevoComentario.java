package com.flamingo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;
import com.flamingo.models.ISistema;
import com.flamingo.models.Producto;
import com.flamingo.models.Cliente;
import com.flamingo.models.Comentario;
import com.flamingo.models.DTFecha;
import com.google.gson.Gson;

@WebServlet("/nuevoComentario")
public class nuevoComentario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public nuevoComentario() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	ISistema sis;
    	if (getServletContext().getAttribute("sistema") == null) {
    	    System.out.println("CREO EL SISTEMA");
    	    getServletContext().setAttribute("sistema", SistemaFactory.getInstancia().getISistema());
    	    sis = (ISistema) getServletContext().getAttribute("sistema");
    	    sis.crearCasos();
    	} else {
    	    sis = (ISistema) getServletContext().getAttribute("sistema");
    	}
    	
    	// Establecer tipo de respuesta
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            // Obtener los parámetros del formulario
            String textoComentario = request.getParameter("texto");
            int estrellasComentario = Integer.parseInt(request.getParameter("estrellas"));

            // Supongamos que obtienes el usuario logueado de la sesión
            Cliente usuarioActual = (Cliente) sis.getUsuarioActual();

            // Obtener la fecha actual
            DTFecha fechaActual = new DTFecha(LocalDate.now().getDayOfMonth(),
                                              LocalDate.now().getMonthValue(),
                                              LocalDate.now().getYear());

            List<Comentario> lista = new ArrayList<>();
            

            Comentario nuevoComentario = new Comentario("9", textoComentario, lista, usuarioActual, sis.getProductoActual(), fechaActual, estrellasComentario);


            Producto productoActual = sis.getProductoActual();

            if (productoActual != null) {

                productoActual.agregarComentario(nuevoComentario);
                
                out.println("{\"message\": \"Comentario agregado exitosamente\"}");
                
                response.sendRedirect("infoProducto");
                
            } else {
                out.println("{\"error\": \"Producto no encontrado\"}");
            }
        } catch (Exception e) {
            // Manejar errores
            out.println("{\"error\": \"Ocurrió un error al agregar el comentario: " + e.getMessage() + "\"}");
        } finally {
            out.close();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        processRequest(request, response);
    }
}