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
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Catálogo</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="media/css/catalogo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp"/>

    <%
	    
	%>

	<!-- Mostrar el select de orden -->
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

	<div class="container-fluid w-100">
        <div class="row d-flex justify-content-center">
            <!-- Aside -->
            <aside id="aside" class="aside-1 scrolleable col-md-3 col-11 row ms-sm-4 ms-0">
                
            </aside>
            
            
            
            <!-- Productos -->
            <main id="prods" class="col-12 col-md ms-sm-3 ms-0">
                <article id="producto1" class="rectangle-1 row">
                
                </article>
            </main>
        </div>    
    </div>


	
<jsp:include page="/WEB-INF/template/footer.jsp" />


<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>