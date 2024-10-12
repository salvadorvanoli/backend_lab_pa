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
                        <input type="number" id="cantidad-input" min="1" value="1">
                        <input type="button" value="Agregar al carrito" class="siguiente-button btn btn-danger" id="agregar-al-carrito" data-toggle="modal" data-target="#modalCarrito">

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


            <div class="caja-comentario card p-3 mt-3 mb-3 ms-5 me-5" id="respuesta">
                <div class="form-group">
                    <label for="comentarioInput">Escribe tu comentario:</label>
                    <input type="text" class="form-control" id="comentarioInput" placeholder="Escribe aquí...">
                    <input type="number" class="form-control mt-3" id="cantEstrellas" min="1" placeholder="Inserte la cantidad de estrellas (1-5)">
                </div>
                <div class="mt-3" id="botonesComentario">
                    <button class="btn btn-success" onclick="agregarComentario()">Enviar</button>
                </div>
            </div>

            <div id="comentarios-container" class="contenedor-responsive">
			    <%
			        // Renderizar todos los comentarios y respuestas de manera recursiva
			        String htmlComentarios = ComentarioRenderer.renderComentarios(producto.getComentarios(), 0);
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

	    document.addEventListener("DOMContentLoaded", function() {
	
		    function mostrarCajaRespuesta(contador, id) {
		        document.getElementById("respuesta" + contador).classList.remove("d-none");
		        document.getElementById("botonesComentario" + contador).innerHTML = `
		            <button class="btn btn-success" onclick="aceptarComentario(${contador}, ${id})">Aceptar</button>
		            <button class="btn btn-danger" onclick="cancelarComentario(${contador})">Cancelar</button>
		        `;
		    }
		
		    function cancelarComentario(contador) {
		        document.getElementById("respuesta" + contador).classList.add("d-none");
		    }
		
		    function aceptarComentario(contador, id) {
		
		        let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;
		
		        if(usuarioActual == null || usuarioActual == ""){
		            mostrarAlerta("No se puede escribir un comentario si no se tiene la sesión iniciada");
		            return;
		        }
		
		        function buscarComentario(comentarios) {
		            for (let comentario of comentarios) {
		                if (comentario.id === id) {
		                    return comentario;
		                }
		                let respuestaEncontrada = buscarComentario(comentario.respuestas);
		                if (respuestaEncontrada) {
		                    return respuestaEncontrada;
		                }
		            }
		            return null;
		        }
		        
		        for (let producto of productos) {
		            if (producto.id == productoSeleccionado.id) {
		
		                let comentario = buscarComentario(producto.comentarios);
		                if (comentario) {
		                    // Obtenemos la fecha actual
		                    const fechaActual = new Date();
		                    const dia = String(fechaActual.getDate()).padStart(2, '0'); // Asegura que el día tenga dos dígitos
		                    const mes = String(fechaActual.getMonth() + 1).padStart(2, '0'); // +1 porque getMonth() empieza en 0
		                    const anio = fechaActual.getFullYear();
		                    const fechaFormateada = `${dia}/${mes}/${anio}`;
		                    const nuevoComentario = document.getElementById("comentarioInput" + contador).value.trim();
		
		                    if (nuevoComentario) {
		                        // Agregamos la nueva respuesta al comentario
		                        comentario.respuestas.push({
		                            usuario: usuarioActual.nombre,
		                            comentario: nuevoComentario,
		                            foto: usuarioActual.img,
		                            fecha: fechaFormateada,
		                            id: obtenerNuevoId(), // Asegúrate de que esta función retorne un ID único
		                            respuestas: []
		                        });
		
		                        // Guardamos los cambios en localStorage
		                        localStorage.setItem("productos", JSON.stringify(productos));
		                        localStorage.setItem("productoSeleccionado", JSON.stringify(producto));
		
		                        // Recargamos la página para mostrar los nuevos comentarios
		                        location.reload();
		                    } else {
		                        alert("El comentario no puede estar vacío");
		                    }
		                }
		            }
		        }
		    }
		
		    function agregarComentario(){
		        let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;
		
		        if(usuarioActual == null || usuarioActual == ""){
		            mostrarAlerta("No se puede escribir un comentario si no se tiene la sesión iniciada");
		            return;
		        }
		
		        let texto = document.getElementById("comentarioInput").value;
		        let cantEstrellas = document.getElementById("cantEstrellas").value;
		        const fechaActual = new Date();
		        const dia = String(fechaActual.getDate()).padStart(2, '0');
		        const mes = String(fechaActual.getMonth() + 1).padStart(2, '0');
		        const anio = fechaActual.getFullYear();
		        
		        console.log(dia);
		        console.log(mes);
		        console.log(dia);
		        
		        
		        const fechaFormateada = `${dia}/${mes}/${anio}`;
		
		        if(texto == "" || texto == null || texto == undefined) {
		            mostrarAlerta("No se puede agregar un comentario vacío");
		            return;
		        }
		
		        if(cantEstrellas < 0 || cantEstrellas > 5) {
		            mostrarAlerta("La cantidad de estrellas no es válida");
		            return;
		        }
		
		        
		    }
		
		    // Lógica para agregar un producto al carrito
		
		    function itemYaExiste(item) {
		        let carritoActual = JSON.parse(localStorage.getItem("carritoActual")) || "";
		
		        for(let producto of carritoActual) {
		            if(item.id == producto.id) {
		                return true;
		            }
		        }
		        return false;
		    }
		
		    function checkCantidad() {
		        let cantidad = parseInt(document.getElementById("cantidad-input").value) || 1;
		        if(cantidad < 1) {
		            cantidad = 1;
		        }
		        return cantidad;
		    }
		
		    document.getElementById("agregar-al-carrito").addEventListener("click", () => {
		        let carritoActual = JSON.parse(localStorage.getItem("carritoActual")) || [];
		        
		        if(itemYaExiste(productoSeleccionado)) {
		            mostrarAlerta("El item elegido ya existe en el carrito, no se volvió a agregar");
		            document.getElementById("modalTitle").innerHTML = "El item elegido ya existe en el carrito, no se volvió a agregar";
		        } else {
		            document.getElementById("modalTitle").innerHTML = "¡Producto agregado con éxito!";
		            carritoActual.push({
		                "nombre": productoSeleccionado.nombre || "",
		                "precio": productoSeleccionado.precio || 0,
		                "descripcion": productoSeleccionado.descripcion || "",
		                "imagenes": productoSeleccionado.imagenes || [],
		                "id": productoSeleccionado.id || 0,
		                "cantidad": checkCantidad() || 1
		            });
		        }
		
		        localStorage.setItem("carritoActual", JSON.stringify(carritoActual));
		    });
		
		    function revisarProductoComprado(){
		        let bool = false;
		        let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;
		        if(usuarioActual != null){
		            for(let orden of usuarioActual.ordenes){
		                for(let item of orden.productos){
		                    if(item.id == productoSeleccionado.id) {
		                        bool = true;
		                    }
		                }
		            }
		        }
		        
		        if(!bool){
		            document.getElementById("respuesta").style.display = "none";
		            const botonesResponder = document.querySelectorAll('button.btn.btn-primary.mt-2');
		
		            botonesResponder.forEach(boton => {
		                boton.disabled = true;
		                boton.style.display = "none";
		            });
		        }
		    }
		
		    revisarProductoComprado();
	    });
    </script>

</html>