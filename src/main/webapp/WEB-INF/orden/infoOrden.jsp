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

 
    <main class="container-fluid">
	    <div class="rectangle-1">
	        <div class="rectangle-2">
	            <div class="row"> 
	                <div class="col-md-2 d-flex">
	                    <!-- Imagen dinámica del producto -->
	                    <div class="fotosProductos">
	                    
	                        <img src="<%= producto.getImagen() %>" alt="Imagen del Producto">
	                    </div>   
	                </div>
	                <div class="col-md-4 d-flex flex-column justify-content-start p-0">
	                    <!-- Nombre dinámico del producto -->
	                    <h1 class="nombresProductos text-start mt-3"><%= producto.getNombre() %></h1>    
	                    <!-- Descripción dinámica del producto -->
	                    <h2 class="descripcionProductos text-start m-0"><%= producto.getDescripcion() %></h2>
	                    <!-- Precio dinámico del producto -->
	                    <h2 class="precioProductos text-start mt-3"> $<%= producto.getPrecio() %></h2>
	                </div>
	                <div class="col-md-4">
	                    <!-- Número de producto dinámico -->
	                    <h1 class="numProducto text-start mt-3">Nro. Producto: <%= producto.getNumero() %></h1>
	                    <div class="stepper-container d-flex align-items-end">
	                        <!-- Cantidad disponible dinámica -->
	                        <input type="number" id="quantity" class="stepper" value="0" min="0" max="<%= producto.getCantidadMaxima() %>" disabled>
	                        <label for="quantity" class="labelCantidad">Cantidad</label> 
	                    </div>          
	                </div>
	                <div class="col-md-2">
	                    <h1 class="textoSubtotal mt-3">Subtotal</h1>
	                    <!-- Subtotal dinámico -->
	                    <h1 class="precioProductosSubt mt-3">$<%= producto.getSubtotal() %></h1>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="rectangle-3 row">
	        <h1 class="textoResumen"> Resumen </h1>
	        <div class="horizontal-line"></div>
	        
	        <div class="col-md-6 mt-3">
	            <h1 class="subtotal"> Subtotal </h1>
	            <h1 class="envio"> Envio </h1>
	            <h1 class="impuestos"> Impuestos</h1>
	        </div>
	        <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
	            <!-- Subtotales dinámicos -->
	            <h1 class="subtotal2">$<%= carrito.getSubtotal() %></h1>
	            <h1 class="envio2"> $<%= carrito.getEnvio() %></h1>
	            <h1 class="impuestos2"> $<%= carrito.getImpuestos() %></h1>
	        </div>
	        <div class="horizontal-line"></div>
	        <div class="col-md-6 mt-3">
	            <h1 class="total"> Total </h1>
	        </div>
	        <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
	            <!-- Total dinámico -->
	            <h1 class="total2"> $<%= carrito.getTotal() %></h1>
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