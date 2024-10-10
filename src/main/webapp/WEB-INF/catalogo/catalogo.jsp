<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.flamingo.models.Producto" %>
<%@ page import="com.flamingo.models.Categoria" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="media/css/catalogo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp" />

     <!-- Ordenar por -->
    <div class="row container-fluid w-100 justify-content-end align-items-center">
        <header class="header-1 col align-self-end">
            <div class="text-center">
                <select class="select-1">
                    <option selected value="99">Seleccione Un Filtro (Sin orden)</option>
                    <option value="1">Ordenar por Precio Ascendente (↓)</option>
                    <option value="2">Ordenar por Precio Descendente (↑)</option>
                    <option value="3">Ordenar Alfabéticamente Ascendente (↓)</option>
                    <option value="4">Ordenar Alfabéticamente Descendente (↑)</option>
                    <option value="5">Ordenar por cantidad de compras Ascendente (↓)</option>
                    <option value="6">Ordenar por cantidad de Descendente (↑)</option>
                </select>
            </div>
        </header>
    </div>


	<aside id="aside" class="aside-1 scrolleable col-3 row">
	    <%
	        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
	        
	        if (categorias != null) { // Verifica que categorias no sea null
	            for (Categoria categoria : categorias) {
	    %>
	            <div class="dropdown">
	                <button class="dropbtn" aria-haspopup="true" aria-expanded="false">&#9654; <%= categoria.getNombreCat() %></button>
	                <div class="dropdown-content">
	                    <%
	                    HashMap<String, Categoria> hijosHash = categoria.getHijos();
	                    
	                        if (hijosHash != null && !hijosHash.isEmpty()) { // Verifica que hijos no sea null y no esté vacío
	                            ArrayList<Categoria> hijos = new ArrayList<>(hijosHash.values());
	                            for (Categoria subcategoria : hijos) {
	                    %>
	                                <a href="#" class="dropdown-item">&#9654; <%= subcategoria.getNombreCat() %></a>
	                                <%
	                                HashMap<String, Categoria> subhijosHash = subcategoria.getHijos();
	                                ArrayList<Categoria> subhijos = new ArrayList<>(subhijosHash.values());
	                                    if (subhijos != null && !subhijos.isEmpty()) { // Verifica que subhijos no esté vacío
	                                        for (Categoria subsubcategoria : subhijos) {
	                                %>
	                                            <a href="#" class="dropdown-item">&emsp;&emsp;&#9654; <%= subsubcategoria.getNombreCat() %></a>
	                                <%
	                                        }
	                                    } else {
	                                %>
	                                        <p>No hay subsubcategorías disponibles.</p>
	                                <%
	                                    } // Fin de la verificación de subhijos
	                                %>
	                            <%
	                            } // Fin del for de subcategorías
	                        } else {
	                    %>
	                            <p>No hay subcategorías disponibles.</p>
	                    <%
	                        } // Fin de la verificación de hijos
	                    %>
	                </div>
	            </div>
	    <%
	            } // Fin del for de categorías
	        } else {
	    %>
	            <p>No hay categorías disponibles.</p>
	    <%
	        } // Fin de la verificación de categorías
	    %>
	</aside>


            <!-- Productos -->
    <main id="prods" class="col-12 col-md">
	    <%
	        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
	            
	        if (productos != null && !productos.isEmpty()) { // Verifica que productos no sea null y no esté vacío
	            for (Producto producto : productos) {
	                String imagen = (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) 
	                ? producto.getImagenes().get(0) 
	                : "media/images/default.webp"; // Coloca una ruta de imagen por defecto aquí
	    %>
	                <article class="rectangle-1 row product" data-referencia="<%= producto.getNumReferencia() %>">
	                    <figure class="col-3">
	                        <img src="<%= imagen %>" class="image-1" alt="Imagen de <%= producto.getNombreTienda() %> - <%= producto.getNumReferencia() %>">
	                        <figcaption><%= producto.getNombreTienda() %></figcaption>
	                    </figure>
	                    <div class="col-9 row todo-lodemas">
	                        <div class="row col-12 item-estrellas">
	                            <div class="col item-1"><%= producto.getNumReferencia() %></div>
	                            <div class="col conjunto_estrellas">
	                                <%
	                                    for (int i = 1; i <= 5; i++) {
	                                %>
	                                    <span class="fa fa-star <%= (i <= producto.getEstrellas() ? "checked" : "") %>"></span>
	                                <%
	                                    }
	                                %>
	                            </div>
	                        </div>
	                        <div class="tienda-x col-12"><%= producto.getNombreTienda() %></div>
	                        <div class="row col-12 precio-carrito">
	                            <div class="col precio">$UYU <%= producto.getPrecio() %></div>
	                            <div class="col-2 row">
	                                <div class="col carrito fa-solid fa-cart-shopping"></div>
	                            </div>
	                        </div>
	                    </div>
	                </article>
	    <%
	            } // Fin del for de productos
	        } else {
	    %>
	    	<p>No hay productos disponibles.</p>
	    <%
	        } // Fin de la verificación de productos
	    %>
	  
	</main> 
</body>

<jsp:include page="/WEB-INF/template/footer.jsp" />

<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="media/js/catalogo.js"></script>
</html>
