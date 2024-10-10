let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null; // En un futuro la información se traerá del backend

if(usuarioActual == null) {
    window.location.href = "iniciarSesion.html";
}

if(usuarioActual.tipo == "Proveedor") {
    document.getElementById("proveedor-section").innerHTML = `
        <h2>
            Información Proveedor
        </h2>

        <div class="container-md container-fluid">
            <div class="row">
                <div id="info-proveedor-container">
                    <div class="form-group d-flex align-items-center">
                        <div class="mr-2 w-100">
                            <input type="text" class="form-control" id="inputSitioWeb" placeholder="" disabled>
                            <input type="text" class="form-control mt-2 mb-2" id="inputCompañía" placeholder="" disabled>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="productos-proveedor-container">
            
        </div>

        <div class="container-md container-fluid">
            <div class="row">
                <div id="boton-agregar-producto">
                    <div class="form-group d-flex align-items-center">
                        <div class="mr-2 w-100">
                            <button type="button" class="btn btn-primary botonRosado mt-2" id="agregar-producto-btn">Agregar producto</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
}

// Esta función a futuro trae los productos de un proveedor, dado su id, de la base de datos
function getProductos(id) {
    let productos = [
        {
        "nombre": "Zucaritas",
        "estrellas": 3,
        "precio": 300,
        "descripcion": "Muy ricas, sisi muy muy ricas",
        "id": "777",
        "categorias": [
            "Comida",
            "Dulce",
            "Cereales"
        ],
        "especificacion": [
            "Cereal dulce de maíz",
            "0 proteína 100% lípidos",
            "Totalmente mortal para el cuerpo"
        ],
        "imagenes": [
            "/img/test.jpg",
            "/img/test.jpg"
        ]
        },
        {
        "nombre": "Zucaritas",
        "estrellas": 3,
        "precio": 300,
        "descripcion": "Muy ricas, sisi muy muy ricas",
        "id": "777",
        "categorias": [
            "Comida",
            "Dulce",
            "Cereales"
        ],
        "especificacion": [
            "Cereal dulce de maíz",
            "0 proteína 100% lípidos",
            "Totalmente mortal para el cuerpo"
        ],
        "imagenes": [
            "/img/test.jpg",
            "/img/test.jpg"
        ]
        },
        {
        "nombre": "Zucaritas",
        "estrellas": 3,
        "precio": 300,
        "descripcion": "Muy ricas, sisi muy muy ricas",
        "id": "777",
        "categorias": [
            "Comida",
            "Dulce",
            "Cereales"
        ],
        "especificacion": [
            "Cereal dulce de maíz",
            "0 proteína 100% lípidos",
            "Totalmente mortal para el cuerpo"
        ],
        "imagenes": [
            "/img/test.jpg",
            "/img/test.jpg"
        ]
        },
        {
        "nombre": "Zucaritas",
        "estrellas": 3,
        "precio": 300,
        "descripcion": "Muy ricas, sisi muy muy ricas",
        "id": "777",
        "categorias": [
            "Comida",
            "Dulce",
            "Cereales"
        ],
        "especificacion": [
            "Cereal dulce de maíz",
            "0 proteína 100% lípidos",
            "Totalmente mortal para el cuerpo"
        ],
        "imagenes": [
            "/img/test.jpg",
            "/img/test.jpg"
        ]
        },
        {
        "nombre": "Zucaritas",
        "estrellas": 3,
        "precio": 300,
        "descripcion": "Muy ricas, sisi muy muy ricas",
        "id": "777",
        "categorias": [
            "Comida",
            "Dulce",
            "Cereales"
        ],
        "especificacion": [
            "Cereal dulce de maíz",
            "0 proteína 100% lípidos",
            "Totalmente mortal para el cuerpo"
        ],
        "imagenes": [
            "/img/test.jpg",
            "/img/test.jpg"
        ]
        }
    ]

    return productos;
}

function cargarDatos() {
    const inputNombre = document.getElementById("inputNombre");
    const inputApellido = document.getElementById("inputApellido");
    const inputNickname = document.getElementById("inputNickname");
    const inputEmail = document.getElementById("inputEmail");
    const inputFecha = document.getElementById("inputFecha");
    const fotoPerfilUsuario = document.getElementById("fotoPerfilUsuario");

    inputNombre.value = usuarioActual.nombre;
    inputApellido.value = usuarioActual.apellido;
    inputNickname.value = usuarioActual.nickname;
    inputEmail.value = usuarioActual.email;
    inputFecha.value = usuarioActual.fecha;
    fotoPerfilUsuario.src = usuarioActual.foto;

    let contador = 0;
    for(let orden of usuarioActual.ordenes) {
        document.getElementById("selectOrdenes").innerHTML += `
            <option value="${orden.id}">Orden ${orden.id} - Fecha ${orden.fecha}</option>
        `;
        contador++;
    }

    if(usuarioActual.tipo == "Proveedor") {
        document.getElementById("inputSitioWeb").value = usuarioActual.sitioWeb;
        document.getElementById("inputCompañía").value = usuarioActual.compania;

        let productos = getProductos(usuarioActual.id);

        for(let producto of productos){
            document.getElementById("productos-proveedor-container").innerHTML += `
                <div class="container-md container-fluid product-card">
                    <div class="row">
                        <div class="col-md-3 col-12">
                            <img src="${producto.imagenes[0]}" alt="Imagen producto" class="img-producto">
                        </div>
                        <div class="col-md-8 col-12">
                            <div>
                                <h4 class="titulo-producto">${producto.nombre}</h4>
                                <p class="descripcion-producto">${producto.descripcion}</p>
                                <div class="precio-producto">
                                    $${producto.precio}
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        `;
        }
    }
}

cargarDatos();

// Eventos para los botones de ver orden de compra y agregar producto

document.getElementById("ver-orden-btn").addEventListener("click", () => {
    localStorage.setItem("ordenParaVisualizar", JSON.stringify(document.getElementById("selectOrdenes").value || "0"))
    window.location.href = "infoOrdenCompra.html";
});

if(usuarioActual.tipo == "Proveedor"){
    document.getElementById("agregar-producto-btn").addEventListener("click", () => {
        window.location.href = "registrarProducto.html";
    });
}

// Actualizar datos del usuario

function revisarDatos(){
    const inputNombre = document.getElementById("inputNombre");
    const inputApellido = document.getElementById("inputApellido");
    const inputNickname = document.getElementById("inputNickname");
    const inputEmail = document.getElementById("inputEmail");

    // Expresión regular para validar nombre y apellido (solo letras y espacios)
    const regexNombreApellido = /^[a-zA-Z\s]+$/;

    // Expresión regular para nickname (permitir letras, números, guiones y guiones bajos)
    const regexNickname = /^[a-zA-Z0-9_-]+$/;

    // Validar si los campos están vacíos
    if (inputNombre.value.trim() === "") {
        mostrarAlerta("El campo de nombre no puede estar vacío.");
        return false;
    }

    if (inputApellido.value.trim() === "") {
        mostrarAlerta("El campo de apellido no puede estar vacío.");
        return false;
    }

    if (inputNickname.value.trim() === "") {
        mostrarAlerta("El campo de nickname no puede estar vacío.");
        return false;
    }

    if (inputEmail.value.trim() === "") {
        mostrarAlerta("El campo de correo electrónico no puede estar vacío.");
        return false;
    }

    if (inputFecha.value === "") {
        mostrarAlerta("El campo de fecha no puede estar vacío.");
        return false;
    }

    if (!regexNombreApellido.test(inputNombre.value)) {
        mostrarAlerta("El nombre solo puede contener letras y espacios.");
        return false;
    }

    if (!regexNombreApellido.test(inputApellido.value)) {
        mostrarAlerta("El apellido solo puede contener letras y espacios.");
        return false;
    }

    if (!regexNickname.test(inputNickname.value)) {
        mostrarAlerta("El nickname solo puede contener letras, números, guiones (-) y guiones bajos (_).");
        return false;
    }

    if (!inputEmail.value) {
        mostrarAlerta("Por favor, ingrese un correo electrónico válido.");
        return false;
    }

    // Validación de la fecha: no puede ser posterior a hoy ni menor a 18 años
    const fechaIngresada = new Date(inputFecha.value);
    const hoy = new Date();
    const hace18Anios = new Date();
    hace18Anios.setFullYear(hoy.getFullYear() - 18);

    if (fechaIngresada > hoy) {
        mostrarAlerta("La fecha no puede ser posterior a la fecha actual.");
        return false;
    }

    if (fechaIngresada > hace18Anios) {
        mostrarAlerta("Debes tener al menos 18 años.");
        return false;
    }

    return true;
}

document.getElementById("actualizar-datos-usuario").addEventListener("click", function(event) {
    if(revisarDatos()){
        window.location.reload();
    }
});

// Cambiar foto dinámicamente

document.getElementById("inputFile").addEventListener("change", function(event) {
    const file = event.target.files[0];
    const validImageTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/webp'];

    if (file && validImageTypes.includes(file.type)) {
        const reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById("fotoPerfilUsuario").src = e.target.result;
        };

        reader.readAsDataURL(file);
    } else {
        mostrarAlerta("Por favor, selecciona un archivo de imagen válido (PNG, JPG, JPEG, WEBP).");
    }
});

document.getElementById("cerrar-sesion").addEventListener("click", () => {
    localStorage.removeItem("usuarioActual");
    location.reload();
});