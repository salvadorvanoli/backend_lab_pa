<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="media/css/infoUsuario.css">
</head>
<body>
    
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
                                    <input type="text" class="form-control mt-2" id="inputNombre" placeholder="Ingrese su nombre" required>
                                </div>
                                <div class="w-50">
                                    <input type="text" class="form-control mt-2" id="inputApellido" placeholder="Ingrese su apellido" required>
                                </div>
                            </div>
            
                            <div class="form-group">
                                <input type="text" class="form-control mt-2" id="inputNickname" placeholder="Ingrese su nickname" required>
                            </div>
            
                            <div class="form-group">
                                <input type="email" class="form-control mt-2" id="inputEmail" placeholder="Ingrese su correo electr�nico" required>
                            </div>

                            <div class="form-group">
                                <div class="mr-2 w-50">
                                    <input type="date" class="form-control mt-2" id="inputFecha" required>
                                </div>
                            </div>

                            <div class="form-group d-flex">
                                <div class="mr-2 w-50">
                                    <button type="button" class="btn btn-primary botonRosado mt-2" id="actualizar-datos-usuario">Actualizar datos</button>
                                </div>
                                <div class="w-50">
                                    <button class="btn btn-outline-success mt-2" type="submit" id="cerrar-sesion">Cerrar sesi�n</button>
                                </div>
                            </div>
                        </div>
            
                        <div class="col-md-6 col-12 centro">
                            <div> 
                                <img src="img/Flamin-Go.webp" alt="Foto de perfil" id="fotoPerfilUsuario" class="img-fluid">
                            </div>
                            <div>
                                <input type="file" id="inputFile" class="btn btn-secondary mt-2" accept=".png, .jpg, .jpeg, .webp">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </section>

        <div class="linea-resumen"></div>

        <section id="ver-orden-section">

            <h2>
                Ver orden de compra
            </h2>

            <div class="container-md container-fluid">
                <div class="row">
                    <div id="ver-orden-container">
                        <div class="form-group d-flex align-items-center">
                            <div class="mr-2 w-100">
                                <label for="selectOrdenes">Seleccione una orden de compra</label>
                                <select class="form-control mt-2" id="selectOrdenes">
                                </select>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary botonRosado mt-2" id="ver-orden-btn">Ver orden</button>
                    </div>
                </div>
            </div>
        </section>

        <div class="linea-resumen"></div>

        <section id="proveedor-section">

        </section>

    </main>

    <jsp:include page="/WEB-INF/template/footer.jsp" />
    
    <script src="js/init.js"></script>
    <script src="js/infoUsuario.js"></script>
    <script src="js/navbar.js"></script>
    <!-- /SCRIPTS -->
    <script type="text/javascript">
	    let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || {
	        "nombre": "Salvador",
	        "apellido": "Vanoli",
	        "nickname": "salvaelpro777",
	        "tipo": "proveedor",
	        "email": "salva@salva.com",
	        "fecha": "2004-05-01",
	        "foto": "img/test.jpg",
	        "web": "salva.com",
	        "empresa": "salvaEnterprise",
	        "id": "0",
	        "ordenes": [
	            {
	                "id": 0,
	                "fecha": "2024-09-24",
	                "productos": [
	                    {
	                        "nombre": "Zucaritas",
	                        "precio": 300,
	                        "descripcion": "Muy ricas, sisi muy muy ricas",
	                        "imagenes": [
	                          "/img/test.jpg",
	                          "/img/test.jpg"
	                        ],
	                        "id": "777",
	                        "cantidad": 1
	                    },
	                    {
	                        "nombre": "WATAFAK",
	                        "precio": 200,
	                        "descripcion": "sii",
	                        "imagenes": [
	                          "/img/test.jpg",
	                          "/img/test.jpg"
	                        ],
	                        "id": "778",
	                        "cantidad": 5
	                    }
	                ]
	            },
	            {
	                "id": 1,
	                "fecha": "2024-09-27",
	                "productos": [
	                    {
	                        "nombre": "Zucaritas",
	                        "precio": 400,
	                        "descripcion": "Muy ricas, sisi muy muy ricas",
	                        "imagenes": [
	                          "/img/test.jpg",
	                          "/img/test.jpg"
	                        ],
	                        "id": "777",
	                        "cantidad": 6
	                    },
	                    {
	                        "nombre": "SSSSSSSSSSSESx",
	                        "precio": 200,
	                        "descripcion": "sii",
	                        "imagenes": [
	                          "/img/test.jpg",
	                          "/img/test.jpg"
	                        ],
	                        "id": "778",
	                        "cantidad": 8
	                    }
	                ]
	            },
	        ],
	        "productos": [
	            {
	                "nombre": "Zucaritas",
	                "estrellas": 3,
	                "precio": 300,
	                "descripcion": "Muy ricas, sisi muy muy ricas",
	                "id": "777",
	                "categorias": [
	                    {
	                        "nombre": "Comida",
	                        "hijas": [
	                            {
	                                "nombre": "Dulce",
	                                "hijas": [
	                                    {
	                                        "nombre": "Cereales",
	                                        "hijas": []
	                                    }
	                                ]
	                            }
	                        ]
	                    }
	                ],
	                "especificacion": [
	                    "Cereal dulce de ma�z",
	                    "0 prote�na 100% l�pidos",
	                    "Totalmente mortal para el cuerpo"
	                ],
	                "imagenes": [
	                    "/img/test.jpg",
	                    "/img/test.jpg"
	                ]
	            }
	        ]
	    }; // En un futuro la informaci�n se traer� del backend
	
	    if(usuarioActual == null) {
	        window.location.href = "iniciarSesion.html";
	    }
	
	    if(usuarioActual.tipo == "Proveedor") {
	        document.getElementById("proveedor-section").innerHTML = `
	            <h2>
	                Informaci�n Proveedor
	            </h2>
	
	            <div class="container-md container-fluid">
	                <div class="row">
	                    <div id="info-proveedor-container">
	                        <div class="form-group d-flex align-items-center">
	                            <div class="mr-2 w-100">
	                                <input type="text" class="form-control" id="inputSitioWeb" placeholder="" disabled>
	                                <input type="text" class="form-control mt-2 mb-2" id="inputCompa��a" placeholder="" disabled>
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
	
	    // Esta funci�n a futuro trae los productos de un proveedor, dado su id, de la base de datos
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
	                "Cereal dulce de ma�z",
	                "0 prote�na 100% l�pidos",
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
	                "Cereal dulce de ma�z",
	                "0 prote�na 100% l�pidos",
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
	                "Cereal dulce de ma�z",
	                "0 prote�na 100% l�pidos",
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
	                "Cereal dulce de ma�z",
	                "0 prote�na 100% l�pidos",
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
	                "Cereal dulce de ma�z",
	                "0 prote�na 100% l�pidos",
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
	            document.getElementById("inputCompa��a").value = usuarioActual.compania;
	
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
	
	        // Expresi�n regular para validar nombre y apellido (solo letras y espacios)
	        const regexNombreApellido = /^[a-zA-Z\s]+$/;
	
	        // Expresi�n regular para nickname (permitir letras, n�meros, guiones y guiones bajos)
	        const regexNickname = /^[a-zA-Z0-9_-]+$/;
	
	        // Validar si los campos est�n vac�os
	        if (inputNombre.value.trim() === "") {
	            mostrarAlerta("El campo de nombre no puede estar vac�o.");
	            return false;
	        }
	
	        if (inputApellido.value.trim() === "") {
	            mostrarAlerta("El campo de apellido no puede estar vac�o.");
	            return false;
	        }
	
	        if (inputNickname.value.trim() === "") {
	            mostrarAlerta("El campo de nickname no puede estar vac�o.");
	            return false;
	        }
	
	        if (inputEmail.value.trim() === "") {
	            mostrarAlerta("El campo de correo electr�nico no puede estar vac�o.");
	            return false;
	        }
	
	        if (inputFecha.value === "") {
	            mostrarAlerta("El campo de fecha no puede estar vac�o.");
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
	            mostrarAlerta("El nickname solo puede contener letras, n�meros, guiones (-) y guiones bajos (_).");
	            return false;
	        }
	
	        if (!inputEmail.value) {
	            mostrarAlerta("Por favor, ingrese un correo electr�nico v�lido.");
	            return false;
	        }
	
	        // Validaci�n de la fecha: no puede ser posterior a hoy ni menor a 18 a�os
	        const fechaIngresada = new Date(inputFecha.value);
	        const hoy = new Date();
	        const hace18Anios = new Date();
	        hace18Anios.setFullYear(hoy.getFullYear() - 18);
	
	        if (fechaIngresada > hoy) {
	            mostrarAlerta("La fecha no puede ser posterior a la fecha actual.");
	            return false;
	        }
	
	        if (fechaIngresada > hace18Anios) {
	            mostrarAlerta("Debes tener al menos 18 a�os.");
	            return false;
	        }
	
	        return true;
	    }
	
	    document.getElementById("actualizar-datos-usuario").addEventListener("click", function(event) {
	        if(revisarDatos()){
	            window.location.reload();
	        }
	    });
	
	    // Cambiar foto din�micamente
	
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
	            mostrarAlerta("Por favor, selecciona un archivo de imagen v�lido (PNG, JPG, JPEG, WEBP).");
	        }
	    });
	
	    document.getElementById("cerrar-sesion").addEventListener("click", () => {
	        localStorage.removeItem("usuarioActual");
	        location.reload();
	    });
    </script>

</html>