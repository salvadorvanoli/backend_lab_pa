const subtotales = document.querySelectorAll(".precio-subtotal");
const envios = document.querySelectorAll(".precio-envio");
const impuestos = document.querySelectorAll(".precio-impuestos");
const totales = document.querySelectorAll(".precio-total");

const envioGratis = document.querySelector("#envioGratis");
const envioVip = document.querySelector("#envioVip");

const encabezado1 = document.querySelector("#encabezado1");
const seccion1 = document.querySelector("#seccion1");

const encabezado2 = document.querySelector("#encabezado2");
const seccion2 = document.querySelector("#seccion2");

const encabezado3 = document.querySelector("#encabezado3");
const seccion3 = document.querySelector("#seccion3");


function modificarTextos(array, text) {
    array.forEach(item => {
        item.textContent = text;
    });
}


const carrito = document.querySelector("#carrito");
const contenedoresSecundarios = document.querySelectorAll(".contenedor-secundario");

function manejarCarritoVacio() {
    encabezado1.remove();
    seccion1.remove();
    encabezado2.remove();
    seccion2.remove();
    encabezado3.remove();
    seccion3.remove();
    const texto = document.createElement("div");
    texto.classList.add("text-center", "fs-1", "m-5");
    texto.innerHTML = "No hay productos en el carrito actualmente";
    document.querySelector("main").appendChild(texto);
}

function cargarProducto(id) {
    const productos = JSON.parse(localStorage.getItem("productos"));
    let productoSeleccionado;
    for (let i = 0; i < productos.length; i++){
        if (productos[i].id == id){
            productoSeleccionado = productos[i];
        }
    }
    if (productoSeleccionado != undefined) {
        localStorage.setItem("productoSeleccionado", JSON.stringify(productoSeleccionado));
        window.location.href = "infoProducto.html";
    }
}

function cargarElementosCarrito(array) {
    if (array == undefined || ! array.length > 0){
        manejarCarritoVacio();
    } else {
        let subtotal = 0;
        array.forEach(element => {
            let itemCarrito = document.createElement("div");
            itemCarrito.innerHTML = 
                `<div class="row my-3 d-flex align-items-center" id="producto${element.id}">
                    <div class="col-sm-3 col-4 d-flex align-items-center justify-content-center" onclick="cargarProducto(${element.id})">
                        <img class="w-75" src="${element.imagenes[0]}" alt="${element.nombre}">
                    </div>
                    <div class="col-sm-6 col-4">
                        <div class="row titulo-producto">
                            <p class="col-sm-6 col-12">${element.nombre}</p>
                            <p class="col-sm-6 col-12">Nro. ${element.id}</p>
                        </div>
                        <div class="row descripcion-producto d-none d-sm-block">
                            <p>${element.descripcion}</p>
                        </div>
                        <div class="row precio-producto">
                            <p>$${element.precio}</p>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="col-12">
                            <label for="cantidad">Cantidad</label>
                            <input type="number" name="cantidad" class="cantidad-producto" min="1" required value="${element.cantidad}" onchange="manejarCantidad(this, ${element.id})">
                            <div class="invalid-feedback">
                                <i class="fa-solid fa-triangle-exclamation"></i> Valor inválido.
                            </div>
                        </div>
                        <br>
                        <div class="col-12">    
                            <button type="button" class="btn btn-danger text-nowrap" onclick="eliminarItem(${element.id})"><i class="fa-solid fa-trash-can"></i></button>
                        </div>
                    </div>
                </div>`;

            carrito.appendChild(itemCarrito);

            contenedoresSecundarios.forEach(contenedor => {
                let imagen;
                let itemContenedor = document.createElement("div");
                if (Array.isArray(element.imagenes)){
                    imagen = element.imagenes[0];
                } else {
                    imagen = element.imagenes;
                }
                itemContenedor.innerHTML =
                    `<div class="row my-3 d-flex align-items-center producto-secundario${element.id}">
                        <div class="col-4 d-flex align-items-center justify-content-center">
                            <img class="w-75" src="${imagen}" alt="Zucaritas">
                        </div>
                        <div class="col-4">
                            <div class="row titulo-producto">
                                <p>${element.nombre}</p>
                            </div>
                            <div class="row precio-producto">
                                <p>$${element.precio}</p>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="col-12">
                                <label for="cantidad">Cant.</label>
                                <input type="number" name="cantidad" class="cantidad-producto" disabled value="${element.cantidad}">
                            </div>
                        </div>
                    </div>`;

                contenedor.appendChild(itemContenedor);
            });

            subtotal += element.precio * element.cantidad;

        });

        modificarTextos(subtotales, "$" + subtotal);
        modificarTextos(envios, "GRATIS");
        modificarTextos(impuestos, "$" + subtotal * 0.02);
        modificarTextos(totales, "$" + subtotal * 1.02);

    }
}


