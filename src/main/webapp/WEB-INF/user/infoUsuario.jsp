<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="media/css/infoUsuario.css">
    <%@page import="com.flamingo.models.Usuario"%>
    <%@page import="com.flamingo.models.Cliente"%>
    <%@page import="com.flamingo.models.Proveedor"%>
    <%@page import="com.flamingo.models.OrdenDeCompra"%>
    <%@page import="com.flamingo.models.Producto"%>
    <%@page import="com.flamingo.models.OrdenDeCompra"%>
    <%@ page import="java.util.List" %>
</head>
<body>

	<%
    	Usuario usuarioActual = (Usuario) request.getAttribute("usuarioActual");
	
		if(usuarioActual == null) {
			response.sendRedirect("backend_lab_pa/iniciarsesion");
	        return;
		}
	%>
    
    <jsp:include page="/WEB-INF/template/header.jsp" />
    
    <main>

        <section id="cliente-section">

            <h1>Informaci�n personal</h1>

            <div class="container-md container-fluid">

                <form>
                    <div class="row">
                        <div class="col-md-6 col-12">
            
                            <div class="form-group d-flex">
                                <div class="w-50 me-2">
                                	<label for="inputNombre" class=" ">Nombre</label>
                                    <input type="text" class="form-control mt-2" value="<%= usuarioActual.getNombre() %>" id="inputNombre" placeholder="Ingrese su nombre" disabled>
                                </div>
                                <div class="w-50">
                                	<label for="inputApellido" class=" ">Apellido</label>
                                    <input type="text" class="form-control mt-2" value="<%= usuarioActual.getApellido() %>" id="inputApellido" placeholder="Ingrese su apellido" disabled>
                                </div>
                            </div>
            
                            <div class="form-group mt-3">
                            	<label for="inputNickname" class=" ">Nickname</label>
                                <input type="text" class="form-control mt-2" value="<%= usuarioActual.getNickname() %>" id="inputNickname" placeholder="Ingrese su nickname" disabled>
                            </div>
            
                            <div class="form-group mt-3">
                            	<label for="inputEmail" class=" ">Email</label>
                                <input type="email" class="form-control mt-2" value="<%= usuarioActual.getEmail() %>" id="inputEmail" placeholder="Ingrese su correo electr�nico" disabled>
                            </div>

                            <div class="form-group mt-3">
                                <div class="mr-2 w-50">
                                	<label for="inputFecha" class=" ">Fecha de nacimiento</label>
                                    <input type="date" value="<%= usuarioActual.getFechaNac().getFechaEnFormatoInput() %>" class="form-control mt-2" id="inputFecha" disabled>
                                </div>
                            </div>

                            <div class="form-group d-flex">
                                <!--<div class="mr-2 w-50">
                                    <button type="button" onclick="if(revisarDatos()){ window.location.reload(); }" class="btn btn-primary botonRosado mt-2" id="actualizar-datos-usuario">Actualizar datos</button>
                                </div> -->
                                <div class="w-50">
                                    <button class="btn btn-primary botonRosado mt-2" type="submit" id="cerrar-sesion">Cerrar sesi�n</button>
                                </div>
                            </div>
                        </div>
            
                        <div class="col-md-6 col-12 centro">
                            <div> 
                                <img src="<%= usuarioActual.getFoto() != null && !usuarioActual.getFoto().isEmpty() ? usuarioActual.getFoto() : "media/images/Chica1.png" %>" alt="Foto de perfil" id="fotoPerfilUsuario" class="img-fluid">
                            </div>
                            <!--<div>
                                <input type="file" id="inputFile" class="btn btn-secondary mt-2" accept=".png, .jpg, .jpeg, .webp">
                            </div>  -->
                        </div>
                    </div>
                </form>
            </div>
        </section>


		<%
	    // Verifica si el usuarioActual es de tipo Cliente
	    if (usuarioActual instanceof Usuario) {
	    	Usuario usuario = (Usuario) usuarioActual;
		%>
		
			<div class="linea-resumen"></div>
		
	        <section id="ver-orden-section">
	            <h2>Ver orden de compra</h2>
	
	            <div class="container-md container-fluid">
	                <div class="row">
	                    <div id="ver-orden-container">
	                        <div class="form-group d-flex align-items-center">
	                            <div class="mr-2 w-100">
	                                <label for="selectOrdenes">Seleccione una orden de compra</label>
	                                <select class="form-control mt-2" id="selectOrdenes">
	                                
	                                <%
                                    for (OrdenDeCompra orden : usuario.getOrdenesDeCompras()) { // Aseg�rate de que 'getOrdenes' retorne una lista de �rdenes
	                                %>
	                                
	                                    <option value="<%= orden.getNumero() %>">Orden <%= orden.getNumero() %> - Fecha <%= orden.getFecha().getFechaEnFormatoInput() %></option>
	                                    
	                                <%
                                    }
	                                %>
	                                
	                                </select>
	                            </div>
	                        </div>
	                        <button type="button" onclick="window.location.href = 'VerOrdenDeCompra?ordenId=' + document.getElementById('selectOrdenes').value;" class="btn btn-primary botonRosado mt-2" id="ver-orden-btn">Ver orden</button>
	                    </div>
	                </div>
	            </div>
	        </section>
		<%
	    }
		%>
		
        <div class="linea-resumen"></div>

		<%
	    // Verifica si el usuarioActual es de tipo Cliente
	    if (usuarioActual instanceof Proveedor) {
	    	Proveedor proveedor = (Proveedor) usuarioActual;
		%>
        <section id="proveedor-section">
			<h2>
   	            Informaci�n Proveedor
   	        </h2>

   	        <div class="container-md container-fluid">
   	            <div class="row">
   	                <div id="info-proveedor-container">
   	                    <div class="form-group d-flex align-items-center">
   	                        <div class="mr-2 w-100 mb-5">
   	                        	<label for="inputSitioWeb" class="mt-3">Sitio web</label>
   	                            <input type="text" value="<%= proveedor.getlink() %>" class="form-control mt-2" id="inputSitioWeb" placeholder="" disabled>
   	                            <label for="inputCompa��a" class="mt-3">Nombre de la compa��a</label>
   	                            <input type="text" value="<%= proveedor.getnomCompania() %>" class="form-control mt-2 mb-2" id="inputCompa��a" placeholder="" disabled>
   	                        </div>
   	                    </div>
   	                </div>
   	            </div>
   	        </div>

   	        <div id="productos-proveedor-container">
   	            
 				<%
			        // Supongamos que tienes una lista de productos en la variable "productos"
			        List<Producto> productos = (List<Producto>) proveedor.getProductos();
			
			        if (productos != null) {
			            for (Producto producto : productos) {
			    %>
			                <div class="container-md container-fluid product-card" onclick="window.location.href='infoProducto?productoSeleccionado=<%= producto.getNumReferencia() %>'">
			                    <div class="row">
			                        <div class="col-md-3 col-12">
			                            <img src="<%= (producto.getImagenes() != null && !producto.getImagenes().isEmpty() && producto.getImagenes().get(0) != "") ? producto.getImagenes().get(0) : "media/images/default.webp" %>" alt="Imagen producto" class="img-producto">
			                        </div>
			                        <div class="col-md-8 col-12">
			                            <div>
			                                <h4 class="titulo-producto"><%= producto.getNombreProducto() %></h4>
			                                <p class="descripcion-producto"><%= producto.getDescripcion() %></p>
			                                <div class="precio-producto">
			                                    $<%= producto.getPrecio() %>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			    <%
			            }
			        }
			    %>
   	            
   	        </div>

   	        <div class="container-md container-fluid">
   	            <div class="row">
   	                <div id="boton-agregar-producto">
   	                    <div class="form-group d-flex align-items-center">
   	                        <div class="mr-2 w-100">
   	                            <button type="button" class="btn btn-primary botonRosado mt-2" onclick="window.location.href = 'registrarProducto';" id="agregar-producto-btn">Agregar producto</button>
   	                        </div>
   	                    </div>
   	                </div>
   	            </div>
   	        </div>
        </section>
		<%
	    }
		%>

    </main>

    <jsp:include page="/WEB-INF/template/footer.jsp" />
	<script>
	
		document.getElementById("cerrar-sesion").addEventListener("click", () => {
		    fetch("http://localhost:8080/backend_lab_pa/cerrarSesion", {
		        method: "POST",
		        headers: {
		            "Content-Type": "application/json",
		        },
		    })
		    .then(response => {
		        if (response.ok) {
		            window.location.href = "home";
		        } else {
		            throw new Error('Error al cerrar sesi�n');
		        }
		    })
		    .catch(error => {
		        // console.error('Error:', error);
		    });
		});
		
	</script>
	

</html>