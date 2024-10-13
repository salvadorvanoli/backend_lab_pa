<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información producto</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <jsp:include page="/WEB-INF/template/head.jsp" />
 
    <link rel="stylesheet" href="media/css/infoProducto.css">
	
    <%@page import="com.flamingo.models.Usuario"%>
    <%@page import="com.flamingo.models.Cliente"%>
    <%@page import="com.flamingo.models.Proveedor"%>
    <%@page import="com.flamingo.models.OrdenDeCompra"%>
    <%@page import="com.flamingo.models.Producto"%>
    <%@page import="com.flamingo.models.OrdenDeCompra"%>
    <%@page import="com.flamingo.models.Categoria"%>
    <%@page import="com.flamingo.models.Comentario"%>
    <%@page import="com.flamingo.models.ComentarioRenderer"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    
    
    <style>
    
    	.linea-resumen {
		    width: 100%;
		    height: 1px;
		    opacity: 30%;
		    border-color: #606060;
		    border-width: 1px;
		    border-style: solid;
		    background-color: #606060;
		}
    
    </style>
    
</head>
<body>
    
    <%
    	Producto producto = (Producto) request.getAttribute("productoActual");
	
		if(producto == null) {
			response.sendRedirect("backend_lab_pa/home");
	        return;
		}
	%>
    
    <jsp:include page="/WEB-INF/template/header.jsp" />

    <main>
        
        <div class="container-md container-fluid">
            <div class="row">
                <div class="col-md-6 col-12 mt-5">
                    <div id="imgCarousel" class="carousel slide" data-bs-ride="carousel">
					    <ol class="carousel-indicators" id="indicadores-carrusel">
					        <%
					        List<String> imagenes = producto.getImagenes();
					        for (int contador = 0; contador < imagenes.size(); contador++) {
					            if (contador == 0) {
					        %>
					                <li data-bs-target="#imgCarousel" data-slide-to="<%= contador %>" class="active"></li>
					        <%
					            } else {
					        %>
					                <li data-bs-target="#imgCarousel" data-slide-to="<%= contador %>" class=""></li>
					        <%
					            }
					        }
					        %>
					    </ol>
					    
					    <div class="carousel-inner" id="item-carrusel">
					        <%
					        for (int contador = 0; contador < imagenes.size(); contador++) {
					            String imagen = imagenes.get(contador);
					            if (contador == 0) {
					        %>
					                <div class="carousel-item active">
					                    <img class="d-block w-100" src="<%= imagen %>" alt="Slide">
					                </div>
					        <%
					            } else {
					        %>
					                <div class="carousel-item">
					                    <img class="d-block w-100" src="<%= imagen %>" alt="Slide">
					                </div>
					        <%
					            }
					        }
					        %>
					    </div>
					    
					    <a class="carousel-control-prev" href="#imgCarousel" role="button" data-slide="prev">
					        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					        <span class="sr-only">Previous</span>
					    </a>
					    <a class="carousel-control-next" href="#imgCarousel" role="button" data-slide="next">
					        <span class="carousel-control-next-icon" aria-hidden="true"></span>
					        <span class="sr-only">Next</span>
					    </a>
					</div>
					
					<%
					    // Ocultar el carrusel si no hay imágenes
					    if (imagenes.isEmpty()) {
					%>
					    <script>
						    document.addEventListener('DOMContentLoaded', function() {
						    	document.getElementById("imgCarousel").style.display = "none";
						        document.getElementById("informacion-producto-container").classList.remove("col-md-6");
						        document.getElementById("informacion-producto-container").classList.add("col-md-12");
						        document.getElementById("numero-producto").style.textAlign = "left";
						    });
					    </script>
					<%
					    }
					%>
                    <div id="numero-producto">
						Número de referencia: <%= producto.getNumReferencia() %>
                    </div>
                </div>
    
                
                <div class="col-md-6 col-12 mt-5" id="informacion-producto-container">
                    <h1 id="nombre-producto">
						<%= producto.getNombreProducto() %>
                    </h1>

                    <div id="estrellas-container">
					    <%
					    int estrellas = producto.getEstrellas(); // Obtener la cantidad de estrellas
					    // Insertar estrellas llenas
					    for (int i = 0; i < estrellas; i++) {
					    %>
					        <i class="fas fa-star" style="color: #7A7A7A;"></i>
					    <%
					    }
					    
					    // Insertar estrellas vacías
					    for (int i = 0; i < (5 - estrellas); i++) {
					    %>
					        <i class="fas fa-star" style="color: #EBEBEB;"></i>
					    <%
					    }
					    %>
					</div>

                    <div class="linea-resumen"></div>
						
                    <div id="precio-container">
                    	$<%= producto.getPrecio() %>
                    </div>

                    <div class="linea-resumen"></div>

                    <div id="descripcion-container">
                        <p id="descripcion-texto">
							<%= producto.getDescripcion() %>
                        </p>
                    </div>

                    <div id="">
                    	
                    	<form action="agregarAlCarrito">
                    		<input type="text" name="numReferencia" value="<%= producto.getNumReferencia() %>" class="d-none">
                    		<input type="number" name="cantidad" id="cantidad-input" min="1" value="1">
                        	<input type="submit" value="Agregar al carrito" class="siguiente-button btn btn-danger" id="agregar-al-carrito" data-toggle="modal" data-target="#modalCarrito">
                    	</form>
                    
                    </div>
                </div>
            </div>

            <div id="categorias-container">
			    <%
			        List<Categoria> categorias = producto.getCategorias();
			        for (int i = 0; i < categorias.size(); i++) {
			            out.print(categorias.get(i).getNombreCat());
			            if (i < categorias.size() - 1) {
			                out.print(", ");
			            }
			        }
			    %>
			</div>

        </div>

        <div class="linea-resumen"></div>

        <section id="especificaciones-section">

            <h2>
                Especificaciones del producto
            </h2>

            <div id="especificaciones-container">
                <ul>
                <% 
		        for (String especificacion : producto.getEspecificacion()) { 
		        %>
		            <li><%= especificacion %></li>
		        <%
		        }
		        %>
                </ul>
            </div>

        </section>

        <div class="linea-resumen"></div>

        <section id="comentarios-section">

            <h2>
                Comentarios del producto
            </h2>

			<%
			    // Obtiene el usuario actual (asumiendo que está almacenado en la sesión)
			    Cliente usuarioActual = (Cliente) session.getAttribute("usuarioActual");
			
			    // Verifica si el usuario ha comprado el producto
			    boolean haCompradoProducto = (usuarioActual != null && usuarioActual.comproProducto(producto.getNumReferencia()));
			%>


            <div class="caja-comentario card p-3 mt-3 mb-3 ms-5 me-5 <%= haCompradoProducto ? "" : "d-none" %>" id="respuesta">
			    <div class="form-group">
			        <form action="nuevoComentario" method="POST">
			            <label for="comentarioInput">Escribe tu comentario:</label>
			            <input type="text" name="texto" class="form-control" id="comentarioInput" placeholder="Escribe aquí...">
			            <input type="number" name="estrellas" class="form-control mt-3" id="cantEstrellas" min="1" placeholder="Inserte la cantidad de estrellas (1-5)">
			        
			            <div class="mt-3" id="botonesComentario">
			                <input type="submit" class="btn btn-success" value="Enviar">
			            </div>
			        </form>
			    </div>
			</div>

            <div id="comentarios-container" class="contenedor-responsive">
			    <%
			        // Renderizar todos los comentarios y respuestas de manera recursiva
			        String htmlComentarios = ComentarioRenderer.renderComentarios(producto.getComentarios(), 0, 0, haCompradoProducto);
			        out.print(htmlComentarios);
			    %>
			</div>

        </section>

    </main>

    <footer>

    </footer>

    <!-- MODAL -->
    <div class="modal fade" id="modalCarrito" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">¡Producto agregado con éxito!</h5>
                </div>
            <div class="modal-body">
                Pulse ver carrito para abrirlo
            </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" onclick="window.location.href='carrito.html'">Ver carrito</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /MODAL -->

    <jsp:include page="/WEB-INF/template/footer.jsp" />
</body>

    <script type="text/javascript">

	    function cancelarComentario(contador) {
	    	
	        document.getElementById("respuesta" + contador).classList.add("d-none");
	    }
	    
	    function mostrarCajaRespuesta(contador, id) {

	    	contador++;
	    	
	        document.getElementById("respuesta" + contador).classList.remove("d-none");
	        
	        document.getElementById("comentarioId" + contador).value = id;
	    }
    
    </script>

</html>