function modificarAllTextos(array){
    let subtotal = 0;

    array.forEach(item => {
        subtotal += item.precio * item.cantidad;
    });

    modificarTextos(subtotales, "$" + subtotal);
    if (envioGratis.checked){
        modificarTextos(envios, "GRATIS");
    } else {
        modificarTextos(envios, "$20");
        subtotal += 20;
    }
    modificarTextos(impuestos, "$" + subtotal * 0.02);
    modificarTextos(totales, "$" + subtotal * 1.02);
}


function manejarCantidad(input, id){
    cantidad = Number.parseInt(input.value);
    if ( !(Number.isInteger(cantidad)) || cantidad < 1){
        input.value = 1;
    }

    const carritoActual = JSON.parse(localStorage.getItem('carritoActual'));

    for (let i = 0; i < carritoActual.length; i++){
        if (carritoActual[i].id == id){
            carritoActual[i].cantidad = Number.parseInt(input.value);
            break;
        }
    }

    const itemsSecundarios = document.querySelectorAll(".producto-secundario" + id);

    itemsSecundarios.forEach(itemSecundario => {
        itemSecundario.querySelector(".cantidad-producto").value = input.value;
    });

    localStorage.setItem("carritoActual", JSON.stringify(carritoActual));

    modificarAllTextos(carritoActual);

}


function eliminarItem(id){

    const carritoActual = JSON.parse(localStorage.getItem("carritoActual"));
    
    for (let i = 0; i < carritoActual.length; i++){
        if (carritoActual[i].id == id){
            carritoActual.splice(i, 1);
            break;
        }
    }

    if (carritoActual.length == 0){
        encabezado1.remove();
        seccion1.remove();
        encabezado2.remove();
        seccion2.remove();
        encabezado3.remove();
        seccion3.remove();
        const texto = document.createElement("div");
        texto.classList.add("text-center", "fs-1", "m-5");
        texto.innerHTML = "No hay productos en el carrito actualmente";
        document.querySelector("main").appendChild(texto);
    }

    localStorage.setItem("carritoActual", JSON.stringify(carritoActual));

    modificarAllTextos(carritoActual);

    const item = document.querySelector("#producto" + id);
    item.remove();

    const itemsSecundarios = document.querySelectorAll(".producto-secundario" + id);

    itemsSecundarios.forEach(itemSecundario => {
        itemSecundario.remove();
    });

}


function modificarEnvio(){
    const carritoActual = JSON.parse(localStorage.getItem("carritoActual"));
    let subtotal = 0;
    carritoActual.forEach(element => {
        subtotal += element.precio * element.cantidad;
    });

    // console.log(envioGratis.checked);

    if (envioGratis.checked){
        modificarTextos(envios, "GRATIS");
        modificarTextos(impuestos, "$" + (subtotal) * 0.02)
        modificarTextos(totales, "$" + ((subtotal) + (subtotal) * 0.02));
    } else {
        modificarTextos(envios, "$20");
        modificarTextos(impuestos, "$" + (subtotal + 20) * 0.02)
        modificarTextos(totales, "$" + ((subtotal + 20) + (subtotal + 20) * 0.02));
    }
}

function mostrarAlerta(message, type, icon){
    const alertaActual = document.querySelector(".alerta-carrito");
    if (alertaActual != undefined)
        alertaActual.remove();
    const div = document.createElement("div");
    div.innerHTML = 
        `<div class="alert ${type} alert-dismissible fade show alerta-carrito fs-5 d-flex justify-items-center align-items-center" role="alert" id="alertaFormulario">
            <strong>${icon}</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" onclick="alertaEnPantalla=false;"></button>
        </div>`;
    document.body.appendChild(div);
}


