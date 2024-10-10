let proveedorSeleccionado = JSON.parse(localStorage.getItem("proveedorSeleccionado")) || usuarios[0];



// Genera ID del producto random
function generarIdRandom() {
    return 'id-' + Date.now() + '-' + Math.floor(Math.random() * 10000);
}

// Creo producto vacío solo con el proveedor y un id única (no sé se tiene que ser así lo de la ID)
let nuevoProducto = {
    id: generarIdRandom(),
    proveedor: proveedorSeleccionado,
    nombre: "",
    precio: 0,
    categorias: [],
    especificaciones: [],
    imagenes: [],
    comentarios: []
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
        li.innerHTML = `${categoria} <button class="btn btn-link text-danger float-end" onclick="removeItem(this)"><i class="fas fa-times"></i></button>`;

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

    // Extrae el texto de la categoría, asumiendo que no hay caracteres adicionales
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

    // Agregar especificaciones
    nuevoProducto.especificaciones = []; // Reiniciar las especificaciones
    const especificacionesInputs = document.querySelectorAll('.tabla input[type="text"]');
    especificacionesInputs.forEach(input => {
        if (input.value.trim() !== "") {
            nuevoProducto.especificaciones.push(input.value.trim());
        }
    });

    // Agregar el nuevo producto a la lista de productos
	productos.unshift({ ...nuevoProducto });
	localStorage.setItem('productoSeleccionado', JSON.stringify(nuevoProducto));

    localStorage.setItem("productos", JSON.stringify(productos));

    // Construir el mensaje de éxito
    let mensajeExito = `<strong>Resumen</strong><br>`;
    mensajeExito += `Nombre: ${nuevoProducto.nombre}<br>`;
    mensajeExito += `Número de referencia: ${nuevoProducto.referencia}<br>`;
    mensajeExito += `Precio: $${nuevoProducto.precio.toFixed(2)}<br>`;
    mensajeExito += `Descripción: ${nuevoProducto.descripcion}<br>`;
	mensajeExito += `Especificaciones: ${
	    nuevoProducto.especificaciones.length > 0
	        ? nuevoProducto.especificaciones.map(espec => `- ${espec}`).join('<br>')
	        : 'Ninguna'
	}<br>`;

    // Mostrar las categorías seleccionadas en el mensaje de éxito
    mensajeExito += `Categorías: ${nuevoProducto.categorias.length > 0 ? nuevoProducto.categorias.join(', ') : 'Ninguna'}<br>`;

    // Mostrar el mensaje en el modal
    document.querySelector('#modalCarrito .modal-body').innerHTML = mensajeExito;

    // Mostrar el modal de éxito
    $('#modalCarrito').modal('show');

    // Reiniciar el objeto nuevoProducto para futuros registros
    reiniciarNuevoProducto();
});

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

