package com.flamingo.models;
import java.util.List;

public class ComentarioRenderer {

    public static String renderComentarios(List<Comentario> comentarios, int nivel) {
        StringBuilder html = new StringBuilder();
        
        for (Comentario comentario : comentarios) {
            html.append("<div class='comentario-respuesta-container container-md container-fluid'>")
                .append("<div class='carta-comentarios row' style='margin-left: ")
                .append(nivel * 2)
                .append("px;'>")
                .append("<div class='informacion-usuario col-md-3 col-12'>")
                .append("<img src='")
                .append(comentario.getCliente().getFoto())
                .append("' alt='Perfil usuario' class='imagen-usuario'>")
                .append("<div><h4>")
                .append(comentario.getCliente().getNombre())
                .append("</h4><p class='fecha'>")
                .append(comentario.getFecha().getFechaEnFormatoInput())
                .append("</p>")
                .append("<div class='estrellas'>");

            if(nivel == 0) {
            	// Generar estrellas
                int estrellas = comentario.getEstrellas();
                for (int i = 0; i < estrellas; i++) {
                    html.append("<i class='fas fa-star' style='color: #7A7A7A;'></i>");
                }
                for (int i = 0; i < (5 - estrellas); i++) {
                    html.append("<i class='fas fa-star' style='color: #EBEBEB;'></i>");
                }
            }
            
            html.append("</div><button class='btn btn-success mt-2' onclick='mostrarCajaRespuesta()'>Responder</button>")
                .append("</div></div>")
                .append("<div class='texto-comentario col-md-8 col-12'>")
                .append(comentario.getContenido())
                .append("</div></div>");

            // Recursivamente renderizar las respuestas
            if (comentario.getComentarios() != null && !comentario.getComentarios().isEmpty()) {
                html.append("<div class='respuestas-comentario'>")
                    .append(renderComentarios(comentario.getComentarios(), nivel + 1))
                    .append("</div>");
            }

            html.append("</div>");
        }

        return html.toString();
    }
}
