<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ingresarDatos</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="media/css/infoOrdenCompra.css">
</head>
<body>

     <!-- NAVBAR -->
	<jsp:include page="/WEB-INF/template/header.jsp"/>

	<%@ page import="com.flamingo.models.Producto" %>
	<%@ page import="com.flamingo.models.Usuario" %>
	<%@ page import="java.util.List" %>
 
   	<%
	    // Obtener el usuario y la lista de productos desde el request
	    Usuario usuarioActual = (Usuario) request.getAttribute("usuarioActual");
	    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
	%>

	<main class="container-fluid">
	    <div class="rectangle-1">
	        <div class="rectangle-2">
	            <div class="row">
	                <div class="col-md-2 d-flex flex-column">
	                    <!-- Comprobar si hay productos disponibles -->
	                    <% if (productos != null && !productos.isEmpty()) { %>
	                        <% for (Producto producto : productos) { %>
	                            <div class="fotosProductos">
	                                <!-- Iterar sobre la lista de im�genes -->
	                                <% 
	                                    List<String> imagenes = producto.getImagenes(); // Asumiendo que tienes un m�todo getImagenes()
	                                    if (imagenes != null && !imagenes.isEmpty()) {
	                                        for (String imagen : imagenes) { 
	                                %>
	                                            <img src="<%= imagen != null ? imagen : "media/images/default.webp" %>" alt="Imagen del Producto">
	                                <% 
	                                        }
	                                    } else { 
	                                %>
	                                        <img src="media/images/default.webp" alt="Imagen del Producto no disponible">
	                                <% 
	                                    } 
	                                %>
	                            </div>
	                            <h1 class="nombresProductos text-start mt-3"><%= producto.getNombreProducto() != null ? producto.getNombreProducto() : "Nombre no disponible" %></h1>
	                            <h2 class="descripcionProductos text-start m-0"><%= producto.getDescripcion() != null ? producto.getDescripcion() : "Descripci�n no disponible" %></h2>
	                            <h2 class="precioProductos text-start mt-3">$<%= producto.getPrecio() >= 0 ? producto.getPrecio() : "0" %></h2>
								<h1 class="numProducto text-start mt-3">Nro. Producto: <%= producto.getNumReferencia() > 0 ? producto.getNumReferencia() : "N/A" %></h1>
	                            <h1 class="precioProductosSubt mt-3"></h1>
	                        <% } %>
	                    <% } else { %>
	                        <h1>No hay productos disponibles.</h1>
	                    <% } %>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="rectangle-3 row">
	        <h1 class="textoResumen"> Resumen </h1>
	        <div class="horizontal-line"></div>
	        <div class="col-md-6 mt-3">
			    <h1 class="subtotal">Subtotal</h1>
			    <h1 class="envio">Env�o</h1>
			    <h1 class="impuestos">Impuestos</h1>
			</div>
			<div class="col-md-6 mt-3 d-flex flex-column align-items-end">
			    <h1 class="subtotal2">$${carrito != null ? carrito.getSubtotal() : '0'}</h1>
			    <h1 class="envio2">$${carrito != null ? carrito.getEnvio() : '0'}</h1>
			    <h1 class="impuestos2">$${carrito != null ? carrito.getImpuestos() : '0'}</h1>
			</div>
	        <div class="horizontal-line"></div>
	        <div class="col-md-6 mt-3">
	            <h1 class="total"> Total </h1>
	        </div>
	        <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
	            <!-- Total din�mico con validaci�n del carrito nulo -->
	            <h1 class="total2">$${carrito != null ? carrito.getTotal() : '0'}</h1>
	        </div>
	        <div class="col-md-12 mt-5 d-flex flex-column align-items-end">
	            <button type="button" class="button-volver" id="volver"> Volver </button>
	        </div>
	    </div>
	</main>
    
    <!-- ENCABEZADO DE FASES -->
	<jsp:include page="/WEB-INF/template/footer.jsp"/>


</body>

<script src="js/infoOrdenCompra.js"></script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="media/js/infoOrdenCompra.js"></script>
</html>