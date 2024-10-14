package com.flamingo.models;
import java.util.List;

public class ComentarioRenderer {

    public static String renderComentarios(List<Comentario> comentarios, int nivel, int contador, Boolean compro) {
        StringBuilder html = new StringBuilder();
        
        for (Comentario comentario : comentarios) {
            html.append("<div class='comentario-respuesta-container container-md container-fluid'>")
                .append("<div class='carta-comentarios row' style='margin-left: ")
                .append(nivel * 20)
                .append("px;'>")
                .append("<div class='informacion-usuario col-md-3 col-12'>")
                .append("<img src='")
                .append(comentario.getCliente().getFoto() != null && !comentario.getCliente().getFoto().isEmpty() 
                ? comentario.getCliente().getFoto() 
                : "media/images/Chica1.png")
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
            
            html.append("</div>");
            
            if(compro) {
            	html.append("<button class='btn btn-success mt-2' onclick='mostrarCajaRespuesta(")
            	.append(contador)
	            .append(", \"")
	            .append(comentario.getId())
	            .append("\")'>Responder</button>");
            }
            
            html.append("</div></div>")
	            .append("<div class='texto-comentario col-md-8 col-12'>")
	            .append(comentario.getContenido())
	            .append("</div></div>");
           

            // Recursivamente renderizar las respuestas
            if (comentario.getComentarios() != null && !comentario.getComentarios().isEmpty()) {
                html.append("<div class='respuestas-comentario'>")
                    .append(renderComentarios(comentario.getComentarios(), nivel + 1, contador, compro))
                    .append("</div>");
            }
            
            if (nivel == 0) {
                contador++;
                html.append("<form action='nuevaRespuesta' method='POST' class='caja-comentario card p-3 mt-3 mb-3 d-none' id='respuesta")
                .append(contador)
                .append("'>")
                
                // Campo para ingresar el comentario
                .append("<div class='form-group'>")
                .append("<label for='comentarioInput")
                .append(contador)
                .append("'>Escribe tu comentario:</label>")
                .append("<input type='text' name='texto' class='form-control' id='comentarioInput")
                .append(contador)
                .append("' name='comentario' placeholder='Escribe aquí...'>")
                .append("</div>")
                
                // Input oculto para enviar el ID del comentario
                .append("<input type='hidden' name='id' id='comentarioId")
                .append(contador)
                .append("' value='")
                .append(comentario.getId())
                .append("'>")
                
                // Botones de submit y cancelar
                .append("<div class='mt-3' id='botonesComentario")
                .append(contador)
                .append("'>")
                
                // Botón submit como input type submit
                .append("<input type='submit' class='btn btn-success' value='Aceptar'>")
                
                // Botón para cancelar
                .append("<button class='btn btn-danger' type='button' onclick='cancelarComentario(")
                .append(contador)
                .append(")'>Cancelar</button>")
                
                .append("</div>")
                .append("</form>");

            }
            
            html.append("</div>");
        }

        return html.toString();
    }
}
