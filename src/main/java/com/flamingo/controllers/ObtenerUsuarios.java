package com.flamingo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.flamingo.models.ISistema;
import com.flamingo.models.SistemaFactory;
import com.flamingo.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuarios")
public class ObtenerUsuarios extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ISistema sis = SistemaFactory.getInstancia().getISistema(); // Obtener la instancia del sistema
        List<Usuario> usuarios = sis.getUsuarios(); // Obtener la lista de usuarios registrados

        // Crear JSON manualmente
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            jsonBuilder.append("{");
            jsonBuilder.append("\"email\":\"").append(usuario.getEmail()).append("\",");
            jsonBuilder.append("\"nickname\":\"").append(usuario.getNickname()).append("\"");
            jsonBuilder.append("}");

            if (i < usuarios.size() - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]");

        // Configurar la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonBuilder.toString());
        out.flush();
    }
}
