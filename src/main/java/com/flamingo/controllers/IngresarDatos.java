package com.flamingo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ingresardatos")
public class IngresarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al JSP para mostrar el formulario
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/ingresarDatos.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String contraseña = request.getParameter("contraseña");
        String repetirContraseña = request.getParameter("repetirContraseña");
        String fecha = request.getParameter("fecha");
        String sitioWeb = request.getParameter("sitioWeb");
        String compañia = request.getParameter("compania");

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
            errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
        }

        if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3) {
            errores.add("El apellido debe tener al menos 3 caracteres.");
        }

        if (contraseña == null || contraseña.length() < 8) {
            errores.add("La contraseña debe tener al menos 8 caracteres.");
        } else if (!contraseña.equals(repetirContraseña)) {
            errores.add("Las contraseñas no coinciden.");
        }

        if (fecha == null || fecha.trim().isEmpty()) {
            errores.add("Se debe ingresar una fecha.");
        }

        if (sitioWeb != null && !sitioWeb.trim().isEmpty() && !validarUrl(sitioWeb)) {
            errores.add("La URL del sitio web no es válida.");
        }

        if (compañia == null || compañia.trim().isEmpty()) {
            errores.add("La compañía es obligatoria.");
        }

        // Si hay errores, redirigir de vuelta al formulario con mensajes de error
        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/ingresarDatos.jsp");
            dispatcher.forward(request, response); // Ajusta la ruta según sea necesario
        } else {
            // Procesar el registro del usuario (guardar en base de datos, etc.)
            // Aquí puedes agregar la lógica para guardar los datos del usuario

            // Redirigir a una página de éxito o confirmación
            response.sendRedirect(request.getContextPath() + "/paginaExito.jsp"); // Ajusta esta ruta según sea necesario
        }
    }

    private boolean validarNombreSinNumeros(String nombre) {
        return !nombre.matches(".*\\d.*"); // Devuelve true si no hay dígitos
    }

    private boolean validarUrl(String url) {
        String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(:[0-9]+)?(/.*)?$";
        return url.matches(regex);
    }
}
