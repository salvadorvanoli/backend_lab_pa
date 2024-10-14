<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.util.Comparator"%>
<%@ page import="java.util.Arrays"%>
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

	<jsp:include page="/WEB-INF/template/header.jsp"/>

    <%
	    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    	String categoriasJSON = (String) request.getAttribute("categoriasJSOn");;
    	String productosJSON = (String) request.getAttribute("productosJSON");;
	
	    // Obtener el parámetro 'orden' de la URL (si existe)
	    String ordenSeleccionado = request.getParameter("orden");
	    if (ordenSeleccionado == null) {
	        ordenSeleccionado = "99"; // Valor por defecto si no hay orden especificado
	    }
	
	    // Ordenar productos según el valor de 'orden'
	    if (productos != null && !productos.isEmpty()) {
	    	switch (ordenSeleccionado) {
	            case "1": // Precio Ascendente
	                Collections.sort(productos, (p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()));
	                break;
	            case "2": // Precio Descendente
	                Collections.sort(productos, (p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()));
	                break;
	            case "3": // Alfabéticamente Ascendente
	                Collections.sort(productos, (p1, p2) -> p1.getNombreProducto().compareTo(p2.getNombreProducto()));
	                break;
	            case "4": // Alfabéticamente Descendente
	                Collections.sort(productos, (p1, p2) -> p2.getNombreProducto().compareTo(p1.getNombreProducto()));
	                break;
	            case "5": // Cantidad de compras Ascendente (si getCantCompras devuelve int)
	                Collections.sort(productos, (p1, p2) -> Integer.compare(p1.getCantCompras(), p2.getCantCompras()));
	                break;
	            case "6": // Cantidad de compras Descendente
	                Collections.sort(productos, (p1, p2) -> Integer.compare(p2.getCantCompras(), p1.getCantCompras()));
	                break;
	            default:
	                break; // Sin orden
	        }
	    }
	%>

	<!-- Mostrar el select de orden -->
	<div class="row container-fluid w-100 justify-content-end align-items-center">
	    <header class="header-1 col align-self-end">
	        <div class="text-center">
	            <form method="GET" action="<%= request.getContextPath() %>/Catalogo">
	                <select class="select-1" name="orden" onchange="this.form.submit()">
	                    <option value="99" <%= "99".equals(ordenSeleccionado) ? "selected" : "" %>>Seleccione Un Filtro (Sin orden)</option>
	                    <option value="1" <%= "1".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar por Precio Ascendente (↓)</option>
	                    <option value="2" <%= "2".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar por Precio Descendente (↑)</option>
	                    <option value="3" <%= "3".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar Alfabéticamente Ascendente (↓)</option>
	                    <option value="4" <%= "4".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar Alfabéticamente Descendente (↑)</option>
	                    <option value="5" <%= "5".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar por cantidad de compras Ascendente (↓)</option>
	                    <option value="6" <%= "6".equals(ordenSeleccionado) ? "selected" : "" %>>Ordenar por cantidad de compras Descendente (↑)</option>
	                </select>
	            </form>
	        </div>
	    </header>
	</div>

	<aside id="aside" class="aside-1 scrolleable col-3 row">
	    <%
	        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
	
	        if (categorias == null) {
	    %>
	        <p>No hay categorías disponibles.</p>
	    <%
	        } else {
	            for (Categoria categoria : categorias) {
	    %>
	    <div class="dropdown">
	        <button class="dropbtn" aria-haspopup="true" aria-expanded="false">&#9654; <%= categoria.getNombreCat() %></button>
	        <div class="dropdown-content">
	            <%
	                HashMap<String, Categoria> hijosHash = categoria.getHijos();
	                if (hijosHash != null && !hijosHash.isEmpty()) {
	                    for (Categoria subcategoria : hijosHash.values()) {
	            %>
	            <a href="#" class="dropdown-item" 
	               onclick="selectCategoria('<%= subcategoria.getNombreCat() %>', false)">&#9654; <%= subcategoria.getNombreCat() %></a>
	            <div class="sub-dropdown">
	                <%
	                    HashMap<String, Categoria> subHijosHash = subcategoria.getHijos();
	                    if (subHijosHash != null && !subHijosHash.isEmpty()) {
	                        for (Categoria subSubcategoria : subHijosHash.values()) {
	                %>
	                    <a href="#" class="dropdown-item" 
	                       onclick="selectCategoria('<%= subSubcategoria.getNombreCat() %>', false)">&#9654; <%= subSubcategoria.getNombreCat() %></a>
	                <%
	                        }
	                    }
	                %>
	            </div>
	            <%
	                    }
	                }
	            %>
	        </div>
	    </div>
	    <%
	            }
	        }
	    %>
	</aside>
	<!-- Productos -->
	<main id="prods" class="col-12 col-md">
	    <%
	        // Obtener el valor del parámetro de orden
	        String orden = request.getParameter("orden");
	        
	        // Obtener las categorías seleccionadas
	        String categoriasSeleccionadas = request.getParameter("categorias");
	        List<String> categoriasFiltrar = categoriasSeleccionadas != null 
	            ? Arrays.asList(categoriasSeleccionadas.split(",")) 
	            : new ArrayList<>();
	
	        // Filtrar productos según las categorías seleccionadas
	        List<Producto> productosFiltrados = new ArrayList<>(productos);
	
	        if (!categoriasFiltrar.isEmpty() && !categoriasSeleccionadas.equals("")) {
	            // Filtrar productos según las categorías seleccionadas
	            productosFiltrados = productos.stream()
	                .filter(producto -> {
	                    for (String categoria : categoriasFiltrar) {
	                        if (producto.getCategorias().contains(categoria)) {
	                            return true; // Producto pertenece a la categoría seleccionada
	                        }
	                    }
	                    return false; // Producto no pertenece a ninguna categoría seleccionada
	                })
	                .collect(Collectors.toList());
	        }
	
	        if(orden == null){
	        	orden = "-1";
	        }
	        
	        // Lógica para ordenar los productos filtrados
	        if (productosFiltrados != null && !productosFiltrados.isEmpty()) {
	            switch (orden) {
	                case "1":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getPrecio));
	                    break;
	                case "2":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getPrecio).reversed());
	                    break;
	                case "3":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getNombreTienda));
	                    break;
	                case "4":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getNombreTienda).reversed());
	                    break;
	                case "5":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getCantCompras));
	                    break;
	                case "6":
	                    Collections.sort(productosFiltrados, Comparator.comparing(Producto::getCantCompras).reversed());
	                    break;
	                default:
	                    break;
	            }
	
	            for (Producto producto : productosFiltrados) {
	                String imagen = (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) 
	                    ? producto.getImagenes().get(0) 
	                    : "media/images/default.webp";
	    %>
	                <article class="rectangle-1 row product" data-referencia="<%= producto.getNumReferencia() %>">
	                    <figure class="col-3">
	                        <img src="<%= producto.getImagenes().get(0) %>" class="col-3 image-1" alt="Imagen de <%= producto.getNombreTienda() != null ? producto.getNombreTienda() : "Producto" %> - <%= producto.getNumReferencia() %>">
	                    </figure>
	                    <div class="col-9 row todo-lodemas">
	                        <div class="row col-12 item-estrellas">
	                            <div class="col item-1"><%= producto.getNombreProducto() %></div>
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
	            }
	        } else {
	    %>
	            <p>No hay productos disponibles.</p>
	    <%
	        }
	    %>
	</main>


	
<jsp:include page="/WEB-INF/template/footer.jsp" />


<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="media/js/catalogo.js"></script>
</body>
</html>