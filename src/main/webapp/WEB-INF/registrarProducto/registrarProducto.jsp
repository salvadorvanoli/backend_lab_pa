<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.flamingo.models.Producto"%>
<%@page import="com.flamingo.models.Categoria"%>
<%@page import="com.flamingo.models.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>



<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Producto</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/registrarProducto.css">
	<style>
		
	

		.dropdown {
		    position: relative;
		    display: inline-block;
		}

		.dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #a9a9a9; /* Fondo gris */
		    width: 300px;
		    z-index: 100; /* Asegura que el menú esté por encima de otros elementos */
		    border: 1px solid black;
		    overflow: visible; /* Asegura que el contenido no se oculte si sale del área */
		}

		.dropdown-content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		}

		
		.dropdown-content a:hover {
		    background-color: #f1f1f1;
		}

	
		.dropdown:hover .dropdown-content {
		    display: block;
		}

		
		.nested-dropdown {
		    position: relative;
		}

		
		.nested-dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 160px;
		    left: 100%; /* Alinea el submenú a la derecha del elemento padre */
		    top: 0; /* Comienza desde la parte superior del padre */
		    z-index: 200; /* Asegura que el submenú esté por encima */
		}

		
		.nested-dropdown:hover .nested-dropdown-content {
		    display: block;
		}

		
		.dropbtn {
		    background-color: #d3d3d3; /* Fondo gris claro */
		    color: black; /* Color del texto */
		    padding: 0 40px;
		    font-size: 14px;
		    border: 1px solid black;
		    cursor: pointer;
		    width: 100%;
		    height: 30px;
		    margin: 0;
		    line-height: 30px;
		    text-align: left;
		}

		.dropbtnW {
		    background-color: white; 
		    color: black; 
		    padding: 0 40px; 
		    font-size: 14px; 
		    border: 1px solid black;
		    cursor: pointer;
		    width: 100%; 
		    height: 30px; 
		    margin: 0; 
		    line-height: 30px;
		    text-align: left; 
		}



	</style>
	    
	<jsp:include page="/WEB-INF/template/header.jsp" />

  
	<!--Formulario a llenar-->
	<div class="container-md container-fluid">
	    <div class="row">
	        <div class="col-md-4 col-12 mt-4">
	            <h2 class="registrar_producto">Registrar producto</h2>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-md-4 col-12 mt-2">
	            <input type="text" id="tituloProducto" class="form-control" placeholder="Título del producto">
	        </div>
	        <div class="col-md-4 col-12 mt-2">
	            <input type="text" id="numeroReferencia" class="form-control" placeholder="Número de referencia">
	        </div>
	        <div class="col-md-2 col-12 mt-2">
	            <input type="text" id="precioProducto" class="form-control" placeholder="Precio ($UY)">
	        </div>
	    </div>
	    <div class="row">
	       <div class="col-md-8 col-12 mt-2 mt-md-4">
	            <input type="text" id="descripcionProducto" class="form-control" placeholder="Descripción">
	        </div>
	    </div>
	</div>

				
		
		<!-- Especificaciones y selección de producto-->    
		<div class="container-md container-fluid">
		    <div class="row">
		        <!-- Especificaciones Table -->
				<div class="col-md-8 col-12 mt-4">
				    <div class="tabla">
				        <table style="width: 100%; height: 100%; border-collapse: collapse;">
				            <thead>
				                <tr>
				                    <th style="border: 1px solid #C0C0C0; padding: 8px; background-color: #d3d3d3; text-align: center; font-size: 20px;">Especificación</th>
				                </tr>
				            </thead>
				            <tbody>
				                <tr>
				                    <td style="border: 1px solid #C0C0C0; padding: 8px;">
				                        <input type="text" class="form-control" style="border: none; outline: none;"/>
				                    </td>
				                </tr>
				                <tr>
				                    <td style="border: 1px solid #C0C0C0; padding: 8px;">
				                        <input type="text" class="form-control" style="border: none; outline: none;"/>
				                    </td>
				                </tr>
				                <tr>
				                    <td style="border: 1px solid #C0C0C0; padding: 8px;">
				                        <input type="text" class="form-control" style="border: none; outline: none;"/>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </div>
				</div>


		        
		        <!-- Imagen del producto y botón -->
				<div class="col-12 col-md-5 col-lg-3 mt-4"> 
				    
				        <div class="rectangulo">          
				            

				        </div>
				    </div>
					<div class="row mt-2"> 
					   <div class="col-md-5 offset-lg-8 col-12 offset-0 d-flex mb-4 mb-lg-0 mt-md-0">
					        <button class="adjuntar">Adjuntar imagen</button>
					    </div>
					</div>

				</div>


		    </div>
		</div>


	<!-- Cargar menú de categorías -->
