<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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

	<div class="container-fluid w-100"> 
	    <div class="row">
	        <!-- Aside -->
	        <aside id="aside" class="aside-1 scrolleable col-3 row">
	            <%
	                List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
	                
	                if (categorias != null) { // Verifica que categorias no sea null
	                    for (Categoria categoria : categorias) {
	            %>
	                <div class="dropdown">
	                    <button class="dropbtn">&#9654; <%= categoria.getNombre() %></button>
	                    <div class="dropdown-content">
	                        <%
	                            List<Categoria> hijas = categoria.getHijas();
	                            if (hijas != null) { // Verifica que hijas no sea null
	                                for (Categoria subcategoria : hijas) {
	                        %>
	                            <a href="#" class="dropdown-item">&#9654; <%= subcategoria.getNombre() %></a>
	                            <%
	                                List<Categoria> subHijas = subcategoria.getHijas();
	                                if (subHijas != null) { // Verifica que subHijas no sea null
	                                    for (Categoria subsubcategoria : subHijas) {
	                            %>
	                                <a href="#" class="dropdown-item">&emsp;&emsp;&#9654; <%= subsubcategoria.getNombre() %></a>
	                            <%
	                                    }
	                                }
	                            %>
	                        <%
	                                } // Fin del for de subcategorías
	                            } else {
	                        %>
	                            <p>No hay subcategorías disponibles.</p>
	                        <%
	                            } // Fin de la verificación de hijas
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
	    </div>
	</div>


            <!-- Productos -->
    <main id="prods" class="col-12 col-md">
		<%
			List<Producto> productos = (List<Producto>) request.getAttribute("productos");
			        
			if (productos != null && !productos.isEmpty()) { // Verifica que productos no sea null y no esté vacío
				for (Producto producto : productos) {
			       // Verifica que el producto tenga imágenes antes de acceder a la primera
			       String imagen = (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) 
			       ? producto.getImagenes().get(0) 
			       : "ruta/por/defecto.jpg"; // Coloca una ruta de imagen por defecto aquí
			
		%>
		<article class="rectangle-1 row" onclick="verInfoProducto(<%= producto.getId() %>)">
			<img src="<%= imagen %>" class="col-3 image-1" alt="Imagen del producto">
				<div class="col-9 row todo-lodemas">
			    	<div class="row col-12 item-estrellas">
			        	<div class="col item-1"><%= producto.getNombre() %></div>
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

<script>
	const categor;
	const prod;
		
	// Manejar el cambio de estado de las categorías
	const categoriaLinks = document.querySelectorAll('.dropdown-item');

	if (categoriaLinks.length > 0) {
	    categoriaLinks.forEach(link => {
	        link.addEventListener('click', function(event) {
	            event.preventDefault(); // Evitar el comportamiento por defecto del enlace
	            const categoriaSeleccionada = this.innerHTML; // Obtener el nombre de la categoría

	            // Cambiar el estado de selección
	            agregarCategoria(categoriaSeleccionada, this);
	        });
	    });
	}

	// Mantener el dropdown abierto al hacer clic en el toggle
	document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
	    toggle.addEventListener('click', function(event) {
	        event.preventDefault(); // Evitar que se cierre al hacer clic
	    });
	});

	let categoriasSeleccionadas = [];

	// Función para manejar la selección de una categoría
	function agregarCategoria(categoria, boton) {
	    if (!categoriasSeleccionadas.includes(categoria)) {
	        categoriasSeleccionadas.push(categoria);
	        boton.classList.add('categoria-seleccionada'); // Añadir la clase CSS
	    } else {
	        categoriasSeleccionadas = categoriasSeleccionadas.filter(cat => cat !== categoria);
	        boton.classList.remove('categoria-seleccionada'); // Quitar la clase CSS
	    }

	    document.querySelector('.select-1').value = "99"; // Reiniciar la opción de ordenamiento a "99"
	    filtrarProductosPorCategoria(); // Filtrar productos
	}


	function buscarEnSubcategorias(categoria, categoriasSeleccionadas) {
	    if (categoriasSeleccionadas.includes(categoria.nombre)) {
	        return true; // Si la categoría actual está seleccionada, devolver true
	    }

	    // Recorrer las subcategorías (hijos)
	    if (categoria.hijos && categoria.hijos.length > 0) {
	        return categoria.hijos.some(subcategoria => buscarEnSubcategorias(subcategoria, categoriasSeleccionadas));
	    }

	    return false; // Si no se encontró en ninguna subcategoría, devolver false
	}

	// Función para filtrar productos por categorías seleccionadas
	function filtrarProductosPorCategoria() {
	    // Si no hay categorías seleccionadas, carga todos los productos
	    if (categoriasSeleccionadas.length === 0) {
	        cargarCatalogo(productos);
	        return;
	    }

	    const productosFiltrados = productos.filter(producto => {
	        return producto.categorias.some(categoria => {
	            // Función recursiva para buscar en todas las subcategorías
	            return buscarEnSubcategorias(categoria, categoriasSeleccionadas);
	        });
	    });

	    cargarCatalogo(productosFiltrados); // Cargar los productos filtrados
	}

	// Función para cargar las categorías desde localStorage
	function cargarCategorias(categor) {
	    const contenedorPadre = document.getElementById("aside");

	    // Limpiar el contenedor de categorías antes de agregar nuevas
	    contenedorPadre.innerHTML = '';

	    categor.forEach(categoria => {
	        const dropdownDiv = document.createElement('div');
	        dropdownDiv.classList.add('dropdown');

	        const button = document.createElement('button');
	        button.classList.add('dropbtn');
	        button.innerHTML = `&#9654; ${categoria.nombre}`;

	        dropdownDiv.appendChild(button);

	        const dropdownContent = document.createElement('div');
	        dropdownContent.classList.add('dropdown-content');

	        // Manejar subcategorías
	        categoria.hijas.forEach(subcategoria => {
	            const subcategoriaLink = document.createElement('a');
	            subcategoriaLink.href = "#";
	            subcategoriaLink.innerHTML = `&#9654; ${subcategoria.nombre}`;
	            subcategoriaLink.addEventListener('click', function(event) {
	                event.preventDefault(); // Evitar el comportamiento por defecto del enlace
	                agregarCategoria(subcategoria.nombre, subcategoriaLink); // Seleccionar subcategoría
	            });

	            dropdownContent.appendChild(subcategoriaLink);

	            // Manejar subcategorías de subcategorías
	            subcategoria.hijas.forEach(subsubcategoria => {
	                const subsubcategoriaLink = document.createElement('a');
	                subsubcategoriaLink.href = "#";
	                subsubcategoriaLink.innerHTML = `&emsp;&emsp;&#9654; ${subsubcategoria.nombre}`;
	                subsubcategoriaLink.addEventListener('click', function(event) {
	                    event.preventDefault(); // Evitar el comportamiento por defecto del enlace
	                    agregarCategoria(subsubcategoria.nombre, subsubcategoriaLink); // Seleccionar subsubcategoría
	                });

	                dropdownContent.appendChild(subsubcategoriaLink);
	            });
	        });

	        dropdownDiv.appendChild(dropdownContent);
	        contenedorPadre.appendChild(dropdownDiv);
	    });
	}

	// Llamar a cargarCategorias cuando sea necesario, por ejemplo al cargar la página
	document.addEventListener("DOMContentLoaded", function() {
	    cargarCategorias(categor);
	    cargarCatalogo(prod); // Carga el catálogo inicialmente
	    
	    // Manejar el cambio de ordenamiento
	    document.querySelector('.select-1').addEventListener('change', function() {
	        const ordenSeleccionado = this.value;
	        ordenarProductos(ordenSeleccionado); // Llama a la función de ordenar productos
	    });
	});

	// Función para ordenar productos
	function ordenarProductos(orden) {
	    let productosFiltrados = productos; // Usar todos los productos inicialmente

	    // Filtrar productos por categorías seleccionadas si hay alguna
	    if (categoriasSeleccionadas.length > 0) {
	        productosFiltrados = productos.filter(producto => {
	            // Verificar si el producto pertenece a alguna de las categorías seleccionadas
	            return producto.categorias.some(categoria => {
	                // Función recursiva para buscar en todas las subcategorías
	                return buscarEnSubcategorias(categoria, categoriasSeleccionadas);
	            });
	        });
	    }

	    // Ordenar los productos según la opción seleccionada
	    if (orden === "1") {
	        productosFiltrados.sort((a, b) => a.precio - b.precio); // Ordenar por precio ascendente
	    } else if (orden === "2") {
	        productosFiltrados.sort((a, b) => b.precio - a.precio); // Ordenar por precio descendente
	    } else if (orden === "3") {
	        productosFiltrados.sort((a, b) => a.nombre.localeCompare(b.nombre)); // Ordenar por nombre ascendente
	    } else if (orden === "4") {
	        productosFiltrados.sort((a, b) => b.nombre.localeCompare(a.nombre)); // Ordenar por nombre descendente
	    } else if (orden === "5") {
	        productosFiltrados.sort((a, b) => a.cantCompras - b.cantCompras); // Ordenar por cantidad de compras ascendente
	    } else if (orden === "6") {
	        productosFiltrados.sort((a, b) => b.cantCompras - a.cantCompras); // Ordenar por cantidad de compras descendente
	    }

	    cargarCatalogo(productosFiltrados); // Cargar los productos ordenados
	}

	function verInfoProducto(id) {
	    for(let item of prod) {
	        if(item.id == id){
	            localStorage.setItem("productoSeleccionado", JSON.stringify(item));
	            window.location.href = "infoProducto.html";
	        }
	    }
	}

	// Función para cargar el catálogo
	function cargarCatalogo(prod) {
	    const contenedorPadre = document.getElementById("prods");
	    contenedorPadre.innerHTML = ''; // Limpiar el contenedor antes de agregar los productos

	    if (prod.length === 0) {
	        return;
	    }

	    prod.forEach(element => {
	        const Rectangulo = document.createElement("article");
	        const nuevaImagen = document.createElement("img");
	        const Demas = document.createElement("div");

	        const padreItemEstrellas = document.createElement("div");
	        const Titulo = document.createElement("div");
	        const ConjuntoEstrellas = document.createElement("div");

	        const nuevaTienda = document.createElement("div");

	        const padrePrecioCarrito = document.createElement("div");
	        const Precio = document.createElement("div");
	        const PadreCarrito = document.createElement("div");
	        const Carrito = document.createElement("div");

	        nuevaImagen.src = element.imagenes[0]; // Usar la primera imagen

	        let estrellasMarcadas = element.estrellas;

	        for (let i = 1; i <= 5; i++) {
	            let star = document.createElement("span");
	            if (estrellasMarcadas > 0) {
	                star.classList.add("fa", "fa-star", "checked");
	                estrellasMarcadas--;
	            } else {
	                star.classList.add("fa", "fa-star");
	            }
	            ConjuntoEstrellas.appendChild(star);
	        }

	        // Verificar y agregar contenido
	        if (element.nombre) Titulo.innerHTML = element.nombre;
	        if (element.precio) Precio.innerHTML = "$UYU " + element.precio;
	        nuevaTienda.innerHTML = element.tienda || "Tienda no disponible"; 

	        // Agregar clases o atributos si es necesario
	        Rectangulo.classList.add("rectangle-1", "row");
	        Rectangulo.onclick = function() {
	            verInfoProducto(element.id);
	        };

	        nuevaImagen.classList.add("col-3", "image-1");
	        Demas.classList.add("col-9", "row", "todo-lodemas");

	        padreItemEstrellas.classList.add("row", "col-12", "item-estrellas");
	        Titulo.classList.add("col", "item-1");
	        ConjuntoEstrellas.classList.add("col", "conjunto_estrellas");
	        nuevaTienda.classList.add("tienda-x", "col-12");

	        padrePrecioCarrito.classList.add("row", "col-12", "precio-carrito");
	        Precio.classList.add("col", "precio");

	        PadreCarrito.classList.add("col-2", "row");
	        Carrito.classList.add("col", "carrito", "fa-solid", "fa-cart-shopping");

	        Carrito.addEventListener('click', function() {
	            let array = [element];
	            cargarElementosCarrito(array);
	        });

	        // Meter los contenedores en el contenedor principal
	        padreItemEstrellas.appendChild(Titulo);
	        padreItemEstrellas.appendChild(ConjuntoEstrellas);

	        PadreCarrito.appendChild(Carrito);
	        padrePrecioCarrito.appendChild(Precio);
	        padrePrecioCarrito.appendChild(PadreCarrito);

	        Demas.appendChild(padreItemEstrellas);
	        Demas.appendChild(nuevaTienda);
	        Demas.appendChild(padrePrecioCarrito);

	        Rectangulo.appendChild(nuevaImagen);
	        Rectangulo.appendChild(Demas);

	        contenedorPadre.appendChild(Rectangulo);
	    });
	}

</script>
</html>