function checkUsuarioActual(){
    if (usuarioActual == undefined){
        encabezado1.remove();
        seccion1.remove();
        encabezado2.remove();
        seccion2.remove();
        encabezado3.remove();
        seccion3.remove();
        const texto = document.createElement("div");
        texto.classList.add("text-center", "fs-1", "m-5");
        texto.innerHTML = "Aún no tienes la sesión iniciada. Serás redirigido a inicio en unos segundos.";
        document.querySelector("main").appendChild(texto);
        setTimeout(function () {
            window.location.href = "index.html";
        }, 5000);
    }
}

checkUsuarioActual();



const form1 = document.querySelector("#formCarrito");
const form2 = document.querySelector("#formEnvio");
const form3 = document.querySelector("#formPago");
const formFinal = document.querySelector("#formFinal");

const departamentos = document.querySelector("#departamento");
const ciudades = document.querySelector("#ciudad");


form1.addEventListener('submit', function(e) {
    e.preventDefault();
    e.stopPropagation();
    if (form1.checkValidity()) {
        encabezado1.classList.add("d-none");
        seccion1.classList.add("d-none");
        encabezado2.classList.remove("d-none");
        seccion2.classList.remove("d-none");
    } else {
        if (document.querySelector("#alertaFormulario") == undefined){
            mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
        }
    }

    form1.classList.add('was-validated');
}, false);

form2.addEventListener("submit", function(e) {
    e.preventDefault();
    e.stopPropagation();
    if (form2.checkValidity()) {
        encabezado2.classList.add("d-none");
        seccion2.classList.add("d-none");
        encabezado3.classList.remove("d-none");
        seccion3.classList.remove("d-none");
    } else {
        if (document.querySelector("#alertaFormulario") == undefined){
            mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
        }
    }

    form2.classList.add('was-validated');
}, false);


function agregarOrdenCompra() {
    let idOrden = 1;
    const usuarios = JSON.parse(localStorage.getItem("usuarios"));
    usuarios.forEach(usuario => {
        if (usuario.hasOwnProperty("ordenes")){
            usuario.ordenes.forEach(orden => {
                if (idOrden <= orden.id){
                    idOrden = orden.id + 1;
                }
            });
        }
    });

    const fechaActual = new Date().toJSON().slice(0, 10);

    const nombre = document.querySelector("#nombre").value;
    const apellido = document.querySelector("#apellido").value;
    const direccion1 = document.querySelector("#direccion1").value;
    const direccion2 = document.querySelector("#direccion2").value;
    const departamento = document.querySelector("#departamento").value;
    const ciudad = document.querySelector("#ciudad").value;
    const codPostal = document.querySelector("#codPostal").value;
    const numTelefono = document.querySelector("#numTelefono").value;

    let tipoEnvio;
    let precioEnvio;
    if (document.querySelector("#envioGratis").checked) {
        tipoEnvio = document.querySelector("#envioGratis").value;
        precioEnvio = 0;
    } else {
        tipoEnvio = document.querySelector("#envioVip").value;
        precioEnvio = 20;
    }

    const detallesEnvio = {
        "nombre": nombre,
        "apellido": apellido,
        "direccion1": direccion1,
        "direccion2": direccion2,
        "departamento": departamento,
        "ciudad": ciudad,
        "codPostal": codPostal,
        "numTelefono": numTelefono,
        "tipoEnvio": tipoEnvio,
        "precioEnvio": precioEnvio
    }

    let formaPago;

    if (document.querySelector("#pagoTarjeta").checked){
        const numTarjeta = document.querySelector("#numTarjeta").value;
        const fecVencimiento = document.querySelector("#fecVencimiento").value;
        const cvv = document.querySelector("#cvv").value;
        const nomTitular = document.querySelector("#nomTitular").value;
        formaPago = {
            "tipoPago": "credito",
            "numTarjeta": numTarjeta,
            "fecVencimiento": fecVencimiento,
            "cvv": cvv,
            "nomTitular": nomTitular
        }
    } else {
        // Manejar el pago con PayPal
    }

    carritoActual = JSON.parse(localStorage.getItem("carritoActual"));

    const nuevaOrden = {
        "id": idOrden,
        "fecha": fechaActual,
        "productos": carritoActual,
        "detallesEnvio": detallesEnvio,
        "formaPago": formaPago
    }

    if (!usuarioActual.hasOwnProperty("ordenes")){
        usuarioActual.ordenes = [];
    }

    usuarioActual.ordenes.push(nuevaOrden);

    localStorage.setItem("usuarioActual", JSON.stringify(usuarioActual));
    for (let i = 0; i < usuarios.length; i++){
        if (usuarios[i].nickname == usuarioActual.nickname){
            usuarios[i] = usuarioActual;
            break;
        }
    }
    localStorage.setItem("usuarios", JSON.stringify(usuarios));
    localStorage.setItem("carritoActual", "[]");

}