<div class="container-md container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h2 class="elegir_categoria">Seleccionar categoría</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-12">
            <div class="vertical-menu-1">
                <div class="row">
                    <% 
                        // Obtener el objeto de categorías desde el request
                        Object categoriasObj = request.getAttribute("categorias");
                        HashMap<String, Categoria> categorias = null;

                        // Verificar si el objeto es un HashMap y hacer el cast apropiado
                        if (categoriasObj instanceof HashMap<?,?>) {
                            categorias = (HashMap<String, Categoria>) categoriasObj;
                        }

                        if (categorias != null) {
                            // Iterar sobre los valores del HashMap (es decir, las instancias de Categoria)
                            for (Categoria categoria : categorias.values()) {
                                
                                // Determinar la clase CSS según si tiene subcategorías o no
                                String categoriaClase = categoria.tieneSubcategorias() ? "dropbtn" : "dropbtnW";
                    %>
                    <!-- Mostrar categoría principal -->
                    <div class="col-md-12 col-12">
                        <div class="row">
                            <div class="dropdown">
                                <button class="<%= categoriaClase %>" onclick="agregarCategoria('<%= categoria.getNombreCat() %>')">
                                    <%= categoria.tieneSubcategorias() ? "&#9654;" : "" %> <%= categoria.getNombreCat() %>
                                </button>

                                <!-- Si tiene subcategorías, las mostramos aquí -->
                                <% if (categoria.tieneSubcategorias()) { %>
                                <div class="dropdown-content">
                                    <% for (Categoria subcategoria : categoria.getHijos().values()) { %>
                                    <div class="nested-dropdown">
                                        <a href="#" onclick="agregarCategoria('<%= subcategoria.getNombreCat() %>')">
                                            <%= subcategoria.tieneSubcategorias() ? "&#9654;" : "" %> <%= subcategoria.getNombreCat() %>
                                        </a>
                                        <!-- Si hay sub-subcategorías, las mostramos aquí -->
                                        <% if (subcategoria.tieneSubcategorias()) { %>
                                        <div class="nested-dropdown-content">
                                            <% for (Categoria subSubCategoria : subcategoria.getHijos().values()) { %>
                                            <a href="#" onclick="agregarCategoria('<%= subSubCategoria.getNombreCat() %>')">
                                                <%= subSubCategoria.getNombreCat() %>
                                            </a>
                                            <% } %>
                                        </div>
                                        <% } %>
                                    </div>
                                    <% } %>
                                </div>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <% 
                            } // Fin del for de categorías
                        } 
                    %>
                </div>
            </div>
        </div>
   

			
			
			<div class="col-12 col-md-5 col-lg-3 mt-4 mt-lg-0">
			    <div class="verCategorias">
			        <ul class="list-group list-group-flush text-start">
			           
			        </ul>            
			    </div>          
			</div>

		  </div>
		</div>
		
		<!-- Botones de cancelar y registrar-->
		<div class="container-md container-fluid">
		    <div class="row">
		       <div class="col-md-12 col-12 mt-md-4 mt-ms-4 mt-4 mt-md-0 mt-sm-0 offset-lg-7">
				<button class="btn cancelar ms-0 me-5 mb-3 mt-2 mt-md-0 ms-lg-4 mb-md-0">Cancelar</button>

		            <button class="btn registrar  mb-3 mb-md-0" id="registrarProducto">Registrar</button>
		        </div>
		    </div>
		</div>


		<script>
		    // Espera a que el documento esté completamente cargado
		    document.addEventListener('DOMContentLoaded', function() {
		        // Selecciona todos los enlaces dentro de .dropdown-content
		        const links = document.querySelectorAll('.dropdown-content a');

		        links.forEach(link => {
		            link.addEventListener('click', function(event) {
		                event.preventDefault(); // Previene que el enlace desplace la página
		               
		            });
		        });
		    });

		    
		</script>




	<!-- MODAL -->
	<div class="modal fade" id="modalCarrito" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalTitle">¡Producto agregado con éxito!</h5>
	            </div>
	            <div class="modal-body">
	                <p>¿Qué deseas hacer ahora?</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" onclick="reloadPage()">Agregar otro producto</button>
	                
	            </div>
	        </div>
	    </div>
	</div>


	<form id="formProducto" action="/backend_lab_pa/registrarProducto" method="POST" style="display:none;">
    <input type="hidden" name="id" id="productoId">
    <input type="hidden" name="nombre" id="productoNombre">
    <input type="hidden" name="precio" id="productoPrecio">
    <input type="hidden" name="categorias" id="productoCategorias">
    <input type="hidden" name="especificaciones" id="productoEspecificaciones">
    <input type="hidden" name="imagenes" id="productoImagenes">
	</form>



	<script>
	
	function cargarProductoEnFormulario() {
	    // Asignar los valores del objeto nuevoProducto al formulario
	    document.getElementById('productoId').value = nuevoProducto.id;
	    document.getElementById('productoNombre').value = nuevoProducto.nombre;
	    document.getElementById('productoPrecio').value = nuevoProducto.precio;
	    document.getElementById('productoCategorias').value = JSON.stringify(nuevoProducto.categorias);
	    document.getElementById('productoEspecificaciones').value = JSON.stringify(nuevoProducto.especificaciones);
	    document.getElementById('productoImagenes').value = JSON.stringify(nuevoProducto.imagenes);
	    
	}
	
	
	function mostrarAlerta(mensaje) {
	    console.log("Mensaje recibido:", mensaje); // Para verificar si el mensaje llega
	    let alertaExistente = document.getElementById("alerta");
	    
	    if (alertaExistente) {
	        alertaExistente.remove();
	    }

	    const alerta = document.createElement("div");
	    alerta.className = "alert alert-danger absoluto";
	    alerta.setAttribute("role", "alert");
	    alerta.id = "alerta";
	    alerta.innerHTML = `
	        ${mensaje}
	        <button type="button" class="close" aria-label="Close" onclick="this.parentElement.remove();">
	            <span aria-hidden="true">&times;</span>
	        </button>
	    `;

	    document.getElementsByTagName("body")[0].appendChild(alerta);
	}

	
	// Genera ID del producto random
	function generarIdRandom() {
	    return 'id-' + Date.now() + '-' + Math.floor(Math.random() * 10000);
	}
	
	// Creo producto vacío solo con el proveedor y un id única (no sé se tiene que ser así lo de la ID)
	let nuevoProducto = {
	    id: generarIdRandom(),
	    nombre: "",
	    precio: 0,
	    numeroRef: 0,
	    categorias: [],
	    especificaciones: [],
	    imagenes: [],
	    comentarios: []
	};


	
	    function reloadPage() {
	        location.reload();
	    }

	    window.onload = function() {
	        window.scrollTo(0, 0);
	    };
	    
	    
	    document.addEventListener('DOMContentLoaded', function() {
	        const adjuntarButton = document.querySelector('.adjuntar');
	        const contenedorImagenes = document.querySelector('.rectangulo');

	        adjuntarButton.addEventListener('click', function() {
	            const input = document.createElement('input');
	            input.type = 'file';
	            input.accept = 'image/*'; 

	            input.addEventListener('change', function(event) {
	                const archivos = event.target.files; 
	                
	                // Verifica si hay archivos seleccionados
	                if (archivos.length > 0) {
	                    for (const archivo of archivos) {
	                        const reader = new FileReader();
	                        
	                        reader.onload = function(e) {
	                            // Crea un nuevo div para la imagen
	                            const newRow = document.createElement('div');
	                            newRow.className = 'row my-3 d-flex align-items-right justify-content-right';

	                            // Crea la etiqueta img
	                            const img = document.createElement('img');
	                            img.src = e.target.result; // Establecer la fuente de la imagen
	                            img.className = 'col-sm-10 col-10 imagen-producto img-fluid'; // Agregar clases para estilos

	                            // Añade la ruta de la imagen al arreglo de nuevoProducto
	                            nuevoProducto.imagenes.push(e.target.result); // Guardar la ruta en el arreglo de nuevoProducto

	                            // Muestra la ruta en consola (opcional)
	                            console.log('Ruta de la imagen guardada en nuevoProducto:', e.target.result);
	                            
	                            // Añade la imagen al nuevo div
	                            newRow.appendChild(img);

	                            // Añade el nuevo div al contenedor de imágenes
	                            contenedorImagenes.appendChild(newRow);
	                        };

	                        // Lee la imagen como Data URL
	                        reader.readAsDataURL(archivo);
	                    }
	                }
	            });

	            // Simula un clic en el input para abrir el selector de archivos
	            input.click();
	        });
	    });
	
	    
	    function agregarCategoria(categoria) {
	        // Verifica si la categoría no está ya en el producto
	        if (!nuevoProducto.categorias.includes(categoria)) {
	            // Agrega la categoría al producto
	            nuevoProducto.categorias.push(categoria);
	            console.log(`Categoría "${categoria}" agregada al producto.`);

	            // Crea un nuevo elemento de lista (li) para la categoría
	            const li = document.createElement('li');
	            li.className = 'list-group-item';

	            // Añadir el nombre de la categoría al elemento li
	            const textoCategoria = document.createTextNode(categoria);
	            li.appendChild(textoCategoria);

	            // Crea el botón para eliminar la categoría
	            const botonEliminar = document.createElement('button');
	            botonEliminar.className = 'btn btn-link text-danger float-end';
	            botonEliminar.innerHTML = '<i class="fas fa-times"></i>';
	            botonEliminar.onclick = function() {
	                removeItem(botonEliminar);
	            };

	            // Añade el botón al elemento li
	            li.appendChild(botonEliminar);

	            // Añade el nuevo elemento a la lista
	            const listaCategorias = document.querySelector('.verCategorias ul');
	            listaCategorias.appendChild(li);
	        } else {
	            console.log(`La categoría "${categoria}" ya está seleccionada.`);
	        }
	    }

	    function removeItem(button) {
	        // Encuentra el elemento li del botón
	        const li = button.closest('.list-group-item'); 

	        // Extrae el texto de la categoría
	        const categoria = li.childNodes[0].textContent.trim();

	        // Elimina el elemento de la lista
	        li.remove();

	        // Elimina la categoría de nuevoProducto.categorias
	        nuevoProducto.categorias = nuevoProducto.categorias.filter(cat => cat !== categoria);
	        console.log(`Categoría "${categoria}" eliminada del producto.`);
	    }
	    
	 // Se asegura de que los datos del formulario sean válidos
	    function revisarDatosProducto() {
	        const inputTitulo = document.getElementById("tituloProducto");
	        const inputReferencia = document.getElementById("numeroReferencia");
	        const inputPrecio = document.getElementById("precioProducto");
	        const inputDescripcion = document.getElementById("descripcionProducto");
	        
	        // Expresión regular para validar que el título solo contenga letras, números y espacios
	        const regexTitulo = /^[a-zA-Z0-9\s]+$/;

	        // Expresión regular para validar el precio (solo números y punto decimal)
	        const regexPrecio = /^[0-9]+(\.[0-9]{1,2})?$/;

	        // Validar si los campos están vacíos
	        if (inputTitulo.value.trim() === "") {
	            mostrarAlerta("El campo de título no puede estar vacío.");
	            return false;
	        }

	        if (inputReferencia.value.trim() === "") {
	            mostrarAlerta("El campo de número de referencia no puede estar vacío.");
	            return false;
	        }

	        if (inputPrecio.value.trim() === "") {
	            mostrarAlerta("El campo de precio no puede estar vacío.");
	            return false;
	        }

	        if (inputDescripcion.value.trim() === "") {
	            mostrarAlerta("El campo de descripción no puede estar vacío.");
	            return false;
	        }

	        // Validar que el título solo contenga letras, números y espacios
	        if (!regexTitulo.test(inputTitulo.value)) {
	            mostrarAlerta("El título solo puede contener letras, números y espacios.");
	            return false;
	        }

	        // Validar que el precio sea un número válido con hasta dos decimales
	        if (!regexPrecio.test(inputPrecio.value)) {
	            mostrarAlerta("El precio debe ser un número válido con hasta dos decimales.");
	            return false;
	        }

	        return true;
	    }
	 
	 
	    document.addEventListener('DOMContentLoaded', function() {
	        const btnCancelar = document.querySelector('.cancelar'); // Selecciona el botón "Cancelar"

	        // Evento para el botón "Cancelar"
	        btnCancelar.addEventListener('click', function() {
	            location.reload(); // Recarga la página
	        });

	       
	    });


	    // Función para reiniciar el objeto nuevoProducto
	    function reiniciarNuevoProducto() {
	        nuevoProducto.nombre = "";
	        nuevoProducto.referencia = "";
	        nuevoProducto.precio = 0;
	        nuevoProducto.descripcion = "";
	        nuevoProducto.especificaciones = [];
	        nuevoProducto.categorias = []; // Reiniciar las categorías
	    }
	    
	    
	 // Escuchar el clic en el botón "Registrar"
	    document.querySelector('.registrar').addEventListener('click', function() {
	        // Validar los datos del producto
	        if (!revisarDatosProducto()) return;

	        // Verificar si al menos una categoría ha sido seleccionada
	        if (nuevoProducto.categorias.length === 0) {
	            mostrarAlerta("Debes seleccionar al menos una categoría.");
	            return;
	        }

	        // Obtener valores de los campos del formulario
	        nuevoProducto.nombre = document.getElementById("tituloProducto").value;
	        nuevoProducto.referencia = document.getElementById("numeroReferencia").value;
	        nuevoProducto.precio = parseFloat(document.getElementById("precioProducto").value);
	        nuevoProducto.descripcion = document.getElementById("descripcionProducto").value;
	        console.log(nuevoProducto);
	        // Agregar especificaciones
	        nuevoProducto.especificaciones = []; // Reiniciar las especificaciones
	        const especificacionesInputs = document.querySelectorAll('.tabla input[type="text"]');
	        especificacionesInputs.forEach(input => {
	            if (input.value.trim() !== "") {
	                nuevoProducto.especificaciones.push(input.value.trim());
	            }
	        });

	        // Construir el mensaje de éxito
	        let mensajeExito = `<strong>Resumen</strong><br>`;
	        mensajeExito += `Nombre: ${nuevoProducto.nombre}<br>`;
	        mensajeExito += `Número de referencia: ${nuevoProducto.referencia}<br>`;
	        mensajeExito += `Precio: $${nuevoProducto.precio.toFixed(2)}<br>`;
	        mensajeExito += `Descripción: ${nuevoProducto.descripcion}<br>`;
	        mensajeExito += `Especificaciones: <br>`;
	        if (nuevoProducto.especificaciones.length > 0) {
	            mensajeExito += nuevoProducto.especificaciones.map(espec => `- ${espec}`).join('<br>');
	        } else {
	            mensajeExito += 'Ninguna';
	        }
	        mensajeExito += '<br>';

	     // Mostrar las categorías seleccionadas en el mensaje de éxito
	        mensajeExito += `Categorías: `;
	        if (nuevoProducto.categorias.length > 0) {
	            mensajeExito += nuevoProducto.categorias.join(', ');
	        } 
	        mensajeExito += '<br>';

	        
	        // Mostrar el mensaje en el modal
	        document.querySelector('#modalCarrito .modal-body').innerHTML = mensajeExito;

	        // Mostrar el modal de éxito
	        $('#modalCarrito').modal('show');
	        console.log(nuevoProducto.precio);
	        console.log(nuevoProducto.nombre);
	        console.log(nuevoProducto.categorias);
	        console.log(nuevoProducto.especificaciones);
	        
	    	
	        cargarProductoEnFormulario(); // Asegúrate de que los datos estén en el formulario
	        document.getElementById('formProducto').submit(); // Envía el formulario
	        
	        // Reiniciar el objeto nuevoProducto para futuros registros
	        reiniciarNuevoProducto();
	    });



	</script>
	
	

	<jsp:include page="/WEB-INF/template/footer.jsp" />

</body>

<!-- Cargar Popper.js y Bootstrap -->
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<!-- Cargar Font Awesome -->
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>

<!-- Scripts personalizados -->
<script src="js/init.js"></script>
<script src="media/js/infoProducto.js"></script>
<script src="media/js/iniciarSesion.js"></script>

</html>