function fechaValida(){
    let partesFecha = document.querySelector("#fecVencimiento").value.split('/');

    if (partesFecha.length != 2){
        return false;
    }

    for (let i = 0; i < partesFecha.length; i++){
        partesFecha[i] = Number.parseInt(partesFecha[i]);
    }

    const date = new Date();
    const MM = Number.parseInt(date.getMonth()) + 1;
    const year = date.getFullYear().toString();

    // console.log(partesFecha);

    const YY = Number.parseInt(year.slice(2));

    /*
    console.log("MM: " + MM + "\nYY: " + YY);

    console.log("CONDICION 1: " + partesFecha[1] > YY);
    console.log("CONDICION 2: " + (partesFecha[1] == YY && partesFecha[0] > MM));
    */

    return !(partesFecha[1] < YY || (partesFecha[1] == YY && partesFecha[0] < MM));
}

function disableInputs() {
    inputs = document.querySelectorAll("input");
    inputs.forEach(input => {
        input.setAttribute("disabled", true);
    });
}

form3.addEventListener("submit", function(e) {
    e.preventDefault();
    e.stopPropagation();
    // let formulariosValidos = true;
    let mensajeError = "";
    if (form3.checkValidity()) {
        if (fechaValida()) {
            if (form2.checkValidity()) {
                if (form1.checkValidity()) {
                    disableInputs();
                    agregarOrdenCompra();
                    mostrarAlerta("¡Compra realizada de manera exitosa! Serás redirigido al inicio.", "alert-success", '<i class="fa-solid fa-circle-check me-3"></i>');
                    setTimeout(function () {
                        window.location.href = "index.html";
                    }, 5000);
                } else {
                    mensajeError = "Se encontraron errores de validación en el Carrito de Compra."
                    // formulariosValidos = false;
                }
            } else {
                // formulariosValidos = false;
                if (mensajeError != ""){
                    mensajeError = mensajeError.slice(0, -1);
                    mensajeError += " y en los Detalles del envío.";
                } else {
                    mensajeError = "Se encontraron errores de validación en los Detalles del envío."
                }
            }
        } else {
            mostrarAlerta("La fecha ingresada es inválida.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
            const fecha = document.querySelector("#fecVencimiento");
            fecha.value = "";
        }
    } else {
        if (document.querySelector("#alertaFormulario") == undefined){
            mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
        }
    }

    if (mensajeError != ""){
        mostrarAlerta(mensajeError, "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
    }

    form3.classList.add('was-validated');
}, false);

volver = document.querySelector("#botonVolver");

volver.addEventListener("click", function(e) {
    // Te redirige a inicio
});

atras1 = document.querySelector("#botonAtras1");

atras1.addEventListener("click", function(e) {
    encabezado2.classList.add("d-none");
    seccion2.classList.add("d-none");
    encabezado1.classList.remove("d-none");
    seccion1.classList.remove("d-none");
});

atras2 = document.querySelector("#botonAtras2");

atras2.addEventListener("click", function(e) {
    encabezado3.classList.add("d-none");
    seccion3.classList.add("d-none");
    encabezado2.classList.remove("d-none");
    seccion2.classList.remove("d-none");
});



async function getRegiones() {
    const URL_REGIONES = "https://gist.githubusercontent.com/fedebabrauskas/b708c2a1b7a29af94927ad0e8d6d6a27/raw/b0c544d53c82de298ccedb824f8dd5e5ef5477e7/localidades.json";
    try {
        const response = await fetch(URL_REGIONES);
        if (!response.ok){
            throw new Error("Response status: " + response.status);
        }
        const json = await response.json();
        // console.log(json);
        return json;
    } catch (error) {
        console.log(error.message);
    }
}

async function cargarCiudades() {
    const regiones = await getRegiones();
    regiones.forEach(region => {
        if (region.name == departamentos.value){   
            if (region.towns.length != 0){
                ciudades.textContent = "";
                region.towns.forEach(town => {
                    const option = document.createElement("option");
                    const text = document.createTextNode(town.name);
                    option.setAttribute("value", town.name);
                    option.appendChild(text);
                    ciudades.appendChild(option);
                });
                return;
            }
        }
    });
}

if (usuarioActual != undefined) {
    cargarElementosCarrito(carritoActual);
}

departamentos.addEventListener("change", async function(e){
    if (departamentos.value != ""){
        ciudades.removeAttribute('disabled');
        await cargarCiudades();
    } else {
        ciudades.setAttribute('disabled', true);
        ciudades.textContent = "";
        const option = document.createElement("option");
        const text = document.createTextNode("Elige una ciudad");
        option.setAttribute("value", "");
        option.appendChild(text);
        ciudades.appendChild(option);
    }
});

departamentos.value = "";
// document.addEventListener("DOMContentLoaded", async function(e) {

//     // Cargamos al usuario actual

//     localStorage.setItem("usuarioActual", JSON.stringify(
//     {
//         "nombre": "Salvador",
//         "apellido": "Vanoli",
//         "nickname": "salvaelpro777",
//         "tipo": "proveedor",
//         "email": "salva@salva.com",
//         "fecha": "2004-05-01",
//         "foto": "img/test.jpg",
//         "web": "salva.com",
//         "empresa": "salvaEnterprise",
//         "id": "0",
//         "ordenes": [
//             {
//                 "id": 0,
//                 "fecha": "2024-09-24",
//                 "productos": [
//                     {
//                         "nombre": "Zucaritas",
//                         "precio": 300,
//                         "descripcion": "Muy ricas, sisi muy muy ricas",
//                         "imagenes": [
//                           "/img/test.jpg",
//                           "/img/test.jpg"
//                         ],
//                         "id": "777",
//                         "cantidad": 1
//                     },
//                     {
//                         "nombre": "WATAFAK",
//                         "precio": 200,
//                         "descripcion": "sii",
//                         "imagenes": [
//                           "/img/test.jpg",
//                           "/img/test.jpg"
//                         ],
//                         "id": "778",
//                         "cantidad": 5
//                     }
//                 ]
//             },
//             {
//                 "id": 1,
//                 "fecha": "2024-09-27",
//                 "productos": [
//                     {
//                         "nombre": "Zucaritas",
//                         "precio": 400,
//                         "descripcion": "Muy ricas, sisi muy muy ricas",
//                         "imagenes": [
//                           "/img/test.jpg",
//                           "/img/test.jpg"
//                         ],
//                         "id": "777",
//                         "cantidad": 6
//                     },
//                     {
//                         "nombre": "SSSSSSSSSSSESx",
//                         "precio": 200,
//                         "descripcion": "sii",
//                         "imagenes": [
//                           "/img/test.jpg",
//                           "/img/test.jpg"
//                         ],
//                         "id": "778",
//                         "cantidad": 8
//                     }
//                 ]
//             },
//         ],
//         "productos": [
//             {
//                 "nombre": "Zucaritas",
//                 "estrellas": 3,
//                 "precio": 300,
//                 "descripcion": "Muy ricas, sisi muy muy ricas",
//                 "id": "777",
//                 "categorias": [
//                     "Comida",
//                     "Dulce",
//                     "Cereales"
//                 ],
//                 "especificacion": [
//                     "Cereal dulce de maíz",
//                     "0 proteína 100% lípidos",
//                     "Totalmente mortal para el cuerpo"
//                 ],
//                 "imagenes": [
//                     "/img/test.jpg",
//                     "/img/test.jpg"
//                 ]
//             }
//         ]
//     }));
    
//     // Cargamos al carrito actual
    
//     localStorage.setItem("carritoActual", '[{"nombre":"Zucaritas","precio":300,"descripcion":"Muy ricas, sisi muy muy ricas","imagenes":["/img/test.jpg","/img/test.jpg"],"id":"777","cantidad":7},{"nombre":"Laptop","precio":7000,"descripcion":"Super potente master","imagenes":["img/Zucaritas.webp"],"id":"69","cantidad":4}]')

//     checkUsuarioActual();

//     if (usuarioActual != undefined) {
//         cargarElementosCarrito(usuarioActual, carritoActual);
//     }

//     departamentos.value = "";

// });